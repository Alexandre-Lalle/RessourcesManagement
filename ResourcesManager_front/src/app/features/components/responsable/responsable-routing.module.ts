import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ListeRessourcesComponent } from './liste-ressources/liste-ressources.component';
import { ResourceDetailComponent } from './resource-detail/resource-detail.component';
import { ResponsableLayoutComponent } from 'src/app/core/components/responsable/responsable-layout/responsable-layout.component';
import { DashboardComponent } from 'src/app/core/components/dashboard/dashboard.component';
import { OffreComponent } from './offre/offre.component';
import { ConsulterFournisseurComponent } from './consulter-fournisseur/consulter-fournisseur.component';
import { ConsulterPannesComponent } from './consulter-pannes/consulter-pannes.component';
import { UserComponent } from './user/user.component';

const routes: Routes = [
  {
    path: "",
    children: [
      { path: "", component: ResponsableLayoutComponent, children: [{ path: "", component: DashboardComponent, outlet: "center" }] },
      {
        path: "resources-list",
        children: [
          { path: "", component: ResponsableLayoutComponent, children: [{ path: "", component: ListeRessourcesComponent, outlet: "center" }] }
        ]
      },
      {
        path: "resource-detail",
        children: [
          { path: ":type/:id", component: ResponsableLayoutComponent, children: [{ path: "", component: ResourceDetailComponent, outlet: "center" }] },
        ]
      },
      {
        path: "offre",
        children: [
          { path: "", component: ResponsableLayoutComponent, children: [{ path: "", component: OffreComponent, outlet: "center" }] },
        ]
      },
      {
        path: "consulterF",
        children: [
          { path: "", component: ResponsableLayoutComponent, children: [{ path: "", component: ConsulterFournisseurComponent, outlet: "center" }] },
        ]
      },
      {
        path: "consulterP",
        children: [
          { path: "", component: ResponsableLayoutComponent, children: [{ path: "", component: ConsulterPannesComponent, outlet: "center" }] },
        ]
      },
      {
        path: "teachers",
        children: [
          { path: "", component: ResponsableLayoutComponent, children: [{ path: "", component: UserComponent, outlet: "center" }] }
        ]
      },
    ]
  },
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
