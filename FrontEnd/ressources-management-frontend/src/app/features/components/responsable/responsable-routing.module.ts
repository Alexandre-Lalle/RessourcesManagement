import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ListeRessourcesComponent } from './liste-ressources/liste-ressources.component';
import { ResourceDetailComponent } from './resource-detail/resource-detail.component';

const routes: Routes = [
  {path: "resources-list", component: ListeRessourcesComponent},
      
  { path: "resources-list/:id", component: ResourceDetailComponent }
        
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class ResponsableRoutingModule { }
