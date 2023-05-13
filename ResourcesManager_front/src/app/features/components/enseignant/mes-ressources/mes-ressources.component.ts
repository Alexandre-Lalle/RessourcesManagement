import { Component, OnInit } from '@angular/core';
import { Computer } from 'src/app/features/models/computer.model';
import { Panne } from 'src/app/features/models/panne';
import { Printer } from 'src/app/features/models/printer.model';
import { EnseignantService } from 'src/app/features/services/enseignant.service';


@Component({
  selector: 'app-mes-ressources',
  templateUrl: './mes-ressources.component.html',
  styleUrls: ['./mes-ressources.component.css']
})
export class MesRessourcesComponent implements OnInit {
  computers: Computer[] = [];
  printers: Printer[] = [];
  panne: Panne = {

  };
  panneList: Panne[] = [];

  constructor(private enseignantService: EnseignantService) {

  }
  ngOnInit(): void {
    this.getComputers();
    this.getPrinters();

  }
  // Mes Ressouces
  getComputers() {
    return this.enseignantService.findCumputers()
      .subscribe((computers) => {
        this.computers = computers;
        console.log("computer :", this.computers)
      })
      ;
  }
  getPrinters() {
    return this.enseignantService.findPrinters()
      .subscribe(printers => { this.printers = printers })
      ;
  }
  // ajouter panne
  remplir(id: any) {
    this.panne.ressourceDto = {
      id: id,
      barCode: NaN,
      name: "",
      assignmentDate: new Date(),
      deliveryDate: new Date(),
      warrantyDate: new Date(),
      dateOfRequest: new Date(),
      brand: "",
      resourceType: "Computer",
      state: 1
    };
  }
  savePane() {
    return this.enseignantService.savePane(this.panne)
      .subscribe(panne => alert("Le panne est ajoute avec succes "), err => alert("Il y a une erreur!!!"));

  }
  setPanneList(pannes: any) {
    this.panneList = pannes;
    console.log("hello there ", this.panneList);
  }




}
