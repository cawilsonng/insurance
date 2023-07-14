import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {RouterModule} from '@angular/router';
import {PageNotFoundComponent} from './pages/page-not-found/page-not-found.component';
import {CarInsuranceQuotationComponent} from './pages/car-insurance-quotation/car-insurance-quotation.component';
import {WelcomeComponent} from './pages/welcome/welcome.component';
import {PageLayoutComponent} from './layout/page-layout/page-layout.component';
import {HttpClientModule} from "@angular/common/http";
import {ReactiveFormsModule} from "@angular/forms";
import {QuoteFormComponent} from './components/quote-form/quote-form.component';
import {QuoteResultComponent} from './components/quote-result/quote-result.component';
import {routes} from "./routes";

@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    CarInsuranceQuotationComponent,
    WelcomeComponent,
    PageLayoutComponent,
    QuoteFormComponent,
    QuoteResultComponent
  ],
  imports: [
    BrowserModule,
    NgbModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],

})
export class AppModule {
}
