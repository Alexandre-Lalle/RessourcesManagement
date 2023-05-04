import { Component, OnInit } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Director } from 'src/app/features/models/director.model';
import { Message } from 'src/app/features/models/message.model';
import { MessagesService } from 'src/app/features/services/messages/messages.service';

@Component({
  selector: 'app-list-messages',
  templateUrl: './list-messages.component.html',
  styleUrls: ['./list-messages.component.css']
})
export class ListMessagesComponent implements OnInit{
  messages!:Array<Message>;


  constructor(private messagesService:MessagesService){}

  ngOnInit(): void {
    this.fetchMessages();
  }


  public async fetchMessages():Promise<Message[]>{
    try{
      const data = await this.messagesService.getDirectorMessages().toPromise();
      this.messages = data;
      return this.messages;
    }catch(err){
      console.log("Error while fetching the messages from the api : \n"+err);
      return [];
    }
  }

  public deleteSelectedMessages(){
    this.deleteMessages().subscribe((resp)=>{
      alert("the selected messages was correctly deleted : "+resp);
    });
  }

  public deleteMessages():Observable<boolean>{
    let confirmation  = confirm("Vollez vous vraiment supprimer ces messages ?")

    if(confirmation){

      let messages = document.getElementsByClassName("messageBody");
      let checkboxs = document.getElementsByClassName("messageCheck");
      let deleted = true, input, check, index;
      console.log(messages,checkboxs);

      for(let i=0,index=0;i<messages.length;i++){
        check = checkboxs[i] as HTMLInputElement;
        if(check.checked){
          console.log("message : ",messages[i].nodeValue);
          input = messages[i] as HTMLInputElement;
          this.messagesService.deleteMessage(+input.value).subscribe((resp)=>{
            this.messages.splice(index,1);
          },(err)=>{
            console.log("Error while deleting the messages : \n",err)
            return false;
          })
        }
        else
          index++;
      }
      return of(deleted);
    }

    return of(false);
  }
}
