import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Panne } from '../models/panne';
import { Message } from '../models/message.model';
import { Besoin } from '../models/Besoin.model';
import { Computer } from '../models/computer.model';
import { Printer } from '../models/printer.model';

@Injectable({
  providedIn: 'root'
})
export class EnseignantService {
  constructor(private http:HttpClient) { }

  apiUrl="http://localhost:8085/enseignant"; 
   


  getRequests(id:any){
    return this.http.get<Message[]>(`${this.apiUrl}/liste-demandes/${id}`) ; 
  }

  getBesoin(id:any){
    return this.http.get<Besoin>(`${this.apiUrl}/liste-demandes/besoin/${id}`) ; 
  }
  findCumputers(){
   return this.http.get<Computer[]>(`${this.apiUrl}/liste-ordinateurs`) ; 
  }
  findPrinters(){
    return this.http.get<Printer[]>(`${this.apiUrl}/liste-imprimantes`) ; 
   }
   saveRequest(besoin:Besoin){
     return this.http.post<Besoin>(`${this.apiUrl}/ajouter-demande`,besoin); 
   }
   savePane(panne:Panne){
    return this.http.post<Panne>(`${this.apiUrl}/signaler-panne`,panne); 
   }
 
}
