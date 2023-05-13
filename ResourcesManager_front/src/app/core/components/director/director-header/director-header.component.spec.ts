import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DirectorHeaderComponent } from './director-header.component';

describe('DirectorHeaderComponent', () => {
  let component: DirectorHeaderComponent;
  let fixture: ComponentFixture<DirectorHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DirectorHeaderComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DirectorHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
