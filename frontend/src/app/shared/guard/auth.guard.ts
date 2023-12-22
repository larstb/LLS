import { CanActivateFn } from '@angular/router';
import {inject} from "@angular/core";
import {KeycloakService} from "keycloak-angular";

export const authGuard: CanActivateFn = (route, state) => {
  const keyCloakService = inject(KeycloakService);
  return keyCloakService.isLoggedIn()
    ? true
    : keyCloakService
      .login()
      .then(() => true)
      .catch(() => false);
};
