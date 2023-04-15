import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FournisseurSideBarComponent } from './fournisseur-side-bar.component';

describe('FournisseurSideBarComponent', () => {
  let component: FournisseurSideBarComponent;
  let fixture: ComponentFixture<FournisseurSideBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FournisseurSideBarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FournisseurSideBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
