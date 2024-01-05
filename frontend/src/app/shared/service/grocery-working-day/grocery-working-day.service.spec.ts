import { TestBed } from '@angular/core/testing';

import { GroceryWorkingDayService } from './grocery-working-day.service';

describe('GroceryWorkingDayService', () => {
  let service: GroceryWorkingDayService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GroceryWorkingDayService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
