import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Login } from 'src/app/core/models/login.model';
import { User } from 'src/app/features/models/user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }


  public setRoles(roles: []) {
    localStorage.setItem('roles', JSON.stringify(roles));
  }

  public getRoles(): [] {
    return JSON.parse(localStorage.getItem('roles') || '');
  }

  public setToken(jwtToken: string) {
    localStorage.setItem('jwtToken', jwtToken);
  }

  public getToken(): string | null {
    return localStorage.getItem('jwtToken');
  }

  public setUser(user:User){
    localStorage.setItem("user",JSON.stringify(user));
  }

  public getUser(){
    return JSON.parse(localStorage.getItem('user')||'');
  }

  public clear() {
    localStorage.clear();
  }


  public isLoggedIn() {
    return this.getRoles() && this.getToken();
  }
}
