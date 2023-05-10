import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TechnicianHeaderComponent } from './technician-header.component';

describe('TechnicianHeaderComponent', () => {
  let component: TechnicianHeaderComponent;
  let fixture: ComponentFixture<TechnicianHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TechnicianHeaderComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TechnicianHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
