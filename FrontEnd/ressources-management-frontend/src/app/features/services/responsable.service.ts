import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Computer } from '../models/computer.model';
import { Printer } from '../models/printer.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ResponsableService {

  constructor(private http:HttpClient) {}

  apiUrl="http://localhost:8085/responsable"; 

  findComputers(): Observable<Computer[]>{
    return this.http.get<Computer[]>(`${this.apiUrl}/liste-ordinateurs`); 
   }
   
  findPrinters(): Observable<Printer[]>{
     return this.http.get<Printer[]>(`${this.apiUrl}/liste-imprimantes`) ; 
   }



}
