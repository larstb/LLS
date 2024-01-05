import {Component, Input} from '@angular/core';
import {Select} from "@ngxs/store";
import {PortalStoreState} from "../../shared/store/portal-store-states";
import {Observable} from "rxjs";
import {CategoryDetailDTO} from "../../shared/model/categoryDetailDTO";
import {
  AbstractMatDataSourceService
} from "../../shared/service/abstract-material-datasource/abstract-mat-data-source.service";
import {ProductDetailDTO} from "../../shared/model/productDetailDTO";
import {ProductOverviewFilter} from "../../shared/shared-model/filter/product-overview-filter";

@Component({
  selector: 'app-order-side-bar',
  templateUrl: './order-side-bar.component.html',
  styleUrl: './order-side-bar.component.css'
})
export class OrderSideBarComponent {

  @Input()
  public dataSource!: AbstractMatDataSourceService<ProductDetailDTO>;

  @Select(PortalStoreState.categories)
  public categories$?: Observable<CategoryDetailDTO[]>;

  public filter: ProductOverviewFilter = {};

  public mergeFilter(filter: ProductOverviewFilter | undefined): void {
    this.filter = Object.assign({}, this.filter, filter);
  }

}
