import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OtherTicketedEventComponent } from './other-ticketed-event.component';

describe('OtherTicketedEventComponent', () => {
  let component: OtherTicketedEventComponent;
  let fixture: ComponentFixture<OtherTicketedEventComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OtherTicketedEventComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OtherTicketedEventComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
