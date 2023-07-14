import {ComponentFixture, TestBed} from '@angular/core/testing';

import {CarInsuranceQuotationComponent} from './car-insurance-quotation.component';
import {PageLayoutComponent} from "../../layout/page-layout/page-layout.component";
import {QuoteFormComponent} from "../../components/quote-form/quote-form.component";
import {HttpClientTestingModule, HttpTestingController} from "@angular/common/http/testing";
import {HttpClient} from "@angular/common/http";
import {ReactiveFormsModule} from "@angular/forms";
import {QuoteResultComponent} from "../../components/quote-result/quote-result.component";

describe('CarInsuranceQuotationComponent', () => {
  let component: CarInsuranceQuotationComponent;
  let fixture: ComponentFixture<CarInsuranceQuotationComponent>;
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, ReactiveFormsModule],
      declarations: [CarInsuranceQuotationComponent, PageLayoutComponent, QuoteFormComponent, QuoteResultComponent]
    });
    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
    fixture = TestBed.createComponent(CarInsuranceQuotationComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should render form in step 1', () => {
    component.step = 1;
    fixture.detectChanges();
    const selector = fixture.nativeElement.querySelector('app-quote-form');
    expect(selector).toBeTruthy();
  });

  it('should render result in step 2', () => {
    component.step = 2;
    fixture.detectChanges();
    const selector = fixture.nativeElement.querySelector('app-quote-result');
    expect(selector).toBeTruthy();
  });
});
