import { Component, OnInit, Provider } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user!: Provider;
  addFormGroup!: FormGroup;

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    let controls = {
      name: this.fb.control(''),
      eemail: this.fb.control(''),
      password: this.fb.control(''),
      confirmation: this.fb.control('')
    }
  }

  // register(user:any){
  //   let prov:Provider = {name:user.name}
  //   prov?.userName = 
  // }

}
