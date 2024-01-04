import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {ResponseWrapper} from "../../../../shared/shared-model/responseWrapper";
import {UserOverviewDTO} from "../../../../shared/model/userOverviewDTO";
import {ProductManagementService} from "../../../../shared/service/product-management/product-management.service";
import {
  AbstractMatDataSourceService
} from "../../../../shared/service/abstract-material-datasource/abstract-mat-data-source.service";
import {ProductOverviewDTO} from "../../../../shared/model/productOverviewDTO";

@Injectable({
  providedIn: 'root'
})
export class ProductOverviewTableDataSourceService extends AbstractMatDataSourceService<ProductOverviewDTO>{

  constructor(private productManagementService: ProductManagementService) {
    super();
  }

  filter$(queryParams: any): Observable<ResponseWrapper<UserOverviewDTO>> {
    return this.productManagementService.loadAllProducts(queryParams);
  }
}
