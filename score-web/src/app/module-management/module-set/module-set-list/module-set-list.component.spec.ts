import {ComponentFixture, fakeAsync, TestBed} from '@angular/core/testing';

import {ModuleSetListComponent} from './module-set-list.component';

describe('ModuleSetListComponent', () => {
  let component: ModuleSetListComponent;
  let fixture: ComponentFixture<ModuleSetListComponent>;

  beforeEach(fakeAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ModuleSetListComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModuleSetListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
