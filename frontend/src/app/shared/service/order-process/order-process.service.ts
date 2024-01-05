import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ResponseWrapper} from "../../shared-model/responseWrapper";
import {ProductDetailDTO} from "../../model/productDetailDTO";
import {OrderDetailDTO} from "../../model/orderDetailDTO";
import {CreateOrderDTO} from "../../model/createOrderDTO";

@Injectable({
  providedIn: 'root'
})
export class OrderProcessService {

  private _baseUrl = 'https://localhost:9002/api/order/'

  constructor(private httpClient: HttpClient) { }

  public loadAllProductsForShop(queryParams?: any | undefined): Observable<ResponseWrapper<ProductDetailDTO>> {
    return this.httpClient.get<ResponseWrapper<ProductDetailDTO>>(this._baseUrl + 'products', {params: queryParams});
  }

  public loadAllOrdersForPayingUser(): Observable<ResponseWrapper<OrderDetailDTO>> {
    return this.httpClient.get<ResponseWrapper<OrderDetailDTO>>(this._baseUrl + 'paying-user/orders');
  }

  public loadTodayOrderForPayingUser(): Observable<OrderDetailDTO[]> {
    return this.httpClient.get<OrderDetailDTO[]>(this._baseUrl + 'today/orders');
  }

  public createOrderForCurrentCustomer(request: CreateOrderDTO): Observable<OrderDetailDTO> {
    return this.httpClient.post(this._baseUrl + 'today/orders', request);
  }
}
