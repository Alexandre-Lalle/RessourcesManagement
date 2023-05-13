import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Report } from 'src/app/features/models/report'; 
import { ConsulterReportService } from 'src/app/features/services/consulter-pannes.service'; 
import { DemandeRetour } from 'src/app/features/models/demandeRetour';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-consulter-pannes',
  templateUrl: './consulter-pannes.component.html',
  styleUrls: ['./consulter-pannes.component.css']
})
export class ConsulterPannesComponent implements OnInit {
   public reports: Report[]=[];
   public demandes:DemandeRetour[]=[];
   public reportInfo:Report|null;
   public demandeRet:DemandeRetour|null;
   public dateReport:Date|null;
   public finddemandeRet:DemandeRetour|null;
   i: number = 0;
   constructor(private reportService:ConsulterReportService){
      this.reportInfo=null;
      this.demandeRet=null;
      this.dateReport=null;
      this.finddemandeRet=null;
   }



  ngOnInit(){
      this.getReports();
      console.log("hello");
     
  }
  isBtnDisabled: boolean[] = [];

  public getReports(): void {
    
    this.reportService.getReports().subscribe(
      (response: Report[]) => {
        this.reports = response;
        this.isBtnDisabled = new Array<boolean>(this.reports.length).fill(false);
        console.log(this.reports);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  isEnable: { [index: string]: boolean } = {};
  
  public onAddDemandeRetour(addForm: NgForm,index:number): void {
       
    document.getElementById('add-DemandeRetour-form')!.click();
      this.demandeRet = {
        id: undefined,
        message: addForm.value.message,
        resource_id: Number(addForm.value.resource_id) // conversion en Long
    };
    // this.reportService.findDemandeByRId(this.demandeRet.resource_id).subscribe(
    //   (response: DemandeRetour) => {
    //     console.log("find",response);

    //   },
    //   (error: HttpErrorResponse) => {
    //     alert(error.message);
    //   }
    // );

    
    console.log(this.demandeRet)
    console.log("index",index);
     
      if (Number.isInteger(this.demandeRet?.resource_id)) {
         this.reportService.addDemandeRetour(this.demandeRet!).subscribe(
          (response: DemandeRetour) => {
            console.log(response);
            alert("demande de retour ajouté avec succé")
            this.getReports();
            addForm.reset();
            this.isBtnDisabled[index] = true;
            console.log("btndisable",this.isBtnDisabled)
            const indexNum = index.toString();
            this.isEnable[indexNum]=true;
          },
          (error: HttpErrorResponse) => {
            alert(error.message);
            addForm.reset();
          }
        );}
      else {
        alert("L'identifiant de la ressource doit être un nombre entier.");
    }
   
    

  }
  
  public onOpenModal({ report, mode,index }: { report: Report|null; mode: string;index:number }): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
       
        this.reportInfo=report;
        button.setAttribute('data-target', '#addDemandeRetourModal');
        console.log("indexadd ",index);
       // this.isBtnDisabled[index] = true;
        this.i=index;
       
    }
    
   
    this.newMethod(container, button);
  }
  private newMethod(container: HTMLElement | null, button: HTMLButtonElement) {
    container?.appendChild(button);
    button.click();
  }
 // const dateObject = new Date(Date.parse(dateString));
  //isDetailsVisible: boolean = false;
  isDetailsVisible: { [index: string]: boolean } = {};
  toggleDetails(index:number) {
      //this.isDetailsVisible[index] = !this.isDetailsVisible[index];
      const indexNum = index.toString();
      this.isDetailsVisible[indexNum] = !this.isDetailsVisible[indexNum];
      const button = document.getElementById(`btn-details-${index}`);
      if (this.isDetailsVisible[indexNum]) {
          button!.textContent = "Moins de détails";
      } else {
         button!.textContent = "Plus de détails";
      }
  
  }

}
