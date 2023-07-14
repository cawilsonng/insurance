import {ComponentFixture, TestBed} from '@angular/core/testing';

import {WelcomeComponent} from './welcome.component';
import {PageLayoutComponent} from "../../layout/page-layout/page-layout.component";

describe('WelcomeComponent', () => {
  let component: WelcomeComponent;
  let fixture: ComponentFixture<WelcomeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [WelcomeComponent, PageLayoutComponent]
    });
    fixture = TestBed.createComponent(WelcomeComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should welcome the user', () => {
    const content = fixture.nativeElement.querySelector('.welcome').textContent;
    expect(content).toContain("Welcome to insurance company");
  });
});
