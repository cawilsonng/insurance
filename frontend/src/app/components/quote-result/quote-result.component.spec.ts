import {ComponentFixture, TestBed} from '@angular/core/testing';

import {QuoteResultComponent} from './quote-result.component';

describe('QuoteResultComponent', () => {
  let component: QuoteResultComponent;
  let fixture: ComponentFixture<QuoteResultComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [QuoteResultComponent]
    });
    fixture = TestBed.createComponent(QuoteResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should render result with success', () => {
    component.quotation.success = true;
    fixture.detectChanges();
    const content = fixture.nativeElement.textContent;
    expect(content).toContain('Congratulation');
  });

  it('should render custom quote in step 2', () => {
    component.quotation.success = false;
    fixture.detectChanges();
    const content = fixture.nativeElement.textContent;
    expect(content).toContain('Oops!');
  });
});
