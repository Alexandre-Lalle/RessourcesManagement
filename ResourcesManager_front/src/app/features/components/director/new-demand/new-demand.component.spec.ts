import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewDemandComponent } from './new-demand.component';

describe('NewDemandComponent', () => {
  let component: NewDemandComponent;
  let fixture: ComponentFixture<NewDemandComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewDemandComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewDemandComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
