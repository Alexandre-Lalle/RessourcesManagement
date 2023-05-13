import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/services/auth.service';
import { User } from 'src/app/features/models/user.model';

@Component({
  selector: 'app-technician-header',
  templateUrl: './technician-header.component.html',
  styleUrls: ['./technician-header.component.css']
})
export class TechnicianHeaderComponent implements OnInit{
  currentUser!:User;

  constructor(private authService:AuthService, private router:Router){}

  ngOnInit(): void {
    this.currentUser = this.authService.getUser();
  }

  logout(){
    const confirmation = confirm("Voullez vous vraiment vous déconnecter ?") 
    if(confirmation){
      this.authService.clear();
      this.router.navigate(['login']);
    }
  }
}
