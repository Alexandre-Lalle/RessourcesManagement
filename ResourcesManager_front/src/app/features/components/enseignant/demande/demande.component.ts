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
    this.getRequests();
  }

  getRequests() {
    return this.enseignantService.getRequests()
      .subscribe(requests => this.requests = requests)
  }
}
