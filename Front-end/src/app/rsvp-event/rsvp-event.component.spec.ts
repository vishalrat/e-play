import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RsvpEventComponent } from './rsvp-event.component';

describe('RsvpEventComponent', () => {
  let component: RsvpEventComponent;
  let fixture: ComponentFixture<RsvpEventComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RsvpEventComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RsvpEventComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
