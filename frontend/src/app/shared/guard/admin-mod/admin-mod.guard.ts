import { CanActivateFn } from '@angular/router';
import {inject} from "@angular/core";
import {Store} from "@ngxs/store";

export const adminModGuard: CanActivateFn = (route, state) => {
  const store = inject(Store);
  const roles = store.snapshot().portalStore.portalUser?.roles || [];
  return !!roles.includes('ADMIN') || !!roles.includes('MOD');
};
