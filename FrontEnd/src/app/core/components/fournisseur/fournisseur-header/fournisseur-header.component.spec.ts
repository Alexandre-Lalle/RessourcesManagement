import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FournisseurHeaderComponent } from './fournisseur-header.component';

describe('FournisseurHeaderComponent', () => {
  let component: FournisseurHeaderComponent;
  let fixture: ComponentFixture<FournisseurHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FournisseurHeaderComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FournisseurHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
