import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {OrderMainPageComponent} from "./order-main-page/order-main-page.component";

const routes: Routes = [
  { path: '', component: OrderMainPageComponent },
  { path: '**', redirectTo: ''},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class OrderRoutingModule {}
