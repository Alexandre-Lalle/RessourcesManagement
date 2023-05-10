import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Report } from '../../models/report.model';
import { Failure } from '../../models/failure.model';

@Injectable({
  providedIn: 'root'
})
export class FailureService {

  constructor(private http: HttpClient) { }

  public getFailures(): Observable<Failure[]> {
    return this.http.get<Failure[]>("http://localhost:8085/Resource-Managment/failures");
  }

  public getReport(id: number): Observable<any> {
    return this.http.get<any>("http://localhost:8085/Resource-Managment/reports/failures/" + id);
  }

  public updateFailure(failure: Failure): Observable<any> {
    const headers = { 'content-type': 'application/json' }
    return this.http.put<boolean>("http://localhost:8085/Resource-Managment/failures", JSON.stringify(failure), { 'headers': headers });
  }

  public updateReport(report: Report): Observable<boolean> {
    const headers = { 'content-type': 'application/json' }
    return this.http.put<boolean>("http://localhost:8085/Resource-Managment/reports", JSON.stringify(report), { 'headers': headers });
  }

  public deleteReport(report: Report): Observable<boolean> {
    return this.http.delete<boolean>("http://localhost:8085/Resource-Managment/reports/" + report.id);
  }
}
