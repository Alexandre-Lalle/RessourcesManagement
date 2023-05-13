import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/auth/services/auth.service';
import { User } from 'src/app/features/models/user.model';

@Component({
  selector: 'app-technician-side-bar',
  templateUrl: './technician-side-bar.component.html',
  styleUrls: ['./technician-side-bar.component.css']
})
export class TechnicianSideBarComponent implements OnInit {
  currentUser!:User;

  constructor(private authService:AuthService){}

  ngOnInit(): void {
    this.currentUser = this.authService.getUser();
  }
}
