import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TechnicianLayoutComponent } from './technician-layout.component';

describe('TechnicianLayoutComponent', () => {
  let component: TechnicianLayoutComponent;
  let fixture: ComponentFixture<TechnicianLayoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TechnicianLayoutComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TechnicianLayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
