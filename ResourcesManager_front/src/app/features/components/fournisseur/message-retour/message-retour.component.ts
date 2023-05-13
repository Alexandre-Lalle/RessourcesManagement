import { Component, OnInit } from '@angular/core';
import { DemandeRetour } from 'src/app/features/models/DemandeRetour.model';
import { FournisseurService } from 'src/app/features/services/fournisseur.service';

@Component({
  selector: 'app-message-retour',
  templateUrl: './message-retour.component.html',
  styleUrls: ['./message-retour.component.css']
})
export class MessageRetourComponent implements OnInit{
  constructor(private fournisseurService:FournisseurService ){}
  ngOnInit(): void {
     this.getMessages(); 
  }
  
  demandesRetour:DemandeRetour[] =[]

  currentDemandeRetour:DemandeRetour={
      message:""
  }

  getMessages(){
   this.fournisseurService.getMessages()
   .subscribe( messages => this.demandesRetour = messages)
  }
  setCurrentDemande(demande:DemandeRetour){
  this.currentDemandeRetour = demande; 
  }


}
