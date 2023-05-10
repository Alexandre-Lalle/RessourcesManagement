import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DemandeComponent } from './demande/demande.component';
import { MesRessourcesComponent } from './mes-ressources/mes-ressources.component';
import { TeacherLayoutComponent } from 'src/app/core/components/teacher/teacher-layout/teacher-layout.component';
import { ListDemandsComponent } from './list-demands/list-demands.component';
import { NewDemandsComponent } from './new-demands/new-demands.component';
import { UpdateDemandComponent } from './update-demand/update-demand.component';

const routes: Routes = [
  {
    path: "",
    children: [
      {
        path: "requests-list",
        children: [
          { path: "", component: TeacherLayoutComponent, children: [{ path: "", component: ListDemandsComponent, outlet: "center" }] },
        ]
      },
      {
        path: "resources",
        children: [
          { path: "", component: TeacherLayoutComponent, children: [{ path: "", component: MesRessourcesComponent, outlet: "center" }] },
        ]
      },
      {
        path: "demands",
        children: [
          { path: "", component: TeacherLayoutComponent, children: [{ path: "", component: ListDemandsComponent, outlet: "center" }] },
          { path: "add", component: TeacherLayoutComponent, children: [{ path: "", component: NewDemandsComponent, outlet: "center" }] },
          { path: "update/:resourceId", component: TeacherLayoutComponent, children: [{ path: "", component: UpdateDemandComponent, outlet: "center" }] }
        ]
      },
      {
        path: "notifications",
        children: [
          { path: "", component: TeacherLayoutComponent, children: [{ path: "", component: DemandeComponent, outlet: "center" }] },
        ]
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EnseignantRoutingModule { }
