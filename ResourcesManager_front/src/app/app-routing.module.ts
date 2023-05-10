import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DirectorSideBarComponent } from './core/components/director/director-side-bar/director-side-bar.component';
import { DashboardComponent } from './core/components/dashboard/dashboard.component';
import { LoginComponent } from './auth/components/login/login.component';
import { RegisterComponent } from './auth/components/register/register.component';
import { AuthGuard } from './auth/guards/auth.guard';


const routes: Routes = [
  {path: 'director',
  loadChildren: () => import('src/app/features/components/director/director.module').then(m => m.DirectorModule), canActivate:[AuthGuard], data:{roles:['DIRECTOR']}
  },
  {path: 'technician',
  loadChildren: () => import('src/app/features/components/technician/technician.module').then(m => m.TechnicianModule), canActivate:[AuthGuard], data:{roles:['TECHNICIAN']}
  },
  {path: 'teacher',
  loadChildren: () => import('src/app/features/components/enseignant/enseignant.module').then(m => m.EnseignantModule)
  },
  {path: 'provider',
  loadChildren: () => import('src/app/features/components/fournisseur/fournisseur.module').then(m => m.FournisseurModule)
  },
  {path: 'manager',
  loadChildren: () => import('src/app/features/components/responsable/responsable.module').then(m => m.ResponsableModule), canActivate:[AuthGuard], data:{roles:['MANAGER']}
  },
  {path:"login",component:LoginComponent},
  {path:"register",component:RegisterComponent},
  {path:"**",redirectTo:"login",pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
