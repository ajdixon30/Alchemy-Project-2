import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MangageMoviesComponent } from './mangage-movies.component';

describe('MangageMoviesComponent', () => {
  let component: MangageMoviesComponent;
  let fixture: ComponentFixture<MangageMoviesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MangageMoviesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MangageMoviesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
