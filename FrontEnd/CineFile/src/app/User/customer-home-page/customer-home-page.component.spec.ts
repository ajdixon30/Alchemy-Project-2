import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerHomePageComponent } from './customer-home-page.component';

describe('CustomerHomePageComponent', () => {
  let component: CustomerHomePageComponent;
  let fixture: ComponentFixture<CustomerHomePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerHomePageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerHomePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
