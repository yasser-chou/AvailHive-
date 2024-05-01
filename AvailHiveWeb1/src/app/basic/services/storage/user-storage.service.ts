import { Injectable } from '@angular/core';

const TOKEN = 's_token';
const USER = 's_user';

@Injectable({
  providedIn: 'root'
})
export class UserStorageService {

  constructor() { }

  public saveToken(token: string): void {
    window.localStorage.removeItem(TOKEN);
    window.localStorage.setItem(TOKEN, token);
  }

  public getToken(): string | null {
    return localStorage.getItem(TOKEN);
  }

  public saveUser(user: any): void {
    window.localStorage.removeItem(USER);
    window.localStorage.setItem(USER, JSON.stringify(user));
  }

  public getUser(): any {
    const userString = localStorage.getItem(USER);
    return userString ? JSON.parse(userString) : null;
  }

  public getUserId(): string {
    const user = this.getUser();
    return user ? user.userId : '';
  }

  public getUserRole(): string {
    const user = this.getUser();
    return user ? user.role : '';
  }

  public isClientLoggedIn(): boolean {
    const token = this.getToken();
    const role = this.getUserRole();
    return token !== null && role === 'CLIENT';
  }

  public isCompanyLoggedIn(): boolean {
    const token = this.getToken();
    const role = this.getUserRole();
    return token !== null && role === 'COMPANY';
  }

  public signOut(): void {
    window.localStorage.removeItem(TOKEN);
    window.localStorage.removeItem(USER);
  }
}
