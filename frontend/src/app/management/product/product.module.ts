import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {SharedModule} from "../../shared/shared.module";
import {ProductRoutingModule} from "./product-routing.module";
import {ProductOverviewComponent} from "./product-overview/product-overview.component";
import {
  ProductOverviewTableComponent
} from "./product-overview/component/product-overview-table/product-overview-table.component";
import {ProductDetailDialogComponent} from "./product-detail-dialog/product-detail-dialog.component";
import {
  ProductOverviewFilterComponent
} from "./product-overview/component/product-overview-filter/product-overview-filter.component";

@NgModule({
  imports: [
    SharedModule,
    CommonModule,
    ProductRoutingModule
  ],
  declarations: [
    ProductOverviewComponent,
    ProductOverviewTableComponent,
    ProductOverviewFilterComponent,
    ProductDetailDialogComponent,
  ],
})
export class ProductModule {}
