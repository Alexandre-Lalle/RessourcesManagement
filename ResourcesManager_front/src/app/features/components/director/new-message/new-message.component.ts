import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/auth/services/auth.service';
import { Director } from 'src/app/features/models/director.model';
import { Message } from 'src/app/features/models/message.model';
import { MessagesService } from 'src/app/features/services/messages/messages.service';

@Component({
  selector: 'app-new-message',
  templateUrl: './new-message.component.html',
  styleUrls: ['./new-message.component.css']
})
export class NewMessageComponent implements OnInit {
  currentUser!:Director;
  addFormGroup!: FormGroup;
  controls:any



  constructor(private messagesService: MessagesService, private fb: FormBuilder, private authService:AuthService) { }


  ngOnInit(): void {
     this.controls = {
      subject: ['', [Validators.required, Validators.minLength(2)]],
      content: ['', [Validators.required, Validators.minLength(10)]]
    }

    this.addFormGroup = this.fb.group(this.controls);
    this.currentUser = this.authService.getUser();
  }

  public sendMessage(message: Message) {
    message.departmentDirector = this.currentUser;
    
    this.messagesService.addMessage(message).subscribe((res) => {
      alert("the message was sent # " + res);
    }, (err) => {
      console.log("Error while adding the message : \n" + err);
    });

    this.addFormGroup = this.fb.group(this.controls);
  }


  public getErrorMessage(filedName: string, errors: any) {
    if (errors['required']) {
      return "* the " + filedName + " is required !";
    }
    else if (errors['minlength']) {
      return "* the " + filedName + " should have at least " + errors['minlength']['requiredLength'] + " characters !"
    }
    else if (errors['maxlength']) {
      return "* the " + filedName + " shouldn't have more then " + errors['maxlength']['requiredLength'] + " characters !"
    }
    else return "";
  }

}
