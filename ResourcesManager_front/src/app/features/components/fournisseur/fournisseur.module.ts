import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FournisseurRoutingModule } from './fournisseur-routing.module';
import { FormsModule } from '@angular/forms';
import { AjouterSoumissionComponent } from './ajouter-soumission/ajouter-soumission.component';
import { OffreListeComponent } from './offre-liste/offre-liste.component';
import { SoumissionComponent } from './soumission/soumission.component';
import { MessageRetourComponent } from './message-retour/message-retour.component';


@NgModule({
  declarations: [AjouterSoumissionComponent, OffreListeComponent, SoumissionComponent, MessageRetourComponent],
  imports: [
    CommonModule,
    FournisseurRoutingModule,
    FormsModule
  ]
})
export class FournisseurModule { }
