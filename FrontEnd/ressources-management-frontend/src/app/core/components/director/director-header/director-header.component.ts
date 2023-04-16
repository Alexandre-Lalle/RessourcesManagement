import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/services/auth.service';
import { Director } from 'src/app/features/models/director.model';

@Component({
  selector: 'app-director-header',
  templateUrl: './director-header.component.html',
  styleUrls: ['./director-header.component.css']
})
export class DirectorHeaderComponent implements OnInit{
  currentUser!:Director;

  constructor(private router:Router, private authService:AuthService){}



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
