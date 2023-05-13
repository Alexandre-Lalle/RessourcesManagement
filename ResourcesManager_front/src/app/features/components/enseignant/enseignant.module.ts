import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EnseignantRoutingModule } from './enseignant-routing.module';
import { DemandeComponent } from './demande/demande.component';
import { FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MesRessourcesComponent } from './mes-ressources/mes-ressources.component';
import { ListDemandsComponent } from './list-demands/list-demands.component';
import { NewDemandsComponent } from './new-demands/new-demands.component';
import { UpdateDemandComponent } from './update-demand/update-demand.component';


@NgModule({
  declarations: [DemandeComponent, MesRessourcesComponent, ListDemandsComponent, NewDemandsComponent, UpdateDemandComponent],
  imports: [
    CommonModule,
    EnseignantRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class EnseignantModule { }
