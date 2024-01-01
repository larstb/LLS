import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ProductOverviewComponent} from "./product-overview/product-overview.component";
import {categoriesFilterResolver} from "./resolver/categories-filter.resolver";

const routes: Routes = [
  {
    path: '',
    component: ProductOverviewComponent,
    resolve: [categoriesFilterResolver],
  },
  { path: '**', redirectTo: ''},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ProductRoutingModule {}
