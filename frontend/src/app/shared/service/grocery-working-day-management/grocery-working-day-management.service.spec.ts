import { TestBed } from '@angular/core/testing';

import { GroceryWorkingDayManagementService } from './grocery-working-day-management.service';

describe('GroceryWorkingDayManagementService', () => {
  let service: GroceryWorkingDayManagementService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GroceryWorkingDayManagementService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
