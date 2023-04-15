import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Login } from 'src/app/core/models/login.model';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  PATH_OF_API = "http://localhost:8085/Resource-Managment";
  requestHeader = new HttpHeaders(
    { "No-Auth": "True" ,'content-type': 'application/json'}
  );

  constructor(private http: HttpClient, private authService:AuthService) { }


  public login(login: Login): Observable<any> {
    let resp = this.http.post(this.PATH_OF_API + "/auth/login", JSON.stringify(login), { headers: this.requestHeader });
    return resp;
  }


  public roleMatch(allowedRoles:any): boolean {
    let isMatch = false;
    const userRoles: any = this.authService.getRoles();

    if (userRoles != null && userRoles) {
      for (let i = 0; i < userRoles.length; i++) {
        for (let j = 0; j < allowedRoles.length; j++) {
          if (userRoles[i].name === allowedRoles[j]) {
            isMatch = true;
            return isMatch;
          } else {
            return isMatch;
          }
        }
      }
    }

    return false;
  }
}
