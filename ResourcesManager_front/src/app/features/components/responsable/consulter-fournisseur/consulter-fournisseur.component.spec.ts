import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsulterFournisseurComponent } from './consulter-fournisseur.component';

describe('ConsulterFournisseurComponent', () => {
  let component: ConsulterFournisseurComponent;
  let fixture: ComponentFixture<ConsulterFournisseurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsulterFournisseurComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsulterFournisseurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
