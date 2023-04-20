import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ListeRessourcesComponent } from './liste-ressources/liste-ressources.component';
import { ResourceDetailComponent } from './resource-detail/resource-detail.component';
import { ResponsableLayoutComponent } from 'src/app/core/components/responsable/responsable-layout/responsable-layout.component';

const routes: Routes = [
  {path: "", component: ResponsableLayoutComponent},
  {path: "",
    children: [
      {path: "resources-list", component: ListeRessourcesComponent, outlet: "center"},
      {path: "resources-list/:id", component: ResourceDetailComponent, outlet: "center" },
      {path: "resources-list/:state", component: ListeRessourcesComponent, outlet: "center"}
    ]
  }     
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
