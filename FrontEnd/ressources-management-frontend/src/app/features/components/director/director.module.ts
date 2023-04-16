import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';

import { DirectorRoutingModule } from './director-routing.module';
import { DemandsService } from '../../services/demand/demand.service';
import { HttpClientModule } from '@angular/common/http';
import { NewDemandComponent } from './new-demand/new-demand.component';
import { CreateMessageComponent } from './create-message/create-message.component';
import { ListDemandsComponent } from './list-demands/list-demands.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UpdateDemandComponent } from './update-demand/update-demand.component';
import { ListMessagesComponent } from './list-messages/list-messages.component';
import { NewMessageComponent } from './new-message/new-message.component';
import { UpdateMessageComponent } from './update-message/update-message.component';


@NgModule({
  declarations: [
    NewDemandComponent,
    CreateMessageComponent,
    ListDemandsComponent,
    UpdateDemandComponent,
    ListMessagesComponent,
    NewMessageComponent,
    UpdateMessageComponent
  ],
  providers: [DemandsService, DatePipe],
  imports: [
    CommonModule,
    DirectorRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
  ]
})
export class DirectorModule { }
