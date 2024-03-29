import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageLayoutComponent } from './page-layout.component';

describe('PageLayoutComponent', () => {
  let component: PageLayoutComponent;
  let fixture: ComponentFixture<PageLayoutComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PageLayoutComponent]
    });
    fixture = TestBed.createComponent(PageLayoutComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should render background image', () => {
    const selector = fixture.nativeElement.querySelector('#clouds');
    expect(selector).toBeTruthy();
  });
});
