import { TestBed, inject } from '@angular/core/testing';

import { MovieEventService } from './movie-event.service';

describe('MovieEventService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MovieEventService]
    });
  });

  it('should be created', inject([MovieEventService], (service: MovieEventService) => {
    expect(service).toBeTruthy();
  }));
});
