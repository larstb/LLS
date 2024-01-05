import { ResolveFn } from '@angular/router';
import {inject} from "@angular/core";
import {Store} from "@ngxs/store";
import {map} from "rxjs/operators";
import {PortalStoreActions} from "../../../shared/store/portal-actions";
import LoadAllUsersForGroceryWorkingDay = PortalStoreActions.LoadAllUsersForGroceryWorkingDay;

export const userFilterResolver: ResolveFn<boolean> = (route, state) => {
  const store = inject(Store)
  return store.dispatch(new LoadAllUsersForGroceryWorkingDay()).pipe(map(categories => !!categories));
};
