import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieTheatreListComponent } from './movie-theatre-list.component';

describe('MovieTheatreListComponent', () => {
  let component: MovieTheatreListComponent;
  let fixture: ComponentFixture<MovieTheatreListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MovieTheatreListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MovieTheatreListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
