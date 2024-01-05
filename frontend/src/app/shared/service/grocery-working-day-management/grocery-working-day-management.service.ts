import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ResponseWrapper} from "../../shared-model/responseWrapper";
import {ProductOverviewDTO} from "../../model/productOverviewDTO";
import {GroceryWorkingDayDetailDTO} from "../../model/groceryWorkingDayDetailDTO";
import {CreateGroceryWorkingDayDTO} from "../../model/createGroceryWorkingDayDTO";
import {UpdateGroceryWorkingDayDTO} from "../../model/updateGroceryWorkingDayDTO";

@Injectable({
  providedIn: 'root'
})
export class GroceryWorkingDayManagementService {
  private _baseUrl = 'https://localhost:9002/api/portal/grocery-working-day/'

  constructor(private httpClient: HttpClient) { }

  public loadAllGroceryWorkingDays(queryParams?: any | undefined): Observable<ResponseWrapper<GroceryWorkingDayDetailDTO>> {
    return this.httpClient.get<ResponseWrapper<ProductOverviewDTO>>(this._baseUrl, {params: queryParams});
  }

  public createGroceryWorkingDay(request: CreateGroceryWorkingDayDTO): Observable<GroceryWorkingDayDetailDTO> {
    return this.httpClient.post(this._baseUrl, request);
  }

  public updateGroceryWorkingDay(request: UpdateGroceryWorkingDayDTO): Observable<GroceryWorkingDayDetailDTO> {
    return this.httpClient.put(this._baseUrl, request);
  }
}
