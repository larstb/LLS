import {NgModule} from "@angular/core";
import {CategoryRoutingModule} from "./category-routing.module";
import {CommonModule} from "@angular/common";
import {CategoryOverviewComponent} from "./category-overview/category-overview.component";
import {
  CategoryOverviewTableComponent
} from "./category-overview/component/category-overview-table/category-overview-table.component";
import {CategoryDetailDialogComponent} from "./category-detail-dialog/category-detail-dialog.component";
import {SharedModule} from "../../shared/shared.module";

@NgModule({
  imports: [
    SharedModule,
    CommonModule,
    CategoryRoutingModule
  ],
  declarations: [
    CategoryOverviewComponent,
    CategoryOverviewTableComponent,
    CategoryDetailDialogComponent
  ],
})
export class CategoryModule {}
