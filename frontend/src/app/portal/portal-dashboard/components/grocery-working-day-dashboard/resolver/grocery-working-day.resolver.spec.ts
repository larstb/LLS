import { TestBed } from '@angular/core/testing';
import { ResolveFn } from '@angular/router';

import { groceryWorkingDayResolver } from './grocery-working-day.resolver';

describe('groceryWorkingDayResolver', () => {
  const executeResolver: ResolveFn<boolean> = (...resolverParameters) => 
      TestBed.runInInjectionContext(() => groceryWorkingDayResolver(...resolverParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeResolver).toBeTruthy();
  });
});
