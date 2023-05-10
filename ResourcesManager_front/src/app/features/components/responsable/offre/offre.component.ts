import { Component, OnInit } from '@angular/core';
import { Computer } from 'src/app/features/models/computer.model';
import { Offre } from 'src/app/features/models/offre';
import { Printer } from 'src/app/features/models/printer.model';
import { OffreService } from 'src/app/features/services/offre.service';
//import {formatDate} from '@angular/common';

@Component({
  selector: 'app-offre',
  templateUrl: './offre.component.html',
  styleUrls: ['./offre.component.css']
})
export class OffreComponent implements OnInit {

  public offres: Offre[] = [];
  public ordinateurWithoutOffre: Computer[] = [];
  public imprimanteWithoutOffre: Printer[] = [];

  public myOffre: Offre = {
    id: 0,
    dateDebut: new Date(),
    dateFin: new Date(),
    ordinateurDtoList: [],
    imprimanteDtoList: [],
    soumissionDTOList: []
  }
  public operation: string = "add";
  public id_deleted: number = -1;
  public id_acceptSoumission: number = -1
  public selectToutOrd: boolean = false;
  public selectToutImp: boolean = false;
  constructor(private offreService: OffreService) {
  }

  ngOnInit(): void {
    this.getOffres();
  }

  getOffres() {
    this.offreService.findAll().subscribe((data) => {
      this.offres = data;
    })
  }

  modal_delete(id: any) {
    this.id_deleted = id;
  }

  deleteOffre(id: any) {
    this.offreService.deleteOffre(id).subscribe((data) => {
      console.log("resultat : " + data);
      this.offres = this.offres.filter(offre => offre.id != id);
      this.id_deleted = -1;
    });
  }

  addOffre() {
    this.offreService.findRessourcesWithoutOffre().subscribe((data) => {
      this.imprimanteWithoutOffre = data;
      this.imprimanteWithoutOffre = this.imprimanteWithoutOffre.filter(ressource => ressource.resourceType == "Printer");
      this.imprimanteWithoutOffre.forEach(rc => { rc.selected = false });
      this.ordinateurWithoutOffre = data;
      this.ordinateurWithoutOffre = this.ordinateurWithoutOffre.filter(ressource => ressource.resourceType == "Computer");
      this.ordinateurWithoutOffre.forEach(rc => { rc.selected = false });
      this.myOffre = {
        id: 0,
        dateDebut: new Date(),
        dateFin: new Date(),
        ordinateurDtoList: [],
        imprimanteDtoList: [],
        soumissionDTOList: []
      }
      this.operation = "add";
    })
  }

  persistOffre() {
    this.imprimanteWithoutOffre = this.imprimanteWithoutOffre.filter(im => im.selected == true)
    this.myOffre.imprimanteDtoList = this.myOffre.imprimanteDtoList.concat(this.imprimanteWithoutOffre);

    this.myOffre.ordinateurDtoList = this.myOffre.ordinateurDtoList.concat(this.ordinateurWithoutOffre.filter(or => or.selected == true));

    this.offreService.addOffre(this.myOffre).subscribe((data) => {
      this.offres = [data, ...this.offres];
      this.imprimanteWithoutOffre = [];
      this.ordinateurWithoutOffre = [];
    })
  }
  editOffre(offre: Offre) {
    this.offreService.findRessourcesWithoutOffre().subscribe((data) => {
      this.imprimanteWithoutOffre = data;
      this.imprimanteWithoutOffre = this.imprimanteWithoutOffre.filter(ressource => ressource.resourceType == "Printer");
      this.imprimanteWithoutOffre.forEach(rc => { rc.selected = false });
      this.ordinateurWithoutOffre = data;
      this.ordinateurWithoutOffre = this.ordinateurWithoutOffre.filter(ressource => ressource.resourceType == "Computer");
      this.ordinateurWithoutOffre.forEach(rc => { rc.selected = false });
      this.myOffre = offre;
      this.myOffre.imprimanteDtoList.forEach(item => {
        item.selected = true;
      });
      this.myOffre.ordinateurDtoList.forEach(item => {
        item.selected = true;
      });
      this.operation = "edit";
    })
  }
  viewOffre(offre: Offre) {
    this.imprimanteWithoutOffre = [];
    this.ordinateurWithoutOffre = [];
    this.myOffre = offre;
    this.operation = "view";
  }
  updateOffre() {
    this.myOffre.imprimanteDtoList = this.myOffre.imprimanteDtoList.filter(im => im.selected == true);
    this.imprimanteWithoutOffre = this.imprimanteWithoutOffre.filter(im => im.selected == true)
    this.myOffre.imprimanteDtoList = this.myOffre.imprimanteDtoList.concat(this.imprimanteWithoutOffre);
    this.myOffre.ordinateurDtoList = this.myOffre.ordinateurDtoList.filter(or => or.selected == true);
    this.myOffre.ordinateurDtoList = this.myOffre.ordinateurDtoList.concat(this.ordinateurWithoutOffre.filter(or => or.selected == true));

    console.log("objet a modifier : " + this.myOffre);
    console.log(this.myOffre);
    this.offreService.updateOffre(this.myOffre).subscribe(data => {
      console.log("objet retourner : " + data);
      console.log(data);
    });
  }

  selectOrdinateur(or: Computer) {
    let index;
    index = this.myOffre.ordinateurDtoList.findIndex(item => item.id == or.id);
    if (index != -1) {
      this.myOffre.ordinateurDtoList[index].selected = !this.myOffre.ordinateurDtoList[index].selected;
    } else {
      const index = this.ordinateurWithoutOffre.findIndex(item => item.id == or.id);
      this.ordinateurWithoutOffre[index].selected = !this.ordinateurWithoutOffre[index].selected;
    }
  }

  selectImprimante(im: Printer) {
    let index;
    index = this.myOffre.imprimanteDtoList.findIndex(item => item.id == im.id);
    console.log("ressoure modifier : ");
    if (index != -1) {
      this.myOffre.imprimanteDtoList[index].selected = !this.myOffre.imprimanteDtoList[index].selected;
      console.log(this.myOffre.imprimanteDtoList[index].selected)
    } else {
      const index = this.imprimanteWithoutOffre.findIndex(item => item.id == im.id);
      this.imprimanteWithoutOffre[index].selected = !this.imprimanteWithoutOffre[index].selected;
      console.log(this.imprimanteWithoutOffre[index].selected)
    }
  }
  selectToutOrdinateur() {
    this.offres.forEach(offre => {
      offre.ordinateurDtoList.forEach(or => {
        or.selected = !this.selectToutOrd;
      });
    })
    this.ordinateurWithoutOffre.forEach(or => {
      or.selected = !this.selectToutOrd;
    });
    this.selectToutOrd = !this.selectToutOrd;
  }
  selectToutImprimante() {
    this.offres.forEach(offre => {
      offre.imprimanteDtoList.forEach(im => {
        im.selected = !this.selectToutImp;
      });
    })
    this.imprimanteWithoutOffre.forEach(im => {
      im.selected = !this.selectToutImp;
    });
    this.selectToutImp = !this.selectToutImp;
  }

  modal_acceptSoumission(id: any) {
    this.id_acceptSoumission = id;
    this.id_deleted = -1;
  }

  accepterSoumission(id_s: any) {
    this.offreService.accepterSoumission(id_s).subscribe((data) => {
      console.log(data);
      this.myOffre.soumissionDTOList?.forEach(s => {
        if (s.id == id_s) {
          s.etat = 1;
        } else {
          s.etat = -1;
        }
      })
      this.getOffres();
    });
  }

}
