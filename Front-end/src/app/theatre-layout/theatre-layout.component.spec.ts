import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TheatreLayoutComponent } from './theatre-layout.component';

describe('TheatreLayoutComponent', () => {
  let component: TheatreLayoutComponent;
  let fixture: ComponentFixture<TheatreLayoutComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TheatreLayoutComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TheatreLayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
