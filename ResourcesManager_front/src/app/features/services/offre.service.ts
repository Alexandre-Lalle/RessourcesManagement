import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Offre } from '../models/offre';


@Injectable({
  providedIn: 'root'
})

export class OffreService {

  apiUrlOffre = "http://localhost:8085/responsable/offres";
  apiUrlRessource = "http://localhost:8085/responsable/ressources";

  constructor(private http: HttpClient) { }

  findAll() {
    return this.http.get<Offre[]>(this.apiUrlOffre);
  }

  deleteOffre(id: number) {
    return this.http.delete<boolean>(this.apiUrlOffre + "/" + id);
  }

  addOffre(offre: Offre) {
    return this.http.post<Offre>(this.apiUrlOffre + "/add", offre);
  }

  updateOffre(offre: Offre) {
    console.log(offre);
    return this.http.put<Offre>(`${this.apiUrlOffre}`, offre);
  }

  findRessourcesWithoutOffre() {
    return this.http.get<any>(`${this.apiUrlRessource}/WithoutOffre`);
  }
  accepterSoumission(id: number) {
    return this.http.get<boolean>(this.apiUrlOffre + "/accepterSoumission/" + id);
  }

}
