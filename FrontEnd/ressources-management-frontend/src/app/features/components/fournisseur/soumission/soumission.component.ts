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
  ngOnInit(): void {
  this.getSoumission(); 
  }
  
  getSoumission(){
    return this.fournisseurServie.getSoumissions()
    .subscribe((soumissions)=> this.soumissions = soumissions)
  }
    
}
