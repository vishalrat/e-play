import { TestBed, inject } from '@angular/core/testing';

import { TheatreLayoutService } from './theatre-layout.service';

describe('TheatreLayoutService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TheatreLayoutService]
    });
  });

  it('should be created', inject([TheatreLayoutService], (service: TheatreLayoutService) => {
    expect(service).toBeTruthy();
  }));
});
