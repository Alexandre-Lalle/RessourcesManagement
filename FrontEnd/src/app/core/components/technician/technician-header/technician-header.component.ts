import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/services/auth.service';

@Component({
  selector: 'app-technician-header',
  templateUrl: './technician-header.component.html',
  styleUrls: ['./technician-header.component.css']
})
export class TechnicianHeaderComponent {

  constructor(private authService:AuthService, private router:Router){}


  logout(){
    const confirmation = confirm("Voullez vous vraiment vous déconnecter ?") 
    if(confirmation){
      this.authService.clear();
      this.router.navigate(['login']);
    }
  }
}
