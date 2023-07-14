import {Component, EventEmitter, Output} from '@angular/core';
import {CarModel} from "../../models/car-model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CarInsuranceService} from "../../services/car-insurance.service";
import {Quotation} from "../../models/quotation";
import {CarInsuranceQuoteRequestModel} from "../../models/car-insurance-quote-request-model";

@Component({
  selector: 'app-quote-form',
  templateUrl: './quote-form.component.html',
  styleUrls: ['./quote-form.component.css'],
  providers: [CarInsuranceService]
})
export class QuoteFormComponent {
  @Output() quotationUpdateEvent = new EventEmitter<Quotation>();

  carModels = new Array<CarModel>();
  categories = new Array<string>();
  categoryMakeMap = new Map<string, Array<string>>();
  submitClicked = false;
  errorMsg = '123';

  quoteRequestForm = new FormGroup({
    category: new FormControl('', [Validators.required]),
    brand: new FormControl('', [Validators.required]),
    model: new FormControl('', [Validators.required]),
    year: new FormControl(null, [Validators.required]),
    age: new FormControl(16, [Validators.required]),
    drivingExperience: new FormControl(null, [Validators.required, Validators.pattern('[0-9]*')]),
    drivingRecord: new FormGroup({
      status: new FormControl(null, [Validators.required]),
      count: new FormControl(0, [Validators.required, Validators.pattern('[0-9]*')]),
    }),
    claims: new FormGroup({
      status: new FormControl(null, [Validators.required]),
      count: new FormControl(0, [Validators.required, Validators.pattern('[0-9]*')]),
    }),
    purchasePrice: new FormControl(null, [Validators.required, Validators.pattern('[0-9]*')]),
    mileage: new FormControl(null, [Validators.required, Validators.pattern('[0-9]*')]),
    insuranceHistory: new FormGroup({
      status: new FormControl(null, [Validators.required]),
      count: new FormControl(0, [Validators.required, Validators.pattern('[0-9]*')]),
    }),
    isPersonalUse: new FormControl(null, [Validators.required]),
    confirm: new FormControl(false, [Validators.requiredTrue]),
  });

  validationCondition(formFieldName: string) {
    let obj = this.quoteRequestForm.get(formFieldName);
    return obj && obj.invalid && (obj.dirty || obj.touched || this.submitClicked)
  }

  getValidationMsg(formFieldName: string, validationPath: string) {
    let obj = this.quoteRequestForm.get(formFieldName);
    return obj && obj.errors?.[validationPath];
  }

  findModels(make: string, category: string) {
    return [...new Set(this.carModels.filter(x => x.Make == make && x.Category == category).map(carModel => carModel.Model))].sort().reverse();
  }

  findYears(make: string, category: string, model: string) {
    return this.carModels.filter(x => x.Make == make && x.Category == category && x.Model == model).sort((a, b) => Number(a.Year) - Number(b.Year)).reverse();
  }

  resetCount(formName: string) {
    this.quoteRequestForm.get(formName)?.setValue(0, {emitEvent: false});
  }

  onSubmit() {
    this.errorMsg = '';
    this.submitClicked = true;
    if (this.quoteRequestForm.invalid) {
      return;
    }
    let carInsuranceQuoteRequestModel: CarInsuranceQuoteRequestModel = this.insuranceApiService.convertFormDataToRequestModel(this.quoteRequestForm.value);
    this.insuranceApiService.getQuote(carInsuranceQuoteRequestModel).subscribe({
      next: (response) => {
        this.quotationUpdateEvent.emit(response);
      },
      error: (err: Error) => this.errorMsg = "The server is not available now. Please try it later.",
    });
  }

  constructor(private insuranceApiService: CarInsuranceService) {
    insuranceApiService.fetchCarModels().subscribe(response => {
      this.carModels = response.data.car_Model_Lists.results;
      let categorySet = new Set<string>();
      let categoryMakeSetMap = new Map<string, Set<string>>();
      this.carModels.map((carModel) => {
        let categories = carModel.Category.split(',');
        categories.forEach(category => {
          category = category.trim();
          categorySet.add(category);
          if (categoryMakeSetMap.has(category)) {
            let makeSet = categoryMakeSetMap.get(category);
            if (makeSet) {
              makeSet.add(carModel.Make);
            }
          } else {
            let makeSet = new Set<string>();
            makeSet.add(carModel.Make);
            categoryMakeSetMap.set(category, makeSet);
          }
        })
      })
      this.categories = [...categorySet].sort();
      categoryMakeSetMap.forEach((makeSet, key) => this.categoryMakeMap.set(key, [...makeSet].sort()))
    });
  }
}
