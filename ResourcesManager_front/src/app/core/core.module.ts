import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TeacherHeaderComponent } from './components/teacher/teacher-header/teacher-header.component';
import { DirectorSideBarComponent } from './components/director/director-side-bar/director-side-bar.component';
import { DirectorHeaderComponent } from './components/director/director-header/director-header.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { AppRoutingModule } from '../app-routing.module';
import { DirectorLayoutComponent } from './components/director/director-layout/director-layout.component';
import { TeacherSideBarComponent } from './components/teacher/teacher-side-bar/teacher-side-bar.component';
import { TechnicianSideBarComponent } from './components/technician/technician-side-bar/technician-side-bar.component';
import { TechnicianHeaderComponent } from './components/technician/technician-header/technician-header.component';
import { FooterComponent } from './components/footer/footer.component';
import { TechnicianLayoutComponent } from './components/technician/technician-layout/technician-layout.component';
import { TeacherLayoutComponent } from './components/teacher/teacher-layout/teacher-layout.component';
import { FournisseurLayoutComponent } from './components/fournisseur/fournisseur-layout/fournisseur-layout.component';
import { FournisseurHeaderComponent } from './components/fournisseur/fournisseur-header/fournisseur-header.component';
import { FournisseurSideBarComponent } from './components/fournisseur/fournisseur-side-bar/fournisseur-side-bar.component';
import { ResponsableLayoutComponent } from './components/responsable/responsable-layout/responsable-layout.component';
import { ResponsableSideBarComponent } from './components/responsable/responsable-side-bar/responsable-side-bar.component';
import { ResponsableHeaderComponent } from './components/responsable/responsable-header/responsable-header.component';


@NgModule({
  declarations: [
    TeacherSideBarComponent,
    TeacherHeaderComponent,
    TechnicianHeaderComponent,
    TechnicianSideBarComponent,
    DashboardComponent,
    DirectorLayoutComponent,
    DirectorHeaderComponent,
    DirectorSideBarComponent,
    FooterComponent,
    TechnicianLayoutComponent,
    TeacherLayoutComponent,
    FournisseurLayoutComponent,
    FournisseurHeaderComponent,
    FournisseurSideBarComponent,
    ResponsableLayoutComponent,
    ResponsableSideBarComponent,
    ResponsableHeaderComponent,
    FooterComponent
  ],
  imports: [
    CommonModule,
    AppRoutingModule
  ]
})
export class CoreModule { }
