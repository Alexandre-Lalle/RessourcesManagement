import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateDemandComponent } from './update-demand.component';

describe('UpdateDemandComponent', () => {
  let component: UpdateDemandComponent;
  let fixture: ComponentFixture<UpdateDemandComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateDemandComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateDemandComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
