import { Component, OnInit } from '@angular/core';
import { Soumission } from 'src/app/features/models/soumission';
import { FournisseurService } from 'src/app/features/services/fournisseur.service';

@Component({
  selector: 'app-soumission',
  templateUrl: './soumission.component.html',
  styleUrls: ['./soumission.component.css']
})
export class SoumissionComponent implements OnInit {
  constructor(private fournisseurServie:FournisseurService){}
  public soumissions:Soumission[]= []
  public soumission:Soumission   = {
    prix:0
    ,offreDto:{id:1
    ,ordinateurDtoList:[],
    imprimanteDtoList:[]
    }  
  }; 
  ngOnInit(): void {
  this.getSoumission(); 
  }
  
  getSoumission(){
    return this.fournisseurServie.getSoumissions()
    .subscribe((soumissions)=> this.soumissions = soumissions)
  }
  deleteSoumission(id:any){
    return this.fournisseurServie.deleteSoumission(id)
    .subscribe( ( ) => {
    this.soumissions = this.soumissions.filter(soumission => soumission.id != id);
    alert("Votre soumission est supprimer avec succes")
    
    })

  }
  editSoumission(soumission:Soumission){
    this.soumission = soumission
   
  }
  updateSoumission(){
    
     return this.fournisseurServie.editSoumission(this.soumission)
     .subscribe((soumission) => alert("votre soumission est modifie avec succes "))
  }
    
}
