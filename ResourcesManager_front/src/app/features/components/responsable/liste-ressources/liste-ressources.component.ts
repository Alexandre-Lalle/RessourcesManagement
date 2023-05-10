import { Component, OnInit, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable, catchError, map, of, switchMap, take, tap } from 'rxjs';
import { Affectation } from 'src/app/features/models/affectation.model';
import { Computer } from 'src/app/features/models/computer.model';
import { Printer } from 'src/app/features/models/printer.model';
import { Teacher } from 'src/app/features/models/teacher.mode';
import { DemandsService } from 'src/app/features/services/demand/demand.service';
import { ResponsableService } from 'src/app/features/services/responsable.service';

@Component({
  selector: 'app-liste-ressources',
  templateUrl: './liste-ressources.component.html',
  styleUrls: ['./liste-ressources.component.css']
})
export class ListeRessourcesComponent implements OnInit {

  computers$!: Observable<Computer[]>;
  printers$!: Observable<Printer[]>;
  selectedStatus1: string = '';
  selectedStatus2: string = '';
  affectation$!: Observable<Affectation>;
  departments$!: Observable<string[]>;
  teachers$!: Observable<Teacher[]>;
  filteredTeachers$!: Observable<Teacher[]>;
  selectedDepartment!: string;
  selectedTeacher!: Teacher;
  teacherForm!: FormGroup;

  constructor(private fb: FormBuilder, private responsableService: ResponsableService, private demandsService: DemandsService, private router: Router) { }

  ngOnInit(): void {
    this.computers$ = this.responsableService.findComputers();
    this.printers$ = this.responsableService.findPrinters();

    this.departments$ = this.demandsService.getDepartments();
  }

  ngOnChanges(changes: SimpleChanges): void {
    // détecte les changements de la propriété selectedStatus
    if (changes['selectedStatus1'] || changes['selectedStatus2']) {
      this.filterData();
    }
    if (changes['selectedDepartment']) {
      this.onDepartmentSelect();
    }
  }

  filterData(): void {
    // retourne les données filtrées en fonction de la valeur sélectionnée
    if (this.selectedStatus1 === "available" || this.selectedStatus2 === "available") {
      this.computers$ = this.responsableService.findComputersByState(1);
      this.printers$ = this.responsableService.findPrintersBySate(1);

    }
    else if (this.selectedStatus1 === "unavailable" || this.selectedStatus2 === "unavailable") {
      this.computers$ = this.responsableService.findComputersByState(-1);
      this.printers$ = this.responsableService.findPrintersBySate(-1);
    }
    else if (this.selectedStatus1 === "processing" || this.selectedStatus2 === "processing") {
      this.computers$ = this.responsableService.findComputersByState(0);
      this.printers$ = this.responsableService.findPrintersBySate(0);
    }
    else {
      this.computers$ = this.responsableService.findComputers();
      this.printers$ = this.responsableService.findPrinters();
    }
  }

  onStatusSelected(): void {
    // appelle filterData() chaque fois que la valeur sélectionnée change
    this.filterData();
  }

  onConsultResource(id: number, type: 'computer' | 'printer'): void {
    this.router.navigateByUrl(`manager/resource-detail/${type}/${id}`);
  }

  getStateLabel(data: any): string {
    switch (data.state) {
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

  ////////////////////////////////////////////////////


  getAffectation(resource: Computer | Printer) {
    if (!resource || typeof resource.id !== 'number') {
      console.error('resource id is null');
      return;
    }

    console.log('resource : ', resource);

    this.responsableService.getAffectationByResourceId(resource.id).pipe(
      catchError(error => {
        console.error(error);
        return of(null);
      })
    ).subscribe(affectation => {
      if (affectation) {
        this.affectation$ = of(affectation);
        console.log("TTT ", affectation);
      } else {
        const newAffectation: Affectation = {
          dateAffectation: new Date(),
          teacherList: [],
          resourceId: resource.id
        };
        this.affectation$ = of(newAffectation);
        console.log('Nouvelle affectation créée : ', newAffectation);
      }
    });
  }

  saveAAffectation() {
    console.log("iiiiiiiii")
    this.affectation$.pipe(
      switchMap(affectation => {
        // Vérifier si une affectation existe déjà pour cette resource
        return this.responsableService.getAffectationByResourceId(affectation.resourceId).pipe(
          switchMap(existingAffectation => {
            if (existingAffectation) {
              affectation.id = existingAffectation.id;
              console.log("iiiiiiiii")
              // Une affectation existe déjà, mettre à jour l'affectation existante
              return this.responsableService.updateAffectation(affectation);
            } else {
              console.log("eeeeeeeeeeee")
              // Aucune affectation existante, créer une nouvelle affectation
              return this.responsableService.addAffectation(affectation);
            }
          }),
          tap(() => {
            // afficher le message de succès ici
            alert('L\'affectation a été sauvegardée avec succès');
          })
        );
      })
    );
  }

  async saveAAAffectation() {
    try {
      const affectation = await this.affectation$.pipe(
        switchMap(affectation => {
          // Vérifier si une affectation existe déjà pour cette resource
          return this.responsableService.getAffectationByResourceId(affectation.resourceId).pipe(
            switchMap(existingAffectation => {
              if (existingAffectation) {
                affectation.id = existingAffectation.id;
                // Une affectation existe déjà, mettre à jour l'affectation existante
                return this.responsableService.updateAffectation(affectation);
              } else {
                // Aucune affectation existante, créer une nouvelle affectation
                return this.responsableService.addAffectation(affectation);
              }
            }),
            tap(() => {
              // afficher le message de succès ici
              alert('L\'affectation a été sauvegardée avec succès');
            })
          );
        })
      ).toPromise();

      // Faire quelque chose avec affectation si nécessaire
    } catch (error) {
      // Gérer l'erreur ici
    }
  }

  saveAffectation() {
    this.affectation$.pipe(
      switchMap(affectation => {
        // Vérifier si une affectation existe déjà pour cette resource
        return this.responsableService.getAffectationByResourceId(affectation.resourceId).pipe(
          switchMap(existingAffectation => {
            if (existingAffectation) {
              affectation.id = existingAffectation.id;
              // Une affectation existe déjà, mettre à jour l'affectation existante
              return this.responsableService.updateAffectation(affectation);
            } else {
              // Aucune affectation existante, créer une nouvelle affectation
              return this.responsableService.addAffectation(affectation);
            }
          }),
          tap(() => {
            // afficher le message de succès ici
            alert('L\'affectation a été sauvegardée avec succès');
          })
        );
      })
    ); // pas besoin d'ajouter un abonnement si le code HTML s'abonne déjà à l'Observable affectation$
  }

  deleteTeacher(teacher: Teacher) {
    this.affectation$.pipe(take(1)).subscribe(affectation => {
      const teacherIndex = affectation.teacherList.findIndex(t => t.id === teacher.id);
      if (teacherIndex >= 0) {
        affectation.teacherList.splice(teacherIndex, 1);
        /*this.responsableService.updateAffectation(affectation).subscribe(() => {
          this.affectation$ = this.responsableService.getAffectationByResourceId(affectation.resource.id);
        });*/
      }
    });
  }

  onDepartmentSelect() {
    this.teachers$ = this.demandsService.getTeachers(this.selectedDepartment);
  }

  addTeacher() {
    console.log(this.selectedTeacher)
    //console.log(this.selectedTeacher.email + ' ' + this.selectedTeacher.lastName);

    this.affectation$.pipe(take(1)).subscribe(affectation => {
      const teacherExists = affectation.teacherList.some(t => t.id === this.selectedTeacher.id);
      if (!teacherExists) {
        affectation.teacherList.push(this.selectedTeacher);
        /* this.responsableService.updateAffectation(affectation).subscribe(() => {
           this.affectation$ = this.responsableService.getAffectationByResourceId(affectation.resource.id);
         });*/
      }
    });

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


