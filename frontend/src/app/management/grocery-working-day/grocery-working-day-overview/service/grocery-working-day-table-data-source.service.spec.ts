import { TestBed } from '@angular/core/testing';

import { GroceryWorkingDayTableDataSourceService } from './grocery-working-day-table-data-source.service';

describe('GroceryWorkingDayTableDataSourceService', () => {
  let service: GroceryWorkingDayTableDataSourceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GroceryWorkingDayTableDataSourceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
