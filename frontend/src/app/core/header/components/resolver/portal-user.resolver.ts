import { ResolveFn } from '@angular/router';
import {inject} from "@angular/core";
import {Store} from "@ngxs/store";
import {PortalStoreActions} from "../../../../shared/store/portal-actions";
import LoadPortalUser = PortalStoreActions.LoadPortalUser;
import {map} from "rxjs/operators";

export const portalUserResolver: ResolveFn<boolean> = (route, state) => {
  const store = inject(Store)
  return store.dispatch(new LoadPortalUser()).pipe(map(user => !!user));
};
