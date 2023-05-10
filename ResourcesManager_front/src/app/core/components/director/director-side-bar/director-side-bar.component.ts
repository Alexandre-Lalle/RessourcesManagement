import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/auth/services/auth.service';
import { Director } from 'src/app/features/models/director.model';

@Component({
  selector: 'app-director-side-bar',
  templateUrl: './director-side-bar.component.html',
  styleUrls: ['./director-side-bar.component.css']
})
export class DirectorSideBarComponent implements OnInit{
  currentUser!:Director;

  constructor(private authService:AuthService){}

  ngOnInit(): void {
    this.currentUser = this.authService.getUser();
  }
}
