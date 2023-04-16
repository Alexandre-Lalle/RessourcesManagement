import { Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/services/auth.service';
import { Provider } from 'src/app/features/models/provider.model';

@Component({
  selector: 'app-fournisseur-header',
  templateUrl: './fournisseur-header.component.html',
  styleUrls: ['./fournisseur-header.component.css']
})
export class FournisseurHeaderComponent implements OnInit{
  currentUser!:Provider;

  constructor(private authService:AuthService, private router:Router){}


  ngOnInit(): void {
    this.currentUser =  this.authService.getUser();
  }


  logout(){
    const confirmation = confirm("Voullez vous vraiment vous déconnecter ?") 
    if(confirmation){
      this.authService.clear();
      this.router.navigate(['login']);
    }
  }
}
