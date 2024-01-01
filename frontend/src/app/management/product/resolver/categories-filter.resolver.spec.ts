import { TestBed } from '@angular/core/testing';
import { ResolveFn } from '@angular/router';

import { categoriesFilterResolver } from './categories-filter.resolver';

describe('categoriesFilterResolver', () => {
  const executeResolver: ResolveFn<boolean> = (...resolverParameters) =>
      TestBed.runInInjectionContext(() => categoriesFilterResolver(...resolverParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeResolver).toBeTruthy();
  });
});
