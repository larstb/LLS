import { ResolveFn } from '@angular/router';
import {map} from "rxjs/operators";
import {inject} from "@angular/core";
import {Store} from "@ngxs/store";
import {PortalStoreActions} from "../../../shared/store/portal-actions";
import LoadAllCategoriesForProduct = PortalStoreActions.LoadAllCategoriesForProduct;

export const categoriesFilterResolver: ResolveFn<boolean> = (route, state) => {
  const store = inject(Store)
  return store.dispatch(new LoadAllCategoriesForProduct()).pipe(map(categories => !!categories));
};
