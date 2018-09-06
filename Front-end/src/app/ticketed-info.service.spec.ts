import { TestBed, inject } from '@angular/core/testing';

import { TicketedInfoService } from './ticketed-info.service';

describe('TicketedInfoService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TicketedInfoService]
    });
  });

  it('should be created', inject([TicketedInfoService], (service: TicketedInfoService) => {
    expect(service).toBeTruthy();
  }));
});
