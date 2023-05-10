import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Offre } from '../models/offre';
import { Soumission } from '../models/soumission';
import { AuthService } from 'src/app/auth/services/auth.service';
import { Teacher } from '../models/teacher.mode';
import { Provider } from '../models/provider.model';
import { DemandeRetour } from '../models/DemandeRetour.model';

@Injectable({
  providedIn: 'root'
})
export class FournisseurService {
  apiUrl = "http://localhost:8085/Recources-Managment/fournisseur";

  constructor(private http: HttpClient, private authService: AuthService) { }
  getOffres() {
    return this.http.get<Offre[]>(`${this.apiUrl}/liste-offres`);
  }
  getSoumissions() {
    const currentUser: Provider = this.authService.getUser()
    return this.http.get<Soumission[]>(`${this.apiUrl}/liste-soumissions/${currentUser.id}`);
  }
  editSoumission(soumission: Soumission) {
    return this.http.post<Soumission>(`${this.apiUrl}/modifier-soumission`, soumission);

  }
  deleteSoumission(id: any) {
    return this.http.get<string>(`${this.apiUrl}/supprimer-soumission/${id}`);
  }

  saveSoumission(soumission: Soumission) {
    const currentUser: Provider = this.authService.getUser()
    return this.http.post<Soumission>(`${this.apiUrl}/ajouter-soumission/${currentUser.id}`, soumission);
  }
  getMessages() {
    const currentUser: Provider = this.authService.getUser()
    return this.http.get<DemandeRetour[]>(`${this.apiUrl}/liste-messages/${currentUser.id}`);
  }

}
