import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DemandeComponent } from './demande/demande.component';
import { MesRessourcesComponent } from './mes-ressources/mes-ressources.component';
import { TeacherLayoutComponent } from 'src/app/core/components/teacher/teacher-layout/teacher-layout.component';

const routes: Routes = [
  {path: "",
    children: [
      {path: "requests-list",
        children: [
          { path: "", component: TeacherLayoutComponent, children: [{ path: "", component: DemandeComponent, outlet: "center" }] },
        ]
      },
      {path: "resources",
        children: [
          { path: "", component: TeacherLayoutComponent, children: [{ path: "", component: MesRessourcesComponent, outlet: "center" }] },
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
