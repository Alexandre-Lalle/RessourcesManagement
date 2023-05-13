import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from 'src/app/core/components/dashboard/dashboard.component';
import { DirectorLayoutComponent } from 'src/app/core/components/director/director-layout/director-layout.component';
import { ListDemandsComponent } from './list-demands/list-demands.component';
import { NewDemandComponent } from './new-demand/new-demand.component';
import { ListMessagesComponent } from './list-messages/list-messages.component';
import { NewMessageComponent } from './new-message/new-message.component';
import { UpdateMessageComponent } from './update-message/update-message.component';
import { UpdateDemandComponent } from './update-demand/update-demand.component';
import { AuthGuard } from 'src/app/auth/guards/auth.guard';

const routes: Routes = [
  {
    path: "",
    children: [
      { path: "", component: DirectorLayoutComponent, children: [{ path: "", component: DashboardComponent, outlet: "center" }] },
      {
        path: "demands",
        children: [
          { path: "", component: DirectorLayoutComponent, children: [{ path: "", component: ListDemandsComponent, outlet: "center" }] },
          { path: "add", component: DirectorLayoutComponent, children: [{ path: "", component: NewDemandComponent, outlet: "center" }] },
          { path: "update/:resourceId", component: DirectorLayoutComponent, children: [{ path: "", component: UpdateDemandComponent, outlet: "center" }]}
        ]
      },
      {
        path: "messages",
        children: [
          { path: "", component: DirectorLayoutComponent, children: [{ path: "", component: ListMessagesComponent, outlet: "center" }] },
          { path: "add", component: DirectorLayoutComponent, children: [{ path: "", component: NewMessageComponent, outlet: "center" }] },
          { path: "update/:messageId", component: DirectorLayoutComponent, children: [{ path: "", component: UpdateMessageComponent, outlet: "center" }
        ] 
      }
        ]
      },
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DirectorRoutingModule { }
