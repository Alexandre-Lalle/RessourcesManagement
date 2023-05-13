import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsulterPannesComponent } from './consulter-pannes.component';

describe('ConsulterPannesComponent', () => {
  let component: ConsulterPannesComponent;
  let fixture: ComponentFixture<ConsulterPannesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsulterPannesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsulterPannesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
