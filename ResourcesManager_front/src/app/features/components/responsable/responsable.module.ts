import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListeRessourcesComponent } from './liste-ressources/liste-ressources.component';
import { ResponsableRoutingModule } from './responsable-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ResourceDetailComponent } from './resource-detail/resource-detail.component';
import { OffreComponent } from './offre/offre.component';
import { ConsulterFournisseurComponent } from './consulter-fournisseur/consulter-fournisseur.component';
import { ConsulterPannesComponent } from './consulter-pannes/consulter-pannes.component';
import { UserComponent } from './user/user.component';




@NgModule({
  declarations: [
    ListeRessourcesComponent,
    ResourceDetailComponent,
    OffreComponent,
    ConsulterFournisseurComponent,
    ConsulterPannesComponent,
    UserComponent
  ],
  imports: [
    CommonModule,
    ResponsableRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class ResponsableModule { }
