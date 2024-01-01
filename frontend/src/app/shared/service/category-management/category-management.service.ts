import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ResponseWrapper} from "../../shared-model/responseWrapper";
import {CategoryOverviewDTO} from "../../model/categoryOverviewDTO";
import {CategoryDetailDTO} from "../../model/categoryDetailDTO";
import {CreateCategoryDTO} from "../../model/createCategoryDTO";
import {UpdateCategoryDTO} from "../../model/updateCategoryDTO";

@Injectable({
  providedIn: 'root'
})
export class CategoryManagementService {

  private _baseUrl = 'https://localhost:9002/api/portal/category/'

  constructor(private httpClient: HttpClient) { }

  public loadAllCategories(queryParams?: any | undefined): Observable<ResponseWrapper<CategoryOverviewDTO>> {
    return this.httpClient.get<ResponseWrapper<CategoryOverviewDTO>>(this._baseUrl, {params: queryParams});
  }

  public loadCategoryById(id: string | undefined): Observable<CategoryDetailDTO> {
    return this.httpClient.get(this._baseUrl + id);
  }

  public createCategory(request: CreateCategoryDTO): Observable<CategoryDetailDTO> {
    return this.httpClient.post(this._baseUrl, request);
  }

  public updateCategory(request: UpdateCategoryDTO): Observable<CategoryDetailDTO> {
    return this.httpClient.put(this._baseUrl, request);
  }
}
