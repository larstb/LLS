import { Injectable } from '@angular/core';
import {
  AbstractMatDataSourceService
} from "../../shared/service/abstract-material-datasource/abstract-mat-data-source.service";
import {ProductDetailDTO} from "../../shared/model/productDetailDTO";
import {Observable} from "rxjs";
import {ResponseWrapper} from "../../shared/shared-model/responseWrapper";
import {DatePipe} from "@angular/common";
import {OrderProcessService} from "../../shared/service/order-process/order-process.service";

@Injectable({
  providedIn: 'root'
})
export class OrderProcessDataSourceService extends AbstractMatDataSourceService<ProductDetailDTO> {

  constructor(datePipe: DatePipe, private orderProcessService: OrderProcessService) {
    super(datePipe);
  }

  filter$(queryParams: any): Observable<ResponseWrapper<ProductDetailDTO>> {
    return this.orderProcessService.loadAllProductsForShop(queryParams);
  }
}
