import { ResolveFn } from '@angular/router';
import {inject} from "@angular/core";
import {Store} from "@ngxs/store";
import {map} from "rxjs/operators";
import {PortalStoreActions} from "../../../../../shared/store/portal-actions";
import LoadGroceryWorkingDayToday = PortalStoreActions.LoadGroceryWorkingDayToday;

export const groceryWorkingDayResolver: ResolveFn<boolean> = (route, state) => {
  const store = inject(Store)
  return store.dispatch(new LoadGroceryWorkingDayToday()).pipe(map(today => !!today));
};
