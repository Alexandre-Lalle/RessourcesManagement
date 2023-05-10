import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Login } from 'src/app/core/models/login.model';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  controls!: any;
  responseStatus!:number;



  constructor(private authService: AuthService, private userService: UserService, private fb: FormBuilder, private router: Router) { }



  ngOnInit(): void {
    this.controls = {
      userName: ['', [Validators.required]],
      password: ['', [Validators.required]]
    }

    this.loginForm = this.fb.group(this.controls);
  }


  login(login: Login) {
    console.log("login : ", login);
    this.userService.login(login).subscribe((resp) => {

      this.authService.setRoles(resp.user.roles)
      this.authService.setToken(resp.accessToken)
      this.authService.setUser(resp.user);

      const role = resp.user.roles[0].name;
      switch(role){
        case "DIRECTOR":this.router.navigate(["/director"]);break;
        case "TECHNICIAN":this.router.navigate(['/technician']);break;
        case "TEACHER":this.router.navigate(['/teacher/resources']);break;
        case "PROVIDER":this.router.navigate(['/provider/submissions']);break;
        case "MANAGER":this.router.navigate(['/manager']);break;
      }
    }, (err) => {
      this.responseStatus = err.status;
      console.log("Error when connectiong ! : ", err)
    });
  }
}
