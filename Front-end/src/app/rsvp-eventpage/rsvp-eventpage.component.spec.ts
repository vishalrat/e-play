import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RsvpEventpageComponent } from './rsvp-eventpage.component';

describe('RsvpEventpageComponent', () => {
  let component: RsvpEventpageComponent;
  let fixture: ComponentFixture<RsvpEventpageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RsvpEventpageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RsvpEventpageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
