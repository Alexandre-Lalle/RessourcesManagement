import { Component, OnInit } from '@angular/core';
import { Failure } from 'src/app/features/models/failure.model';
import { Report } from 'src/app/features/models/report.model';
import { FailureService } from 'src/app/features/services/failure/failure.service';

@Component({
  selector: 'app-list-failures',
  templateUrl: './list-failures.component.html',
  styleUrls: ['./list-failures.component.css']
})
export class ListFailuresComponent implements OnInit {
  failures!: Array<Failure>;


  constructor(private failureService: FailureService) { }

  ngOnInit(): void {
    this.failureService.getFailures().subscribe((data) => {
      this.failures = data;
    }, (err) => {
      console.log("Error while fetching the failures : \n", err)
    });
  }

  public markFailureAsSeen(failure: Failure) {
    let confirmation = confirm("Voulez vous vraiment marker cette pannes comme traitée ?")
    if (confirmation) {

      // let report: Report = { failure: failure };
      failure.processed = true;
      console.log("the failure : ", failure)

      this.failureService.updateFailure(failure).subscribe((resp) => {
        resp ? alert("Le constat a eté marké avec succés !") : alert("Le constat n'a pas eté marké !!");
        let index = this.failures.indexOf(failure);
        this.failures[index].processed = resp;
        // if (resp) {
        //   alert("Le constat a eté marké avec succés !")
        //   failure.state = true;
        // } else
        //   alert("Le constat n'a pas eté marké !!");
      }, (err) => {
        console.log("Error while marking the failures : \n", err);
      })
    }
  }
}
