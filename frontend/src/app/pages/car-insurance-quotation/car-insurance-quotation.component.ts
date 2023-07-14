import {Component} from '@angular/core';
import {CarInsuranceService} from "../../services/car-insurance.service";
import {Quotation} from "../../models/quotation";

@Component({
  selector: 'app-car-insurance-quotation',
  templateUrl: './car-insurance-quotation.component.html',
  styleUrls: ['./car-insurance-quotation.component.css'],
  providers: [CarInsuranceService]
})
export class CarInsuranceQuotationComponent {
  step = 1;
  quotation: Quotation = {
    success: false,
    premium: 0,
    quote_reference: '',
  };

  setQuotation(quotation: Quotation) {
    this.step = 2;
    this.quotation = quotation;
  }
}
