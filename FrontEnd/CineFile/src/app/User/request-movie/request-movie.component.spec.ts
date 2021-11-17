import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestMovieComponent } from './request-movie.component';

describe('RequestMovieComponent', () => {
  let component: RequestMovieComponent;
  let fixture: ComponentFixture<RequestMovieComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RequestMovieComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RequestMovieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
