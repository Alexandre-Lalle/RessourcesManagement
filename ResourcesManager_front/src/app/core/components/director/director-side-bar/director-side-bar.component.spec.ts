import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DirectorSideBarComponent } from './director-side-bar.component';

describe('DirectorSideBarComponent', () => {
  let component: DirectorSideBarComponent;
  let fixture: ComponentFixture<DirectorSideBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DirectorSideBarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DirectorSideBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
