import {ComponentFixture, TestBed} from '@angular/core/testing';

import {PageNotFoundComponent} from './page-not-found.component';
import {PageLayoutComponent} from "../../layout/page-layout/page-layout.component";

describe('PageNotFoundComponent', () => {
  let component: PageNotFoundComponent;
  let fixture: ComponentFixture<PageNotFoundComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PageNotFoundComponent, PageLayoutComponent]
    });
    fixture = TestBed.createComponent(PageNotFoundComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should notice Page Not found', () => {
    const content = fixture.nativeElement.textContent;
    expect(content).toContain("Page Not Found");
  });
});
