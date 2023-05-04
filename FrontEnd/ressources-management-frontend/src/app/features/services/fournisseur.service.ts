import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Offre } from '../models/offre';
import { Soumission } from '../models/soumission';

@Injectable({
  providedIn: 'root'
})
export class FournisseurService {
  apiUrl="http://localhost:8085/fournisseur"; 

  constructor(private http:HttpClient) { }
  getOffres(){
    return this.http.get<Offre[]>(`${this.apiUrl}/liste-offres`) ; 
  }
  getSoumissions(){
    return this.http.get<Soumission[]>(`${this.apiUrl}/liste-soumissions`) ; 
  }
  // findCumputers(id:any){
  //  return this.http.get<Ordinateur[]>(`${this.apiUrl}/liste-ordinateurs/${id}`) ; 
  // }
  // findPrinters(id:any){
  //   return this.http.get<Imprimante[]>(`${this.apiUrl}/liste-imprimantes/${id}`) ; 
  //  }
   saveSoumission(soumission:Soumission){
     return this.http.post<Soumission>(`${this.apiUrl}/ajouter-soumission`,soumission); 
   }
  //  savePane(panne:Panne){
  //   return this.http.post<Panne>(`${this.apiUrl}/signaler-panne`,panne); 
  //  }
}
