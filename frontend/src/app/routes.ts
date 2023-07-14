import {CarInsuranceQuotationComponent} from "./pages/car-insurance-quotation/car-insurance-quotation.component";
import {WelcomeComponent} from "./pages/welcome/welcome.component";
import {PageNotFoundComponent} from "./pages/page-not-found/page-not-found.component";

export const routes = [
  {path: 'insurance/car', component: CarInsuranceQuotationComponent},
  {path: '', component: WelcomeComponent},
  {path: '**', component: PageNotFoundComponent}
]
