import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ResponseWrapper} from "../../shared-model/responseWrapper";
import {ProductOverviewDTO} from "../../model/productOverviewDTO";
import {ProductDetailDTO} from "../../model/productDetailDTO";
import {CreateProductDTO} from "../../model/createProductDTO";
import {UpdateProductDTO} from "../../model/updateProductDTO";

@Injectable({
  providedIn: 'root'
})
export class ProductManagementService {

  private _baseUrl = 'https://localhost:9002/api/portal/product/'

  constructor(private httpClient: HttpClient) { }

  public loadAllProducts(queryParams?: any | undefined): Observable<ResponseWrapper<ProductOverviewDTO>> {
    return this.httpClient.get<ResponseWrapper<ProductOverviewDTO>>(this._baseUrl, {params: queryParams});
  }

  public loadProductById(id: string | undefined): Observable<ProductDetailDTO> {
    return this.httpClient.get(this._baseUrl + id);
  }

  public createProduct(request: CreateProductDTO): Observable<ProductDetailDTO> {
    return this.httpClient.post(this._baseUrl, request);
  }

  public updateProduct(request: UpdateProductDTO): Observable<ProductDetailDTO> {
    return this.httpClient.put(this._baseUrl, request);
  }
}
