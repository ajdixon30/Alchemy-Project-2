import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MangageRequestsComponent } from './mangage-requests.component';

describe('MangageRequestsComponent', () => {
  let component: MangageRequestsComponent;
  let fixture: ComponentFixture<MangageRequestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MangageRequestsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MangageRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
