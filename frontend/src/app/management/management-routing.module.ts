import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ManagementDashboardComponent} from "./management-dashboard/management-dashboard.component";
import {UserOverviewComponent} from "./user/user-overview/user-overview.component";
import {CategoryOverviewComponent} from "./category/category-overview/category-overview.component";

const routes: Routes = [
  { path: 'dashboard', component: ManagementDashboardComponent },
  { path: 'user', component: UserOverviewComponent },
  { path: 'category', component: CategoryOverviewComponent },
  { path: '**', redirectTo: 'dashboard'},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ManagementRoutingModule {}
