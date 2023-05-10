import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FournisseurLayoutComponent } from 'src/app/core/components/fournisseur/fournisseur-layout/fournisseur-layout.component';
import { OffreListeComponent } from './offre-liste/offre-liste.component';
import { SoumissionComponent } from './soumission/soumission.component';
import { MessageRetourComponent } from './message-retour/message-retour.component';

const routes: Routes = [
  {
    path: "",
    children: [
      {
        path: "offres-list",
        children: [
          { path: "", component: FournisseurLayoutComponent, children: [{ path: "", component: OffreListeComponent, outlet: "center" }] },
        ]
      },
      {
        path: "submissions",
        children: [
          { path: "", component: FournisseurLayoutComponent, children: [{ path: "", component: SoumissionComponent, outlet: "center" }] },
        ]
      },
      {
        path: "messages",
        children: [
          { path: "", component: FournisseurLayoutComponent, children: [{ path: "", component: MessageRetourComponent, outlet: "center" }] },
        ]
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FournisseurRoutingModule { }
