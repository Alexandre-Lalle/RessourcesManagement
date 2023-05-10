import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListFailuresComponent } from './list-failures.component';

describe('ListFailuresComponent', () => {
  let component: ListFailuresComponent;
  let fixture: ComponentFixture<ListFailuresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListFailuresComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListFailuresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
