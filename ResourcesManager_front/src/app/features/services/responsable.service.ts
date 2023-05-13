import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Computer } from '../models/computer.model';
import { Printer } from '../models/printer.model';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Affectation } from '../models/affectation.model';

@Injectable({
  providedIn: 'root'
})
export class ResponsableService {

  constructor(private http:HttpClient) {}

  apiUrl="http://localhost:8085/responsable"; 

  findComputers(): Observable<Computer[]>{
    return this.http.get<Computer[]>(`${this.apiUrl}/liste-ordinateurs`); 
   }

  findComputersByState(state: number): Observable<Computer[]>{
    return this.http.get<Computer[]>(`${this.apiUrl}/liste-ordinateurs/${state}`); 
   }

  findComputerById(id: number): Observable<Computer>{
    return this.http.get<Computer>(`${this.apiUrl}/ordinateur/${id}`); 
   }

   deleteComputer(id: number): void{
    this.http.delete<Computer>(`${this.apiUrl}/ordinateur/${id}`); 
   }

  findComputerByBarCode(barCode: number): Observable<Computer> {
    return this.findComputers().pipe(
      map((computers: Computer[]) => computers.find(computer => computer.barCode === barCode)),
      filter((computer: Computer | undefined): computer is Computer => !!computer),
    );
  }

  updateComputer(id: number, computer : Computer): Observable<Computer>{
    return this.http.put<Computer>(`${this.apiUrl}/ordinateur/${id}`, computer); 
   }
  
   
  findPrinters(): Observable<Printer[]>{
     return this.http.get<Printer[]>(`${this.apiUrl}/liste-imprimantes`) ; 
   }

  findPrintersBySate(state: number): Observable<Printer[]>{
     return this.http.get<Printer[]>(`${this.apiUrl}/liste-imprimantes/${state}`) ; 
   }

  findPrinterById(id: number): Observable<Printer>{
    return this.http.get<Printer>(`${this.apiUrl}/imprimante/${id}`) ; 
  }

  deletePrinter(id: number): void{
    this.http.delete<Printer>(`${this.apiUrl}/imprimante/${id}`) ; 
  }

  updatePrinter(id: number, printer : Printer): Observable<Printer>{
    return this.http.put<Printer>(`${this.apiUrl}/imprimante/${id}`,printer); 
  }
  
  findPrinterByBarCode(barCode: number): Observable<Printer> {
    return this.findPrinters().pipe(
      map((printers: Printer[]) => printers.find(printer => printer.barCode === barCode)),
      filter((printer: Printer | undefined): printer is Printer => !!printer),
    );
  }


  // lale
  addAffectation(affectation:Affectation) : Observable<Affectation>{
    return this.http.post<Affectation>(`${this.apiUrl}/add-affectation`, affectation); 
  }

  updateAffectation(affectation:Affectation) : Observable<Affectation>{
    return this.http.put<Affectation>(`${this.apiUrl}/update-affectation`, affectation); 
  }

  getAffectationByResourceId(resourceId:number) : Observable<Affectation>{
    return this.http.get<Affectation>(`${this.apiUrl}/affectation/${resourceId}`); 
  }


}
