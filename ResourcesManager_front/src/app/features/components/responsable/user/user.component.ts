import { Component } from '@angular/core';
import { Teacher } from 'src/app/features/models/teacher.mode';
import { User } from 'src/app/features/models/user.model';
import { UserService } from 'src/app/features/services/user.service';


@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent {
  users: Teacher[] = [];
  myUser: Teacher = {
    id: 0,
    firstName: "",
    lastName: "",
    userName: "",
    email: "",
    password: "1234",
    department: "",
    type: "teacher"
  }
  operation: string = "";
  id_deleted: number = -1;
  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.getUsers();
  }

  getUsers() {
    this.userService.findAll().subscribe((data) => {
      this.users = data;
    })
  }
  modal_delete(id: any) {
    this.id_deleted = id;
  }

  deleteUser(id: any) {
    this.userService.deleteUser(id).subscribe((data) => {
      console.log("resultat : " + data);
      this.users = this.users.filter(user => user.id != id);
      this.id_deleted = -1;
    });
  }

  addUser() {
    this.myUser = {
      id: 0,
      firstName: "",
      lastName: "",
      userName: "",
      email: "",
      password: "1234",
      department: "",
      type: "teacher"
    }
    this.operation = "add";
  }

  persistUser() {
    this.userService.addUser(this.myUser).subscribe((data) => {
      this.users = [data, ...this.users];
    })
  }

  editOffre(user: Teacher) {
    this.myUser = user;
    this.operation = "edit";
  }

  // updateOffre(){
  //   console.log("objet a modifier : "+this.myOffre);
  //   console.log(this.myUser);
  //   this.userService.updateUser(this.myUser).subscribe(data => {
  //     console.log("objet retourner : "+data);
  //     console.log(data);
  //   });
  //  }

}

