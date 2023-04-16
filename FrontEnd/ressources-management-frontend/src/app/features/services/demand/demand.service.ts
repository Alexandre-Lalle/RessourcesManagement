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

  constructor(private http: HttpClient, private authService:AuthService) { }

  getDemand(id: number): Observable<any> {
    return this.http.get<any>("http://localhost:8085/Recources-Managment/demands/" + id)
  }

  getAllDemands(): Observable<any> {
    const currentUser:Director = this.authService.getUser();
    console.log("the director : ",currentUser);
    return this.http.get("http://localhost:8085/Recources-Managment/demands/department/" + currentUser.department);
  }

  addComputerDemand(demand: ComputerDemand): Observable<boolean> {
    const headers = { 'content-type': 'application/json' };
    return this.http.post<boolean>("http://localhost:8085/Recources-Managment/computer/demands", JSON.stringify(demand), { 'headers': headers });
  }

  addPrinterDemand(demand: PrinterDemand): Observable<boolean> {
    const headers = { 'content-type': 'application/json' };
    return this.http.post<boolean>("http://localhost:8085/Recources-Managment/printer/demands", JSON.stringify(demand), { 'headers': headers });
  }

  updateDemand() {

  }

  deleteDemand(demand:Demand):Observable<boolean> {
    return this.http.delete<boolean>("http://localhost:8085/Recources-Managment/demands/"+demand.resource.id);
  }

  getDemandsPage() {

  }

  getDemandsPageBySearchKey() {

  }

  getTeachers(department: string): Observable<any> {
    return this.http.get("http://localhost:8085/Recources-Managment/teachers/" + department);
  }

  getTeacherMails(department: string): Observable<any> {
    return this.http.get("http://localhost:8085/Recources-Managment/teachers/mails/" + department);
  }
}
