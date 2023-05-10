import { Component } from '@angular/core';
import { AuthService } from 'src/app/auth/services/auth.service';
import { Responsable } from 'src/app/features/models/responsable.model';

@Component({
  selector: 'app-responsable-side-bar',
  templateUrl: './responsable-side-bar.component.html',
  styleUrls: ['./responsable-side-bar.component.css']
})
export class ResponsableSideBarComponent {

  currentUser!: Responsable;


  constructor(private authService: AuthService) { }


  ngOnInit(): void {
    this.currentUser = this.authService.getUser();
  }

}
