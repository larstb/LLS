import { TestBed } from '@angular/core/testing';
import { ResolveFn } from '@angular/router';

import { portalUserResolver } from './portal-user.resolver';

describe('portalUserResolver', () => {
  const executeResolver: ResolveFn<boolean> = (...resolverParameters) => 
      TestBed.runInInjectionContext(() => portalUserResolver(...resolverParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeResolver).toBeTruthy();
  });
});
