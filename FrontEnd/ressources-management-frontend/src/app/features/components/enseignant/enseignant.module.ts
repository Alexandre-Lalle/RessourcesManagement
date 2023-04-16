import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EnseignantRoutingModule } from './enseignant-routing.module';
import { DemandeComponent } from './demande/demande.component';
import { FormsModule } from '@angular/forms';
import { MesRessourcesComponent } from './mes-ressources/mes-ressources.component';


@NgModule({
  declarations: [DemandeComponent,MesRessourcesComponent],
  imports: [
    CommonModule,
    EnseignantRoutingModule,
    FormsModule
  ]
})
export class EnseignantModule { }
