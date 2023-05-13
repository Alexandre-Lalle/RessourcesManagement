import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ValidationErrors } from '@angular/forms';
import { ComputerDemand } from 'src/app/features/models/computer-demand.model';
import { DemandForm } from 'src/app/features/models/demandForm.model';
import { Teacher } from 'src/app/features/models/teacher.mode';
import { DemandsService } from 'src/app/features/services/demand/demand.service';
import { PrinterDemand } from 'src/app/features/models/printer-demad.model';
import { DatePipe } from '@angular/common';
import { Director } from 'src/app/features/models/director.model';
import { state } from 'src/app/features/models/state.enum';

@Component({
  selector: 'app-new-demand',
  templateUrl: './new-demand.component.html',
  styleUrls: ['./new-demand.component.css']
})
export class NewDemandComponent implements OnInit {
  addFormGroup!: FormGroup;
  demand!: DemandForm;
  currentUser = { department: "data_science" };
  teachers!: Array<Teacher>;
  all: Director = { id: NaN, firstName: "", lastName: "", password: "", email: "", department: "allDepartment", type: "" }
  teacherMails!: Array<string>
  allP!: number;
  allC!: number;




  constructor(private demandsService: DemandsService, private fb: FormBuilder, private datePipe: DatePipe) { }

  ngOnInit(): void {
    this.allC = 0;
    this.allP = 0;

    this.getTeachers();

    let controls = {
      printer: this.fb.group({
        'name': ['', [Validators.minLength(2), Validators.maxLength(20)]],
        'printSpeed': ['', [Validators.required]],
        'resolution': ['', [Validators.required]]
      }),
      computer: this.fb.group({
        'name': ['', [Validators.minLength(2), Validators.maxLength(20)]],
        'disk': ['', [Validators.required]],
        'ram': ['', [Validators.required]],
        'cpu': ['', [Validators.required]],
        'screen': ['', [Validators.required]]
      }),
      'teachers': [this.all, [Validators.required]]
    }

    this.addFormGroup = this.fb.group(controls);
  }

  public getTeachers(): Array<string> {
    this.demandsService.getTeachers(this.currentUser.department).subscribe((data) => {
      this.teachers = data;
      return data;
    }, (err) => {
      console.log("Error while fetching the teacher mails : \n" + err);
    })
    return [];
  }

  public getTeacherMails(): Array<string> {
    this.demandsService.getTeacherMails(this.currentUser.department).subscribe((data) => {
      this.teacherMails = data;
      return data;
    }, (err) => {
      console.log("Error while fetching the teacher mails : \n" + err);
    })
    return [];
  }


  public addDemand(demand: DemandForm) {
    console.log("the content : ", this.addFormGroup.value, "\n", demand);

    let demandp: PrinterDemand, demandc: ComputerDemand, teachers = new Array<Teacher>, added = false;

    (demand.teachers.department == "allDepartment") ? teachers = this.teachers : teachers.push(demand.teachers);

    console.log("teachers : ", teachers);

    if (demand.printer.printSpeed) {

      demandp = { teachers: teachers, printer: demand.printer };
      this.demandsService.addPrinterDemand(demandp).subscribe((resp) => {
        added = resp;
        resp ? alert("la demande a ete ajoutée avec succes !") : alert("la demande n'a pas etée ajoutée !");
      });
    }

    if (demand.computer.disk) {

      demandc = { teachers: teachers, computer: demand.computer };
      this.demandsService.addComputerDemand(demandc).subscribe((resp) => {
        added = resp;
        resp ? alert("la demande a ete ajoutée avec succes !") : alert("la demande n'a pas etée ajoutée !");
      });
    }
  }



  getErrorMessage(fieldName: string, errors: any) {
    if (errors['required']) {
      return "*" + fieldName + " is required !";
    } else if (errors['min']) {
      return "*" + fieldName + " must be greater than(or equal) " + errors['min']['min'];
    } else if (errors['max']) {
      return "*" + fieldName + " must be lower than(or equal) " + errors['max']['max'] + " (ex: " + 0.12 * errors['max']['max'] + ")";
    }
    else if (errors['minlength']) {
      return "*" + fieldName + " should have at least " + errors['minlength']['requiredLength'] + " characters !"
    } else return "";
  }


  private isFormEmpty(form: FormGroup, element: string): boolean {
    return Object.keys(form.controls[element]).every(key => {
      const control = form.controls[key];
      return !control.value || control.value === '';
    });
  }

  public setAllP(value: any) {
    console.log("the value : ", value)

    this.allP = this.allP + 1
    value == "" ? this.allP = this.allP + 1 : this.allP = this.allP + 1;
    console.log("ctr : ", this.allC);
  }

  public setAllC(value: any) {
    value == "" ? this.allC -= 1 : this.allC += 1;
  }

  disableComputerForm() {

  }

  disablePrinterForm() {

  }
}
