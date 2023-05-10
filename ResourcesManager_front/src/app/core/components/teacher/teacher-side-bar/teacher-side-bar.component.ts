import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/services/auth.service';
import { Teacher } from 'src/app/features/models/teacher.mode';

@Component({
  selector: 'app-teacher-side-bar',
  templateUrl: './teacher-side-bar.component.html',
  styleUrls: ['./teacher-side-bar.component.css']
})
export class TeacherSideBarComponent {
  currentUser!: Teacher;


  constructor(private authService: AuthService) { }


  ngOnInit(): void {
    this.currentUser = this.authService.getUser();
  }

}
