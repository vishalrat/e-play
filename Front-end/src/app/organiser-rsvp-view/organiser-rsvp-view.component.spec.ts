import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrganiserRsvpViewComponent } from './organiser-rsvp-view.component';

describe('OrganiserRsvpViewComponent', () => {
  let component: OrganiserRsvpViewComponent;
  let fixture: ComponentFixture<OrganiserRsvpViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrganiserRsvpViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrganiserRsvpViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
