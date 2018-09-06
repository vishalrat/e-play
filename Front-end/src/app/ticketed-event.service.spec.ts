import { TestBed, inject } from '@angular/core/testing';

import { TicketedEventService } from './ticketed-event.service';

describe('TicketedEventService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TicketedEventService]
    });
  });

  it('should be created', inject([TicketedEventService], (service: TicketedEventService) => {
    expect(service).toBeTruthy();
  }));
});
