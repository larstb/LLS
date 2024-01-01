import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { adminModGuard } from './admin-mod.guard';

describe('adminModGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => adminModGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
