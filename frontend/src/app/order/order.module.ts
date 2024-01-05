import {NgModule} from "@angular/core";
import {SharedModule} from "../shared/shared.module";
import {CommonModule} from "@angular/common";
import {OrderRoutingModule} from "./order-routing.module";
import {OrderMainPageComponent} from "./order-main-page/order-main-page.component";
import {OrderProductSelectionComponent} from "./order-product-selection/order-product-selection.component";
import {OrderSideBarComponent} from "./order-side-bar/order-side-bar.component";
import {OrderSummaryBarComponent} from "./order-summary-bar/order-summary-bar.component";

@NgModule({
  imports: [
    SharedModule,
    CommonModule,
    OrderRoutingModule
  ],
  declarations: [
    OrderMainPageComponent,
    OrderProductSelectionComponent,
    OrderSideBarComponent,
    OrderSummaryBarComponent
  ],
})
export class OrderModule {}
