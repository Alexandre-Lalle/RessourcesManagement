import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Message } from '../../models/message.model';
import { Director } from '../../models/director.model';
import { AuthService } from 'src/app/auth/services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class MessagesService {

  constructor(private http:HttpClient, private authService:AuthService) { }


  getMessage(id:number):Observable<Message>{
    return this.http.get<Message>("http://localhost:8085/Recources-Managment/notifications/"+id);
  }

  getDirectorMessages():Observable<any>{
    const currentUser:Director = this.authService.getUser();
    return this.http.get<any>("http://localhost:8085/Recources-Managment/notifications/director/"+currentUser.id);
  }

  addMessage(message:Message):Observable<boolean>{
    const headers = { 'content-type': 'application/json' };
    return this.http.post<boolean>("http://localhost:8085/Recources-Managment/notifications",JSON.stringify(message),{'headers':headers});
  }


  updateMessage(message:Message):Observable<boolean>{
    const headers = {'content-type': 'application/json'};
    return this.http.put<boolean>("http://localhost:8085/Recources-Managment/notifications",message,{'headers':headers});
  }

  deleteMessage(id:number):Observable<boolean>{
    return this.http.delete<boolean>("http://localhost:8085/Recources-Managment/notifications/"+id);
  }
}
