import { TestBed } from '@angular/core/testing';
import { ResolveFn } from '@angular/router';

import { userFilterResolver } from './user-filter.resolver';

describe('userFilterResolver', () => {
  const executeResolver: ResolveFn<boolean> = (...resolverParameters) => 
      TestBed.runInInjectionContext(() => userFilterResolver(...resolverParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeResolver).toBeTruthy();
  });
});
