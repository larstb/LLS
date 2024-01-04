import { TestBed } from '@angular/core/testing';

import { ProductOverviewTableDataSourceService } from './product-overview-table-data-source.service';

describe('ProductOverviewTableDataSourceService', () => {
  let service: ProductOverviewTableDataSourceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProductOverviewTableDataSourceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
