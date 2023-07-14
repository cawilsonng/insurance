import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {catchError, Observable, throwError} from 'rxjs';
import {CarModelsJson} from "../models/car-model";
import {CarInsuranceQuoteRequestModel} from "../models/car-insurance-quote-request-model";

@Injectable()
export class CarInsuranceService {
  constructor(private httpClient: HttpClient) {
  }

  fetchCarModels(): Observable<CarModelsJson> {
    const url = `https://storage.googleapis.com/connex-th/insurance_assignment/car_model.json`;
    return this.httpClient.get<CarModelsJson>(url)
      .pipe(
        catchError(this.handleError)
      );
  }

  getQuote(data: CarInsuranceQuoteRequestModel): Observable<any> {
    return this.httpClient.post<any>('api/quotation/car', data, {})
      .pipe(
        catchError(this.handleError)
      );
  }

  private calculateDepreciationRate(diffYear: number) {
    let depreciationRate = 1;
    if (diffYear > 0) {
      if (diffYear <= 3) {
        depreciationRate *= Math.pow(0.85, diffYear);
      } else {
        depreciationRate *= Math.pow(0.85, 3) * Math.pow(0.9, diffYear - 3);
      }
    }
    return depreciationRate;
  }

  convertFormDataToRequestModel(quoteRequestFormValue: any) {
    let currentYear = new Date().getFullYear();
    let purchaseYear = Number(quoteRequestFormValue.year);
    let diffYear = currentYear - purchaseYear;
    let depreciationRate = this.calculateDepreciationRate(diffYear);
    let carValue = quoteRequestFormValue.purchasePrice * depreciationRate;
    let annualMileage = Number(quoteRequestFormValue.mileage) / diffYear;
    let carInsuranceQuoteRequestModel: any = {
      age: Number(quoteRequestFormValue.age),
      claims: Number(quoteRequestFormValue.claims.count),
      car_value: carValue,
      annual_mileage: annualMileage,
      insurance_history: Number(quoteRequestFormValue.insuranceHistory.count),
      driver_record: Number(quoteRequestFormValue.drivingRecord.count),
      driving_experience: Number(quoteRequestFormValue.drivingExperience),
      car_category: quoteRequestFormValue.category,
      car_brand: quoteRequestFormValue.brand,
      car_model: quoteRequestFormValue.model,
      car_year: quoteRequestFormValue.year,
      car_price: quoteRequestFormValue.purchasePrice,
      is_personal_use: quoteRequestFormValue.isPersonalUse,
    }
    return carInsuranceQuoteRequestModel;
  }

  private handleError(error: HttpErrorResponse, caught: Observable<any>) {
    if (error.status === 0) {
      console.error('An error occurred:', error.error);
    } else {
      console.error(
        `Backend returned code ${error.status}, body was: `, error.error);
    }
    return throwError(() => new Error('Something bad happened; please try again later.'));
  }
}
