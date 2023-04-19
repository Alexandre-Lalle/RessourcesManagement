import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Computer } from 'src/app/features/models/computer.model';
import { Printer } from 'src/app/features/models/printer.model';
import { ResponsableService } from 'src/app/features/services/responsable.service';

@Component({
  selector: 'app-liste-ressources',
  templateUrl: './liste-ressources.component.html',
  styleUrls: ['./liste-ressources.component.css']
})
export class ListeRessourcesComponent implements OnInit {

  computers$!:Observable<Computer[]>; 
  printers$!:Observable<Printer[]>;

  constructor(private responsableService : ResponsableService, private router : Router){}

  ngOnInit(): void {
    this.computers$ = this.responsableService.findComputers();
    this.printers$ = this.responsableService.findPrinters();
  }

  onConsultResource(computerId : number): void {
    this.router.navigateByUrl(`resources-list/${computerId}`);
  }
  
/*
  getComputers(){
    return this.responsableService.findComputers()
    .subscribe(computers => this.computers =computers)
    ; 
  }
  getPrinters(){
    return this.responsableService.findPrinters()
    .subscribe( printers => this.printers = printers )
    ; 
  }*/

}
