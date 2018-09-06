import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InviteeDetailsComponent } from './invitee-details.component';

describe('InviteeDetailsComponent', () => {
  let component: InviteeDetailsComponent;
  let fixture: ComponentFixture<InviteeDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InviteeDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InviteeDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
