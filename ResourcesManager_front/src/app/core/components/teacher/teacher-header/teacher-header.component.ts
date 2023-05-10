import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/services/auth.service';
import { Teacher } from 'src/app/features/models/teacher.mode';

@Component({
  selector: 'app-teacher-header',
  templateUrl: './teacher-header.component.html',
  styleUrls: ['./teacher-header.component.css']
})
export class TeacherHeaderComponent implements OnInit{
  currentUser!:Teacher;

  constructor(private authService:AuthService, private router:Router){}


  ngOnInit(): void {
    this.currentUser =  this.authService.getUser();
  }


  logout(){
    const confirmation = confirm("Voullez vous vraiment vous déconnecter ?") 
    if(confirmation){
      this.authService.clear();
      this.router.navigate(['login']);
    }
  }

}
