import { TestBed } from '@angular/core/testing';
import { ConsulterFournisseurService } from './consulter-fournisseur.service';


describe('ConsulterFournisseurService', () => {
  let service: ConsulterFournisseurService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ConsulterFournisseurService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
