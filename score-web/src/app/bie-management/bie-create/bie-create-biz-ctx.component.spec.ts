import {ComponentFixture, fakeAsync, TestBed} from '@angular/core/testing';

import {BieCreateBizCtxComponent} from './bie-create-biz-ctx.component';

describe('BieCopyBizCtxComponent', () => {
  let component: BieCreateBizCtxComponent;
  let fixture: ComponentFixture<BieCreateBizCtxComponent>;

  beforeEach(fakeAsync(() => {
    TestBed.configureTestingModule({
      declarations: [BieCreateBizCtxComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BieCreateBizCtxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
