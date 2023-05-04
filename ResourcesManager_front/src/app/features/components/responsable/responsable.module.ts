import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListeRessourcesComponent } from './liste-ressources/liste-ressources.component';
import { ResponsableRoutingModule } from './responsable-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ResourceDetailComponent } from './resource-detail/resource-detail.component';
import { OffreComponent } from './offre/offre.component';



@NgModule({
  declarations: [
    ListeRessourcesComponent, 
    ResourceDetailComponent,
    OffreComponent
  ],
  imports: [
    CommonModule,
    ResponsableRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class ResponsableModule { }
