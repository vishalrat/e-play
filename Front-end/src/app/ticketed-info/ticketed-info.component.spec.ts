import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketedInfoComponent } from './ticketed-info.component';

describe('TicketedInfoComponent', () => {
  let component: TicketedInfoComponent;
  let fixture: ComponentFixture<TicketedInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TicketedInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TicketedInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
