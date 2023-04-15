import { Component, OnInit } from '@angular/core';
import { Failure } from 'src/app/features/models/failure.model';
import { FailureService } from 'src/app/features/services/failure/failure.service';

@Component({
  selector: 'app-list-failures',
  templateUrl: './list-failures.component.html',
  styleUrls: ['./list-failures.component.css']
})
export class ListFailuresComponent implements OnInit{
  failures!:Array<Failure>;


  constructor(private failureService:FailureService){}

  ngOnInit(): void {
      this.failureService.getFailures().subscribe((data)=>{
        this.failures = data;
      },(err)=>{
        console.log("Error while fetching the failures : \n",err)
      });
  }
}
