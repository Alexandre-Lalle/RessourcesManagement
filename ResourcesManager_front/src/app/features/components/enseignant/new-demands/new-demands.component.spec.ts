import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewDemandsComponent } from './new-demands.component';

describe('NewDemandsComponent', () => {
  let component: NewDemandsComponent;
  let fixture: ComponentFixture<NewDemandsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewDemandsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewDemandsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
