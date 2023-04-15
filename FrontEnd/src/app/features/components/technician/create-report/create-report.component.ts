import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Failure } from 'src/app/features/models/failure.model';
import { Report } from 'src/app/features/models/report.model';
import { User } from 'src/app/features/models/user.model';
import { FailureService } from 'src/app/features/services/failure/failure.service';

@Component({
  selector: 'app-create-report',
  templateUrl: './create-report.component.html',
  styleUrls: ['./create-report.component.css']
})
export class CreateReportComponent implements OnInit{
  addFormGroup!:FormGroup;
  currentUser!:{department:""};
  report!:Report;
  failureId!:number;
  controls!:any;


  constructor(private failureService:FailureService, private route:ActivatedRoute, private fb:FormBuilder, private datePipe:DatePipe){}


  ngOnInit(): void {

    this.controls={
      id:this.fb.control(null),
      date:this.fb.control(this.datePipe.transform(new Date(),'yyyy-MM-dd')),
      frequency:this.fb.control(''),
      order:this.fb.control(''),
      explication:this.fb.control(null)
    }

    this.route.params.subscribe((params)=>{
      this.failureId = params['failureId'];
      this.failureService.getReport(params['failureId']).subscribe((data)=>{
        this.report = data;
        this.report.failure.id = params['failureId'];
        this.setControls();
      },(err)=>{
        console.log("Error while fetching the report : \n"+err);
      })
    })

    this.addFormGroup = this.fb.group(this.controls);
  }


  private setControls(){
    let date = this.datePipe.transform(this.report.date,'yyyy-MM-dd');
    this.report.date = date as string;
    this.addFormGroup = this.fb.group(this.report);
  }


  saveReport(report:Report){
    report.failure = {id:this.failureId,date:new Date()};
    this.failureService.updateReport(report).subscribe((rep)=>{
      alert("the report was updated : "+rep)
    })
  }

  deleteReport(report:Report){
    let confirmation = confirm("Voullez vous vraiment supprimer ce constat ?");
    if(confirmation){
      this.failureService.deleteReport(report).subscribe((resp)=>{
        alert("le constat a ete supprime avec succes : "+resp);
      })
    }
  }
}
