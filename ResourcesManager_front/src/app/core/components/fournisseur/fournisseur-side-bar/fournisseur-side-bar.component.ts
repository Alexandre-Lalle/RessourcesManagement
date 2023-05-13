import { Component } from '@angular/core';
import { AuthService } from 'src/app/auth/services/auth.service';
import { Provider } from 'src/app/features/models/provider.model';

@Component({
  selector: 'app-fournisseur-side-bar',
  templateUrl: './fournisseur-side-bar.component.html',
  styleUrls: ['./fournisseur-side-bar.component.css']
})
export class FournisseurSideBarComponent {
  currentUser!: Provider;


  constructor(private authService: AuthService) { }


  ngOnInit(): void {
    this.currentUser = this.authService.getUser();
  }
}
