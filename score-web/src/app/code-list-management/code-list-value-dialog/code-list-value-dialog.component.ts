import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {CodeListValue} from '../domain/code-list';
import {hashCode} from '../../common/utility';

@Component({
  selector: 'score-code-list-value-dialog',
  templateUrl: './code-list-value-dialog.component.html',
  styleUrls: ['./code-list-value-dialog.component.css']
})
export class CodeListValueDialogComponent implements OnInit {

  _hashCode;
  isAddAction;
  actionName;
  codeListValue: CodeListValue;
  lastRevisionValue: CodeListValue;
  isEditable = false;

  constructor(
    public dialogRef: MatDialogRef<CodeListValueDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {

    this.codeListValue = data.codeListValue;
    this.lastRevisionValue = data.lastRevisionValue;
    this.isEditable = data.isEditable;

    this._hashCode = hashCode(this.codeListValue);
  }

  get hasRevision(): boolean {
    return this.lastRevisionValue !== undefined;
  }

  get revisionDeprecated(): boolean {
    return (!!this.lastRevisionValue && this.lastRevisionValue.deprecated);
  }

  get isUsedBefore(): boolean {
    return (!!this.lastRevisionValue && this.lastRevisionValue.used);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  ngOnInit() {
    if (!this.isAvailable(this.codeListValue)) {
      this.actionName = 'View';
      return;
    }

    this.isAddAction = (this.codeListValue.guid === undefined);
    if (this.isAddAction) {
      this.actionName = 'Add';
    } else {
      this.actionName = 'Edit';
    }
  }

  isDisabled() {
    return (this.codeListValue.value === undefined || this.codeListValue.value === '') ||
      (this.codeListValue.meaning === undefined || this.codeListValue.meaning === '') ||
      !this.isDirty();
  }

  color(codeListValue: CodeListValue): string {
    if (codeListValue.locked) {
      return 'bright-red';
    }

    if (codeListValue.used) {
      return 'blue';
    }

    return 'dull-red';
  }

  isDisabledColor(codeListValue: CodeListValue) {
    return this.color(codeListValue) !== 'green';
  }

  isAvailable(codeListValue: CodeListValue): boolean {
    return this.color(codeListValue) !== 'bright-red' || this.color(codeListValue) !== 'dull-red';
  }

  isDirty(): boolean {
    return this._hashCode !== hashCode(this.codeListValue);
  }

}
