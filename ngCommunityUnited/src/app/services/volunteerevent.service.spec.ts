import { TestBed } from '@angular/core/testing';

import { VolunteereventService } from './volunteerevent.service';

describe('VolunteereventService', () => {
  let service: VolunteereventService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VolunteereventService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
