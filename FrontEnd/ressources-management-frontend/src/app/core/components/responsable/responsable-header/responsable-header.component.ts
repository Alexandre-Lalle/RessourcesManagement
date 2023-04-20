import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/services/auth.service';
import { Responsable } from 'src/app/features/models/responsable.model';

@Component({
  selector: 'app-responsable-header',
  templateUrl: './responsable-header.component.html',
  styleUrls: ['./responsable-header.component.css']
})
export class ResponsableHeaderComponent implements OnInit {

  currentUser!:Responsable;

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
