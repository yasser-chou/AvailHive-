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

  postEmployer(employerDTO:any): Observable<any>{
    const userId = this.userStorageService.getUserId();
    return this.http.post(BASIC_URL + `api/company/employer/${userId}`, employerDTO, {
      headers: this.createAuthorizationHeader()
    })
  }

  getAllAdsByUserId(): Observable<any> {
    const userId = this.userStorageService.getUserId();
    return this.http.get(BASIC_URL + `api/company/ads/${userId}`,{
      headers: this.createAuthorizationHeader()
    })

  }

  getAllEmployersByUserId(): Observable<any>{
    const userId =this.userStorageService.getUserId();
    return this.http.get(BASIC_URL+ `api/company/employers/${userId}`,{
      headers: this.createAuthorizationHeader()
    })
  }

  getEmployerById(employerId:any): Observable<any> {
    return this.http.get(BASIC_URL+ `api/company/empprofile/${employerId}`,{
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

  updateEmployer(employerId:any, employerDTO:any):Observable<any> {
    return this.http.put(BASIC_URL + `api/company/employer/${employerId}`, employerDTO, {
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

  deleteEmployer(employerId:any):Observable<any> {
    return this.http.delete(BASIC_URL + `api/company/employer/${employerId}`, {
      headers: this.createAuthorizationHeader()
    })
  }

  getAllAdsBookings(): Observable<any> {
    const companyId = this.userStorageService.getUserId()
    return this.http.get(BASIC_URL + `api/company/bookings/${companyId}`,{
      headers: this.createAuthorizationHeader()
    })

  }

  changeBookingStatus(bookingId:number,status:string): Observable<any>{
    return this.http.get(BASIC_URL + `api/company/booking/${bookingId}/${status}`,{
      headers: this.createAuthorizationHeader()
    })
  }



}
