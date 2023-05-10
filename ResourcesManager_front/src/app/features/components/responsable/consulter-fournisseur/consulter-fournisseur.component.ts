import { Component,OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { Fournisseur } from 'src/app/features/models/fournisseur';
import { ConsulterFournisseurService } from 'src/app/features/services/consulter-fournisseur.service'; 

@Component({
  selector: 'app-consulter-fournisseur',
  templateUrl: './consulter-fournisseur.component.html',
  styleUrls: ['./consulter-fournisseur.component.css']
})
export class ConsulterFournisseurComponent implements OnInit {

  public fournisseurs: Fournisseur[] = [];
  public editFournisseur: Fournisseur | null;
  public deleteFournisseur: Fournisseur | null ;
  public foundFournisseur: Fournisseur | null;
  public testFournisseur: Fournisseur | null;
  public email:String|null;

  constructor(private fournisseurService: ConsulterFournisseurService){
    this.editFournisseur=null;
    this.deleteFournisseur=null;
    this.foundFournisseur=null;
    this.email=null;
    this.testFournisseur=null;
   }
   ngOnInit() {
    this.getFournisseurs();
    console.log("hello consulter four");
   
   }
   public findFournisseurr(fournisseur:Fournisseur): void {
    this.fournisseurService.findFournisseur(fournisseur).subscribe(
      (response: Fournisseur) => {
        this.foundFournisseur = response;
        console.log(this.foundFournisseur)
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
   public getFournisseurs(): void {
    this.fournisseurService.getFournisseurs().subscribe(
      (response: Fournisseur[]) => {
        this.fournisseurs = response;
        console.log(this.fournisseurs);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onAddFournisseur(addForm: NgForm): void {
    document.getElementById('add-employee-form')!.click();
    this.fournisseurService.findFournisseur(addForm.value).subscribe(
      (response: Fournisseur) => {
        if(response !=null){
          alert("oups!!cet Fournisseur existe deja!");
          addForm.reset();
       }
       else{
        this.fournisseurService.addFournisseur(addForm.value).subscribe(
            (response: Fournisseur) => {
              console.log(response);
              alert("Fournisseur ajouté avec succé")
              addForm.reset();
              this.getFournisseurs();
              
            },
            (error: HttpErrorResponse) => {
              alert(error.message);
              addForm.reset();
            }
          );

       }
       
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
    
  }

  public onUpdateFournisseur(fournisseur: Fournisseur): void {
    this.fournisseurService.updateFournisseur(fournisseur).subscribe(
      (response: Fournisseur) => {
        console.log(response);
        this.getFournisseurs();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteFournisseur(fournisseurId: number): void {
    this.fournisseurService.deleteFournisseur(fournisseurId).subscribe(
      (response: void) => {
        console.log(response);
        this.getFournisseurs();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public searchFournisseurs(key: string): void {
    console.log(key);
    const results: Fournisseur[] = [];
    for (const fournisseur of this.fournisseurs) {
      if (fournisseur.name.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || fournisseur.userName.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || fournisseur.manager.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || fournisseur.place.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(fournisseur);
      }
    }
    this.fournisseurs = results;
    if (results.length === 0 || !key) {
      this.getFournisseurs();
    }
  }

  public onOpenModal({ fournisseur, mode }: { fournisseur: Fournisseur|null; mode: string; }): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addFournisseurModal');
    }
    if (mode === 'edit') {
      this.editFournisseur = fournisseur;
      button.setAttribute('data-target', '#updateFournisseurModal');
    }
    if (mode === 'delete') {
      this.deleteFournisseur= fournisseur;
      button.setAttribute('data-target', '#deleteFournisseurModal');
    }
   
    this.newMethod(container, button);
  }
  private newMethod(container: HTMLElement | null, button: HTMLButtonElement) {
    container?.appendChild(button);
    button.click();
  }

}
