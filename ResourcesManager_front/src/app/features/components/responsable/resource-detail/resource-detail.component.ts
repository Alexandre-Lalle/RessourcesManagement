import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, catchError, of, switchMap } from 'rxjs';
import { Computer } from 'src/app/features/models/computer.model';
import { Printer } from 'src/app/features/models/printer.model';
import { Resource } from 'src/app/features/models/resource.model';
import { ResponsableService } from 'src/app/features/services/responsable.service';

@Component({
  selector: 'app-resource-detail',
  templateUrl: './resource-detail.component.html',
  styleUrls: ['./resource-detail.component.css']
})
export class ResourceDetailComponent implements OnInit {
  computer!: Computer;
  computer$!: Observable<Computer>;
  printer!: Printer;
  printer$!: Observable<Printer>;
  resourceId!: number;
  resourceType!: string;

  constructor(private responsableService: ResponsableService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.resourceId = +this.route.snapshot.params['id'];
    this.resourceType = this.route.snapshot.params['type'];

    if (this.resourceType === 'computer') {
      this.computer$ = this.responsableService.findComputerById(this.resourceId);
    }
    else
      this.printer$ = this.responsableService.findPrinterById(this.resourceId);
  }

  isComputer(): boolean {

    return this.resourceType === 'computer';
  }

  isPrinter(): boolean {
    return this.resourceType === 'printer';
  }

  cancelChanges() {
    // Rafraîchir les données dans le formulaire avec les données d'origine
    if (this.isComputer()) {
      this.computer$ = this.responsableService.findComputerById(this.resourceId);
    }
    else
      this.printer$ = this.responsableService.findPrinterById(this.resourceId);
  }


  saveComputerChanges(computer: Computer) {
    const confirmChanges = confirm("Êtes-vous sûr de vouloir enregistrer les modifications ?");
    if (confirmChanges) {
      this.responsableService.updateComputer(computer.id, computer).pipe(
        catchError((error) => {
          console.error(error);
          return of(null);
        })
      ).subscribe(
        (response) => {
          if (response) {
            // Mettre à jour les données dans le tableau
            console.log(response);
          } else {
            console.error("Une erreur s'est produite lors de l'enregistrement des modifications.");
          }
        }
      );
    }
  }

  savePrinterChanges(printer: Printer) {
    const confirmChanges = confirm("Êtes-vous sûr de vouloir enregistrer les modifications ?");
    if (confirmChanges) {
      this.responsableService.updatePrinter(printer.id, printer).pipe(
        catchError((error) => {
          console.error(error);
          return of(null);
        })
      ).subscribe(
        (response) => {
          if (response) {
            // Mettre à jour les données dans le tableau
            console.log(response);
          } else {
            console.error("Une erreur s'est produite lors de l'enregistrement des modifications.");
          }
        }
      );
    }
  }

  deleteResource() {
    const confirmChanges = confirm("Êtes-vous sûr de vouloir supprimer la ressource ?");
    if (confirmChanges) {
      if (this.isComputer()) {

        this.computer$.pipe(
          switchMap(async (computer) => this.responsableService.deleteComputer(computer.id)),
          catchError((error) => {
            console.error(error);
            return of(null);
          })
        ).subscribe(
          (response) => {
            if (response) {
              console.log(`La ressource a été supprimée.`);
              this.router.navigateByUrl(`resources-list`);

            } else {
              console.error("Une erreur s'est produite lors de la suppression de la ressource.");
            }
          }
        );

      } else if (this.isPrinter()) {
        this.printer$.pipe(
          switchMap(async (printer) => this.responsableService.deletePrinter(printer.id)),
          catchError((error) => {
            console.error(error);
            return of(null);
          })
        ).subscribe(
          (response) => {
            if (response) {
              console.log(`La ressource a été supprimée.`);
              this.router.navigateByUrl(`resources-list`);

            } else {
              console.error("Une erreur s'est produite lors de la suppression de la ressource.");
            }
          }
        );

      }
    }
  }


  getStateLabel(data: any): string {
    switch (data?.state) {
      case 1:
        return 'Disponible';
      case 0:
        return 'En cours de traitement';
      case -1:
        return 'Indisponible';
      default:
        return 'Indisponible';
    }
  }

}
