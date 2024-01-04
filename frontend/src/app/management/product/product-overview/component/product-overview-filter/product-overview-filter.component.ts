import {Component, EventEmitter, Input, Output} from '@angular/core';
import {
  AbstractMatDataSourceService
} from "../../../../../shared/service/abstract-material-datasource/abstract-mat-data-source.service";
import {ProductOverviewDTO} from "../../../../../shared/model/productOverviewDTO";
import {Select} from "@ngxs/store";
import {PortalStoreState} from "../../../../../shared/store/portal-store-states";
import {Observable} from "rxjs";
import {ProductOverviewFilter} from "../../../../../shared/shared-model/filter/product-overview-filter";
import {CategoryDetailDTO} from "../../../../../shared/model/categoryDetailDTO";

@Component({
  selector: 'app-product-overview-filter',
  templateUrl: './product-overview-filter.component.html',
  styleUrl: './product-overview-filter.component.css'
})
export class ProductOverviewFilterComponent {

  @Select(PortalStoreState.categories)
  public categories$?: Observable<CategoryDetailDTO[]>;

  @Input()
  public datasource!: AbstractMatDataSourceService<ProductOverviewDTO>;

  @Output()
  public filterChange: EventEmitter<ProductOverviewFilter> = new EventEmitter<ProductOverviewFilter>();

}
