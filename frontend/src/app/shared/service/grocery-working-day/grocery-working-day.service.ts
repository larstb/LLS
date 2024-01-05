import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {GroceryWorkingDayDetailDTO} from "../../model/groceryWorkingDayDetailDTO";

@Injectable({
  providedIn: 'root'
})
export class GroceryWorkingDayService {

  private _baseUrl = 'https://localhost:9002/api/grocery-working-day/';

  constructor(private httpClient: HttpClient) { }

  public loadOrCreateGroceryWorkingDayToday(): Observable<GroceryWorkingDayDetailDTO> {
    return this.httpClient.get(this._baseUrl + 'today');
  }

  public addLoggedInUserToGoingUsers(): Observable<GroceryWorkingDayDetailDTO> {
    return this.httpClient.post(this._baseUrl + 'add/going-users/', {});
  }

  public removeLoggedInUserToGoingUsers(): Observable<GroceryWorkingDayDetailDTO> {
    return this.httpClient.post(this._baseUrl + 'remove/going-users/', {});
  }

  public setCurrentLoggedInUserAsPayingUser(): Observable<GroceryWorkingDayDetailDTO> {
    return this.httpClient.post(this._baseUrl + 'add/paying-users/', {});
  }

  public removeCurrentLoggedInUserAsPayingUser(): Observable<GroceryWorkingDayDetailDTO> {
    return this.httpClient.post(this._baseUrl + 'remove/paying-users/', {});
  }
}
