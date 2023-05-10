import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/auth/services/auth.service';
import { Director } from 'src/app/features/models/director.model';
import { Message } from 'src/app/features/models/message.model';
import { MessagesService } from 'src/app/features/services/messages/messages.service';


@Component({
  selector: 'app-update-message',
  templateUrl: './update-message.component.html',
  styleUrls: ['./update-message.component.css']
})
export class UpdateMessageComponent implements OnInit {
  currentUser!: Director;
  addFormGroup!:FormGroup;
  message!:Message;
  controls!:{};

  constructor(private messagesService:MessagesService, private fb:FormBuilder, private route:ActivatedRoute, private authService:AuthService){}


  ngOnInit(): void {
    
    this.route.params.subscribe((params)=>{
      this.messagesService.getMessage(params['messageId']).subscribe((data)=>{
        this.message = data;
      this.addFormGroup = this.fb.group({
        subject: [data.subject, [Validators.required, Validators.minLength(2)]],
        content: [data.content, [Validators.required, Validators.minLength(10)]]
      });
      });
    })

    this.controls = {
      subject: ['', [Validators.required, Validators.minLength(2)]],
      content: ['', [Validators.required, Validators.minLength(10)]]
    }
    this.addFormGroup = this.fb.group(this.controls);

    this.currentUser = this.authService.getUser();
  }

  public updateMessage(message:Message){
    this.message.subject = message.subject;
    this.message.content = message.content;
    console.log(this.message);
    this.messagesService.addMessage(this.message).subscribe((res)=>{
      alert("le message a ete modifie avec succes # "+res);
    },(err)=>{
      console.log("Error while adding the message : \n"+err);
    });
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
