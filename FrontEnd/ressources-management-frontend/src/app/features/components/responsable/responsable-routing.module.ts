import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ListeRessourcesComponent } from './liste-ressources/liste-ressources.component';
import { ResourceDetailComponent } from './resource-detail/resource-detail.component';
import { ResponsableLayoutComponent } from 'src/app/core/components/responsable/responsable-layout/responsable-layout.component';
import { DashboardComponent } from 'src/app/core/components/dashboard/dashboard.component';


  //{path: "", component: ResponsableLayoutComponent},
  /*{path: "", component: ResponsableLayoutComponent,
    children: [
      //{ path: "", component: DashboardComponent, outlet: "center" },
     /* {
        path: "resources-list",
        children: [
          { path: "", component: ResponsableLayoutComponent, children: [{ path: "", component: ListeRessourcesComponent, outlet: "center" }] },
          { path: "filter/:state", component: ResponsableLayoutComponent, children: [{ path: "", component: ListeRessourcesComponent, outlet: "center" }] },
          { path: ":id", component: ResponsableLayoutComponent, children: [{ path: "", component: ResourceDetailComponent, outlet: "center" }]}
        ]
      }
      {path: "resources-list", component: ListeRessourcesComponent, outlet: "center"},
      {path: "resource-detail/:type/:id", component: ResourceDetailComponent, outlet: "center"}
      //{path: "resources-list/filter/:state", component: ListeRessourcesComponent, outlet: "center"}
    ]
  },*/


const routes: Routes = [
  {path: "",
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
          { path: "/:type/:id", component: ResponsableLayoutComponent, children: [{ path: "", component: ResourceDetailComponent, outlet: "center" }] },
        ] 
      }
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
