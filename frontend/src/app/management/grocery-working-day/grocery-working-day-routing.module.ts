import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {
  GroceryWorkingDayOverviewComponent
} from "./grocery-working-day-overview/grocery-working-day-overview.component";
import {userFilterResolver} from "./resolver/user-filter.resolver";

const routes: Routes = [
  {
    path: '',
    component: GroceryWorkingDayOverviewComponent,
    resolve: [userFilterResolver]
  },
  { path: '**', redirectTo: ''},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class GroceryWorkingDayRoutingModule {}
