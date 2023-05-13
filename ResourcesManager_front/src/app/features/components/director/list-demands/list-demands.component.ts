import { Component, OnInit } from '@angular/core';
import { DemandsService } from 'src/app/features/services/demand/demand.service';
import { Demand } from 'src/app/features/models/demand.model';
import { timeout } from 'rxjs';

@Component({
  selector: 'app-list-demands',
  templateUrl: './list-demands.component.html',
  styleUrls: ['./list-demands.component.css']
})
export class ListDemandsComponent implements OnInit {
  demands!: Array<Demand>;
  selectedDemand!: Demand;


  constructor(private demandService: DemandsService) { }

  ngOnInit(): void {
    setTimeout(() => {
      this.fetchDemends();
    }, 300);
  }

  public async fetchDemends(): Promise<Demand[]> {
    try {
      const data = await this.demandService.getAllDemands().toPromise();
      this.demands = data;
      console.log(this.demands);
      return data;
    } catch (err) {
      console.log("Error while fetching the demands from the API:\n" + err);
      return [];
    }
  }


  deleteDemand(demand: Demand) {
    let confirmation = confirm("Voullez vous vraiment supprimer cette demande ?");
    let index = this.demands.indexOf(demand);
    if (confirmation) {
      this.demandService.deleteDemand(demand).subscribe((res) => {
        this.demands.splice(index, 1);
        alert("La demande a ete supprimée avec succes ! : " + res);
      })
    }
  }

  public sendUnavailableDemands() {
    let confirmation = confirm("Confirmer l'envoye des demandes ?");
    if (confirmation) {
      this.demandService.sendUnavailableDemands().subscribe((res) => {
        res ? alert("Les demandes ont etées envoyées avec succes !") : alert("Les demandes n'ont pas etées envoyées");
      })
    }
  }
}
