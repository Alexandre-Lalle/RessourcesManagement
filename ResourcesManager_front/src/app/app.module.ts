import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { CoreModule } from './core/core.module';
import { LoginComponent } from './auth/components/login/login.component';
import { RegisterComponent } from './auth/components/register/register.component';
import { RecoverPasswordComponent } from './auth/components/recover-password/recover-password.component';
import { DirectorModule } from './features/components/director/director.module';
import { ListFailuresComponent } from './features/components/technician/list-failures/list-failures.component';
import { CreateReportComponent } from './features/components/technician/create-report/create-report.component';
import { UpdateReportComponent } from './features/components/technician/update-report/update-report.component';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthGuard } from './auth/guards/auth.guard';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { UserService } from './auth/services/user.service';
import { AuthInterceptor } from './auth/guards/auth.interceptor';
import { EnseignantModule } from './features/components/enseignant/enseignant.module';
import { FournisseurModule } from './features/components/fournisseur/fournisseur.module';
import { ResponsableModule } from './features/components/responsable/responsable.module';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    RecoverPasswordComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    DirectorModule,
    EnseignantModule,
    FournisseurModule,
    ResponsableModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
