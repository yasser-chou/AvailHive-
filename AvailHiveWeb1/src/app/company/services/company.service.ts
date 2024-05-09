import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { UserStorageService } from '../../basic/services/storage/user-storage.service';

const BASIC_URL = "http://localhost:8080/";

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  constructor(private http: HttpClient, private userStorageService: UserStorageService) {
  }

  postAd(adDTO: any): Observable<any> {
    const userId = this.userStorageService.getUserId();
    return this.http.post(BASIC_URL + `api/company/ad/${userId}`, adDTO, {
      headers: this.createAuthorizationHeader()
    })

  }

  getAllAdsByUserId(): Observable<any> {
    const userId = this.userStorageService.getUserId();
    return this.http.get(BASIC_URL + `api/company/ads/${userId}`,{
      headers: this.createAuthorizationHeader()
    })

  }

  getAdById(adId:any): Observable<any> {
    return this.http.get(BASIC_URL + `api/company/ad/${adId}`,{
      headers: this.createAuthorizationHeader()
    })

  }

  updateAd(adId:any, adDTO:any):Observable<any> {
    return this.http.put(BASIC_URL + `api/company/ad/${adId}`, adDTO, {
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

  deleteAd(adId:any):Observable<any> {
    return this.http.delete(BASIC_URL + `api/company/ad/${adId}`, {
      headers: this.createAuthorizationHeader()
    })
  }

}
