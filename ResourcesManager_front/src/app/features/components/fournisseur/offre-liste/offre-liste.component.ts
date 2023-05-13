import { Component, OnInit } from '@angular/core';
import { Offre } from 'src/app/features/models/offre';
import { Soumission } from 'src/app/features/models/soumission';
import { FournisseurService } from 'src/app/features/services/fournisseur.service';


@Component({
  selector: 'app-offre-liste',
  templateUrl: './offre-liste.component.html',
  styleUrls: ['./offre-liste.component.css']
})
export class OffreListeComponent implements OnInit {

  constructor(private fournisseurService: FournisseurService) { }
  public offres: Offre[] = []
  public offre: Offre = {
    id: 0,
    ordinateurDtoList: [],
    imprimanteDtoList: []
  };
  public soumission: Soumission = {
    marqueOrdinateur: "",
    marqueImprimante: "",
    prix: 0,
    offreDto: {
      id: 0,
      ordinateurDtoList: [],
      imprimanteDtoList: []
    }
  };
  ngOnInit(): void {
    this.getOffres();
  }
  getOffres() {
    return this.fournisseurService.getOffres().
      subscribe(offres => this.offres = offres);
  }
  setOffreID(arg0: number) {
    this.soumission.offreDto.id = arg0;
  }
  setOffre(offre: Offre) {
    this.offre = offre;
  }
  saveSoumission() {
    this.fournisseurService.saveSoumission(this.soumission).
      subscribe(soumission => {
        if (soumission != null) {
          alert("Votre soumission est ajoute avec succes");
          this.soumission = {
            marqueOrdinateur: "",
            marqueImprimante: "",
            prix: 0,
            offreDto: {
              id: 0,
              ordinateurDtoList: [],
              imprimanteDtoList: []

            }
          }

        }
        else alert("Votre demande n'est pas ajoute");
      });
  }

}
