import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ComputerDemand } from 'src/app/features/models/computer-demand.model';
import { Demand } from 'src/app/features/models/demand.model';
import { DemandForm } from 'src/app/features/models/demandForm.model';
import { Director } from 'src/app/features/models/director.model';
import { PrinterDemand } from 'src/app/features/models/printer-demad.model';
import { Teacher } from 'src/app/features/models/teacher.mode';
import { DemandsService } from 'src/app/features/services/demand/demand.service';

@Component({
  selector: 'app-update-demand',
  templateUrl: './update-demand.component.html',
  styleUrls: ['./update-demand.component.css']
})
export class UpdateDemandComponent implements OnInit {
  updateFormGroup!: FormGroup;
  demand!: Demand;
  computerDemand!: ComputerDemand;
  printerDemand!: PrinterDemand;
  currentUser = { department: "data_science" };
  all: Director = { id: NaN, firstName: "", lastName: "", password: "", email: "", department: "allDepartment", type: "" }
  teachers!: Teacher[];




  constructor(private demandsService: DemandsService, private fb: FormBuilder, private route: ActivatedRoute) { }


  ngOnInit(): void {
    let controls = {
      printer: this.fb.group({
        name: this.fb.control(''),
        printSpeed: this.fb.control(0),
        resolution: this.fb.control('')
      }),
      computer: this.fb.group({
        name: this.fb.control(''),
        disk: this.fb.control(''),
        ram: this.fb.control(0),
        cpu: this.fb.control(''),
        screen: this.fb.control('')
      }),
      teachers: this.fb.control('')
    }

    this.route.params.subscribe((params) => {
      this.demandsService.getDemand(params["resourceId"]).subscribe((data) => {
        this.demand = data;
        if (data.resource.disk) {
          this.computerDemand = { teachers: data.teachers, computer: data.resource };
          this.setControlls(data.teachers, 1);

        } else {
          this.printerDemand = { teachers: data.teachers, printer: data.resource };
          this.setControlls(data.teachers, 0);

        }
        console.log("demand : ", this.printerDemand, this.demand);
      }, (err) => {
        console.log("Error while fetching the demand : \n", err);
      })
    })

    this.updateFormGroup = this.fb.group(controls);

    this.demandsService.getTeachers(this.currentUser.department).subscribe((data) => {
      this.teachers = data;
    })
  }


  private setControlls(teachers: Teacher[], control: number) {
    console.log("demandForm : ", this.demand, this.printerDemand);

    let pr = this.printerDemand.printer, cp = this.computerDemand?.computer;

    this.updateFormGroup = this.fb.group({
      printer: this.fb.group({
        'name': [pr.providerName, [Validators.minLength(2), Validators.maxLength(20)]],
        'printSpeed': [pr.printSpeed, [Validators.required]],
        'resolution': [pr.resolution, [Validators.required]]
      }),
      computer: this.fb.group({
        'name': [cp?.providerName, [Validators.minLength(2), Validators.maxLength(20)]],
        'disk': [cp?.disk, [Validators.required]],
        'ram': [cp?.ram, [Validators.required]],
        'cpu': [cp?.cpu, [Validators.required]],
        'screen': [cp?.screen, [Validators.required]]
      }),
      'teachers': [this.all, [Validators.required]]
    })
  }



  public updateDemand(demand: DemandForm) {
    console.log("demandForm : ", demand);

    let demandp: PrinterDemand, demandc: ComputerDemand, teachers = new Array<Teacher>, added = false;

    if (this.printerDemand) {
      demandp = { teachers: this.demand.teachers, printer: demand.printer };
      demandp.printer.id = this.demand.resource.id;
      this.demandsService.addPrinterDemand(demandp).subscribe((resp) => {
        added = resp;
        alert("la demande a ete modifiee avec succes : " + added);
      });
    }
    else if (this.computerDemand) {
      demandc = { teachers: this.demand.teachers, computer: demand.computer };
      demandc.computer.id = this.demand.resource.id;
      this.demandsService.addComputerDemand(demandc).subscribe((resp) => {
        added = resp;
        alert("la demande a ete modifiee avec succes" + added);
      });
    }


    console.log("teachers : ", teachers);
  }



  public getErrorMessage(filedName: string, errors: any) {
    if (errors['required']) {
      return "* the " + filedName + " is required !";
    }
    else if (errors['minlength']) {
      return "* the " + filedName + " should have at least " + errors['minlength']['requiredLength'] + " characters !"
    }
    else if (errors['maxlength']) {
      return "* the " + filedName + " shouldn't have more then " + errors['maxlength']['requiredLength'] + " characters !"
    }
    else return "";
  }
}
