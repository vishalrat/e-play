import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieEventComponent } from './movie-event.component';

describe('MovieEventComponent', () => {
  let component: MovieEventComponent;
  let fixture: ComponentFixture<MovieEventComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MovieEventComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MovieEventComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
