import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Report } from "../models/report";
import { DemandeRetour } from '../models/demandeRetour';

@Injectable({
    providedIn: 'root'
  })
export class ConsulterReportService{
    private apiServerUrl = "http://localhost:8085/responsable/consulterPannes";
    constructor(private http: HttpClient){}

    public getReports(): Observable<Report[]> {
        return this.http.get<Report[]>(`${this.apiServerUrl}/reportInfo`);
    }
    public getDemandes(): Observable<DemandeRetour[]> {
      return this.http.get<DemandeRetour[]>(`${this.apiServerUrl}/allDemandes`);
  }
    public addDemandeRetour(demandeRetour:DemandeRetour): Observable<DemandeRetour> {
      return this.http.post<DemandeRetour>(`${this.apiServerUrl}/add`, demandeRetour);
    }
    public findDemandeByRId(resource_id:number):Observable<DemandeRetour>{
      return this.http.get<DemandeRetour>(`${this.apiServerUrl}/findDemandeByRId/${resource_id}`);
    }
}