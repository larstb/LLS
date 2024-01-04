import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ManagementDashboardComponent} from "./management-dashboard/management-dashboard.component";
import {UserOverviewComponent} from "./user/user-overview/user-overview.component";

const routes: Routes = [
  { path: 'dashboard', component: ManagementDashboardComponent },
  { path: 'user', component: UserOverviewComponent },
  {
    path: 'user',
    loadChildren: () => import('./user/user.module').then(m => m.UserModule),
  },
  {
    path: 'category',
    loadChildren: () => import('./category/category.module').then(m => m.CategoryModule),
  },
  {
    path: 'product',
    loadChildren: () => import('./product/product.module').then(m => m.ProductModule),
  },
  {
    path: 'grocery-working-day',
    loadChildren: () => import('./grocery-working-day/grocery-working-day.module').then(m => m.GroceryWorkingDayModule),
  },
  { path: '**', redirectTo: 'dashboard'},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ManagementRoutingModule {}
