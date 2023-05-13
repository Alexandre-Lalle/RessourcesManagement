import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs';
import { Teacher } from '../../models/teacher.mode';
import { ComputerDemand } from '../../models/computer-demand.model';
import { Demand } from '../../models/demand.model';
import { PrinterDemand } from '../../models/printer-demad.model';
import { AuthService } from 'src/app/auth/services/auth.service';
import { User } from '../../models/user.model';
import { Director } from '../../models/director.model';


@Injectable({
  providedIn: 'root'
})
export class DemandsService {
  APIUrl = "http://localhost:8085/Recources-Managment";

  constructor(private http: HttpClient, private authService: AuthService) { }

  getDemand(id: number): Observable<any> {
    return this.http.get<any>(`${this.APIUrl}/demands/` + id)
  }

  getAllDemands(): Observable<any> {
    const currentUser: Director = this.authService.getUser();
    console.log("the director : ", currentUser);
    return this.http.get(`${this.APIUrl}/demands/department/` + currentUser.department);
  }

  addComputerDemand(demand: ComputerDemand): Observable<boolean> {
    const headers = { 'content-type': 'application/json' };
    return this.http.post<boolean>(`${this.APIUrl}/computer/demands`, JSON.stringify(demand), { 'headers': headers });
  }

  addPrinterDemand(demand: PrinterDemand): Observable<boolean> {
    const headers = { 'content-type': 'application/json' };
    return this.http.post<boolean>(`${this.APIUrl}/printer/demands`, JSON.stringify(demand), { 'headers': headers });
  }

  updateDemand() {

  }

  deleteDemand(demand: Demand): Observable<boolean> {
    return this.http.delete<boolean>(`${this.APIUrl}/demands/` + demand.resource.id);
  }

  sendUnavailableDemands(): Observable<boolean> {
    const headers = { 'content-type': 'application/json' };
    const currentUser: Director = this.authService.getUser()
    return this.http.post<boolean>(`${this.APIUrl}/send-demands`, currentUser.department, { 'headers': headers })
  }

  getDemandsPage() {

  }

  getDemandsPageBySearchKey() {

  }

  getTeachers(department: string): Observable<any> {
    return this.http.get(`${this.APIUrl}/teachers/` + department);
  }

  getTeacherMails(department: string): Observable<any> {
    return this.http.get(`${this.APIUrl}/teachers/mails/` + department);
  }

  getDepartments(): Observable<any> {
    return this.http.get("http://localhost:8085/Recources-Managment/departments");
  }

}
