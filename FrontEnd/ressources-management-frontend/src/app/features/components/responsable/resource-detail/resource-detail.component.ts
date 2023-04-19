import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ResponsableService } from 'src/app/features/services/responsable.service';

@Component({
  selector: 'app-resource-detail',
  templateUrl: './resource-detail.component.html',
  styleUrls: ['./resource-detail.component.css']
})
export class ResourceDetailComponent implements OnInit{

  constructor(private responsableService : ResponsableService, private router : Router){}

  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }
  
}
