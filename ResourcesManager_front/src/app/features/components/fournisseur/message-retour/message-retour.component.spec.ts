import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MessageRetourComponent } from './message-retour.component';

describe('MessageRetourComponent', () => {
  let component: MessageRetourComponent;
  let fixture: ComponentFixture<MessageRetourComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MessageRetourComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MessageRetourComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
