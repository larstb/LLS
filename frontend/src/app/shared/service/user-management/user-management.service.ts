import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserDetailDTO} from "../../model/userDetailDTO";
import {CreateUserDTO} from "../../model/createUserDTO";
import {UpdateUserDTO} from "../../model/updateUserDTO";
import {ResponseWrapper} from "../../shared-model/responseWrapper";
import {UserOverviewDTO} from "../../model/userOverviewDTO";

@Injectable({
  providedIn: 'root'
})
export class UserManagementService {

  private _baseUrl = 'https://localhost:9002/api/portal/user/'

  constructor(private httpClient: HttpClient) { }

  public loadAllUsers(queryParams?: any | undefined): Observable<ResponseWrapper<UserOverviewDTO>> {
    return this.httpClient.get<ResponseWrapper<UserOverviewDTO>>(this._baseUrl, {params: queryParams});
  }

  public loadUserById(id: string | undefined): Observable<UserDetailDTO> {
    return this.httpClient.get(this._baseUrl + id);
  }

  public createUser(request: CreateUserDTO): Observable<UserDetailDTO> {
    return this.httpClient.post(this._baseUrl, request);
  }

  public updateUser(request: UpdateUserDTO): Observable<UserDetailDTO> {
    return this.httpClient.put(this._baseUrl, request);
  }
}
