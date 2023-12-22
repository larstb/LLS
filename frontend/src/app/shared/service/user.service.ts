import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {PortalUserDTO} from "../model/portalUserDTO";
import {HttpClient} from "@angular/common/http";
import {UserDetailDTO} from "../model/userDetailDTO";
import {UpdateUserDTO} from "../model/updateUserDTO";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private _baseUrl = 'https://localhost:9002/api/user/';

  constructor(private httpClient: HttpClient) { }

  public loadLoggedInUser(): Observable<PortalUserDTO> {
    return this.httpClient.get(this._baseUrl + 'current-user');
  }

  public loadUser(): Observable<UserDetailDTO> {
    return this.httpClient.get(this._baseUrl);
  }

  public updateUser(request: UpdateUserDTO): Observable<UserDetailDTO> {
    return this.httpClient.put(this._baseUrl, request);
  }
}
