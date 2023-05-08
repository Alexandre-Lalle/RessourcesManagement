import { Component, OnInit } from '@angular/core';
import { Besoin } from 'src/app/features/models/Besoin.model';
import { Computer } from 'src/app/features/models/computer.model';
import { Message } from 'src/app/features/models/message.model';
import { Printer } from 'src/app/features/models/printer.model';
import { EnseignantService } from 'src/app/features/services/enseignant.service';

@Component({
  selector: 'app-demande',
  templateUrl: './demande.component.html',
  styleUrls: ['./demande.component.css']
})
export class DemandeComponent implements OnInit {
  requests: Message[] = [];

  constructor(private enseignantService: EnseignantService) {
  }
  ngOnInit(): void {
    this.getRequests(1);
  }
  getRequests(id: any) {
    return this.enseignantService.getRequests(id)
      .subscribe(requests => this.requests = requests)
  }
  computer: Computer = {
    id: NaN,
    barCode: NaN,
    assignmentDate: new Date(),
    deliveryDate: new Date(),
    warrantyDate: new Date(),
    dateOfRequest: new Date(),
    brand: "",
    providerName: "",
    resourceType: "Computer",
    state: -1,
    cpu: "",
    disk: "",
    screen: "",
    ram: 0
  };
  printer: Printer = {
    id: NaN,
    barCode: NaN,
    assignmentDate: new Date(),
    deliveryDate: new Date(),
    warrantyDate: new Date(),
    dateOfRequest: new Date(),
    brand: "",
    resourceType: "Computer",
    providerName: "",
    state: -1,
    printSpeed: 0,
    resolution: ""
  }

  besoin: Besoin = {}
  save(computer: Computer, printer: Printer) {
    this.besoin.ordinateurDto = computer;
    this.besoin.imprimanteDto = printer;
    return this.enseignantService.saveRequest(this.besoin)
      .subscribe((besoin) => {
        if (besoin.imprimanteDto != null)
          alert("Votre demande est ajoute avec succes");
        else alert("Votre demande n'est pas ajouter contacte le chef");
      });
  }
  getBesoin(id: any) {
    return this.enseignantService.getBesoin(id)
      .subscribe((besoin) => {
        if (besoin.imprimanteDto != null) this.printer = besoin.imprimanteDto
        if (besoin.ordinateurDto != null) this.computer = besoin.ordinateurDto

      })
  }
}
