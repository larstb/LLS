import { TestBed } from '@angular/core/testing';

import { CategoryOverviewTableDataSourceService } from './category-overview-table-data-source.service';

describe('CategoryOverviewTableDataSourceService', () => {
  let service: CategoryOverviewTableDataSourceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CategoryOverviewTableDataSourceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
