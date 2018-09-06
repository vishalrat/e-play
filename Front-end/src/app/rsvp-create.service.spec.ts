import { TestBed, inject } from '@angular/core/testing';

import { RsvpCreateService } from './rsvp-create.service';

describe('RsvpCreateService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RsvpCreateService]
    });
  });

  it('should be created', inject([RsvpCreateService], (service: RsvpCreateService) => {
    expect(service).toBeTruthy();
  }));
});
