import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Panne } from '../models/panne';
import { Message } from '../models/message.model';
import { Besoin } from '../models/Besoin.model';
import { Computer } from '../models/computer.model';
import { Printer } from '../models/printer.model';
import { Teacher } from '../models/teacher.mode';
import { AuthService } from 'src/app/auth/services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class EnseignantService {
  constructor(private http:HttpClient, private authService:AuthService) { }

  apiUrl="http://localhost:8085/Recources-Managment/enseignant"; 
   


  getRequests(){
    const currentUser:Teacher = this.authService.getUser()

    return this.http.get<Message[]>(`${this.apiUrl}/liste-demandes/${currentUser.department}`) ; 
  }

  getBesoin(id:any){
    const currentUser:Teacher = this.authService.getUser()
    return this.http.get<Besoin>(`${this.apiUrl}/liste-demandes/besoin/${id}/${currentUser.id}`) ; 
  }
  findCumputers(){
    const currentUser:Teacher = this.authService.getUser()
   return this.http.get<Computer[]>(`${this.apiUrl}/liste-ordinateurs/${currentUser.id}`) ; 
  }
  findPrinters(){
    const currentUser:Teacher = this.authService.getUser()
    return this.http.get<Printer[]>(`${this.apiUrl}/liste-imprimantes/${currentUser.id}`) ; 
   }
  //  saveRequest(besoin:Besoin){
  //   const currentUser:Teacher = this.authService.getUser()
  //   besoin.enseignant_id = currentUser.id
  //    return this.http.post<Besoin>(`${this.apiUrl}/ajouter-demande`,besoin); 
  //  }
   savePane(panne:Panne){
    const currentUser:Teacher = this.authService.getUser()
    return this.http.post<Panne>(`${this.apiUrl}/signaler-panne/${currentUser.id}`,panne); 
   }
 
}
