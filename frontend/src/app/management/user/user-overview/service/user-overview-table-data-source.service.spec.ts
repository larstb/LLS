import { TestBed } from '@angular/core/testing';

import { UserOverviewTableDataSourceService } from './user-overview-table-data-source.service';

describe('UserOverviewTableDataSourceService', () => {
  let service: UserOverviewTableDataSourceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserOverviewTableDataSourceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
