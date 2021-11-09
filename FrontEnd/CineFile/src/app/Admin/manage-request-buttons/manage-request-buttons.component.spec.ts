import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageRequestButtonsComponent } from './manage-request-buttons.component';

describe('ManageRequestButtonsComponent', () => {
  let component: ManageRequestButtonsComponent;
  let fixture: ComponentFixture<ManageRequestButtonsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageRequestButtonsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageRequestButtonsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
