import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Fournisseur } from '../models/fournisseur';



@Injectable({providedIn: 'root'})
export class ConsulterFournisseurService {
  private apiServerUrl = "http://localhost:8085/responsable/consulterFournisseur";

  constructor(private http: HttpClient){}
  

public findFournisseur(fournisseur: Fournisseur): Observable<Fournisseur> {
  return this.http.post<Fournisseur>(`${this.apiServerUrl}/find`, fournisseur)

}

  public getFournisseurs(): Observable<Fournisseur[]> {
    return this.http.get<Fournisseur[]>(`${this.apiServerUrl}/all2`);
  }

  public addFournisseur(fournisseur: Fournisseur): Observable<Fournisseur> {
    return this.http.post<Fournisseur>(`${this.apiServerUrl}/add`, fournisseur);
  }

  public updateFournisseur(fournisseur: Fournisseur): Observable<Fournisseur> {
    return this.http.put<Fournisseur>(`${this.apiServerUrl}/update`, fournisseur);
  }

  public deleteFournisseur(fournisseurId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/delete/${fournisseurId}`);
  }
}