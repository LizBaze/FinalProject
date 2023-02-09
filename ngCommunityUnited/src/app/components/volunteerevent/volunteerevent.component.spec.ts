import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VolunteereventComponent } from './volunteerevent.component';

describe('VolunteereventComponent', () => {
  let component: VolunteereventComponent;
  let fixture: ComponentFixture<VolunteereventComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VolunteereventComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VolunteereventComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
