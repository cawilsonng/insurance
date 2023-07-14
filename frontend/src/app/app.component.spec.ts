import {ComponentFixture, fakeAsync, TestBed} from '@angular/core/testing';
import {AppComponent} from './app.component';
import {RouterTestingModule} from "@angular/router/testing";
import {Router} from "@angular/router";
import {routes} from "./routes";
import {PageLayoutComponent} from "./layout/page-layout/page-layout.component";
import {WelcomeComponent} from "./pages/welcome/welcome.component";
import {CarInsuranceQuotationComponent} from "./pages/car-insurance-quotation/car-insurance-quotation.component";
import {PageNotFoundComponent} from "./pages/page-not-found/page-not-found.component";

describe('AppComponent', () => {
  let fixture: ComponentFixture<AppComponent>;
  let router: Router;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes(routes)],
      declarations: [AppComponent, PageLayoutComponent,
        WelcomeComponent, CarInsuranceQuotationComponent, PageNotFoundComponent]
    });
    router = TestBed.inject(Router);
    router.initialNavigation();
    fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
  });

  it('should create', () => {
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it('navigate to "" should render welcome', fakeAsync(() => {
    router.navigate([""]).then(() => {
      const selector = fixture.nativeElement.querySelector('app-welcome');
      expect(selector).toBeTruthy();
    });
  }));

  it('navigate to "insurance/car" should render not found', fakeAsync(() => {
    router.navigate(["insurance/car"]).then(() => {
      const selector = fixture.nativeElement.querySelector('app-car-insurance-quotation');
      expect(selector).toBeTruthy();
    });
  }));

  it('navigate to "123" should render not found', fakeAsync(() => {
    router.navigate(["123"]).then(() => {
      const selector = fixture.nativeElement.querySelector('app-page-not-found');
      expect(selector).toBeTruthy();
    });
  }));

});
