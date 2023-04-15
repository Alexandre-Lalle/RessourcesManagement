import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TechnicianSideBarComponent } from './technician-side-bar.component';

describe('TechnicianSideBarComponent', () => {
  let component: TechnicianSideBarComponent;
  let fixture: ComponentFixture<TechnicianSideBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TechnicianSideBarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TechnicianSideBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
