import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RsvpInvitationComponent } from './rsvp-invitation.component';

describe('RsvpInvitationComponent', () => {
  let component: RsvpInvitationComponent;
  let fixture: ComponentFixture<RsvpInvitationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RsvpInvitationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RsvpInvitationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
