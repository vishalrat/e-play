import { TestBed, inject } from '@angular/core/testing';

import { RsvpInvitationService } from './rsvp-invitation.service';

describe('RsvpInvitationService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RsvpInvitationService]
    });
  });

  it('should be created', inject([RsvpInvitationService], (service: RsvpInvitationService) => {
    expect(service).toBeTruthy();
  }));
});
