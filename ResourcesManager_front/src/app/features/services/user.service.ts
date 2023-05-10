import { Injectable, Provider } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Offre } from '../models/offre';
import { User } from '../models/user.model';
import { Teacher } from '../models/teacher.mode';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  apiUrlOffre = "http://localhost:8085/Recources-Managment/enseignant";
  constructor(private http: HttpClient) { }

  public findAll() {
    return this.http.get<Teacher[]>(this.apiUrlOffre + "/allUser");
  }

  public deleteUser(id: number) {
    return this.http.delete<boolean>(this.apiUrlOffre + "/deleteUser/" + id);
  }

  public addUser(user: Teacher) {
    return this.http.post<Teacher>(this.apiUrlOffre + "/createUser", user);
  }

  // updateUser(user:User){
  //   console.log(user);
  //   return this.http.put<Offre>(`${this.apiUrlOffre}`,user);
  // }

  registerProvider(provider: Provider): Observable<boolean> {
    const headers = { 'content-type': 'application/json' }
    return this.http.post<boolean>(this.apiUrlOffre + "/providers", JSON.stringify(provider), { 'headers': headers });
  }
}
