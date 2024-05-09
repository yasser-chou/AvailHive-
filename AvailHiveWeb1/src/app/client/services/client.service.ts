import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import { UserStorageService } from '../../basic/services/storage/user-storage.service';

const BASIC_URL ="http://localhost:8080/"

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(private http: HttpClient, private userStorageService : UserStorageService) { }

  getAllAds(): Observable<any> {
    return this.http.get(BASIC_URL + `api/client/ads`,{
      headers: this.createAuthorizationHeader()
    })

  }

  searchAdByName(name:any): Observable<any> {
    return this.http.get(BASIC_URL + `api/client/search/${name}`,{
      headers: this.createAuthorizationHeader()
    })

  }

  createAuthorizationHeader(): HttpHeaders {
    let authHeaders: HttpHeaders = new HttpHeaders();// Ensure that the server expects JSON
    return authHeaders.set(
      'Authorization',
      'Bearer ' + this.userStorageService.getToken()
    )

  }
}
