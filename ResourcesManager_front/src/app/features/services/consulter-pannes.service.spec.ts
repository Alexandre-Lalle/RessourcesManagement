import { TestBed } from '@angular/core/testing';

import { ConsulterReportService } from './consulter-pannes.service';

describe('ConsulterPannesService', () => {
  let service:ConsulterReportService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ConsulterReportService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
