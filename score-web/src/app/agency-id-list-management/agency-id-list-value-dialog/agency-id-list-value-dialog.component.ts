import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {AgencyIdListValue} from '../domain/agency-id-list';
import {hashCode} from '../../common/utility';

@Component({
  selector: 'score-agency-id-list-value-dialog',
  templateUrl: './agency-id-list-value-dialog.component.html',
  styleUrls: ['./agency-id-list-value-dialog.component.css']
})
export class AgencyIdListValueDialogComponent implements OnInit {

  _hashCode;
  isAddAction;
  actionName;
  agencyIdListValue: AgencyIdListValue;
  lastRevisionValue: AgencyIdListValue;
  agencyId: number;
  isEditable = false;

  constructor(
    public dialogRef: MatDialogRef<AgencyIdListValueDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {

    this.agencyIdListValue = data.agencyIdListValue;
    this.lastRevisionValue = data.lastRevisionValue;
    this.agencyId = data.agencyId;
    this.isEditable = data.isEditable;

    this._hashCode = hashCode(this.agencyIdListValue);
  }

  get hasRevision(): boolean {
    return !!this.lastRevisionValue;
  }

  get derived(): boolean {
    return !!this.agencyIdListValue.basedAgencyIdListValueManifestId;
  }

  get wasDeprecated(): boolean {
    return (!!this.lastRevisionValue && this.lastRevisionValue.deprecated);
  }

  get isDeprecatedChangeable(): boolean {
    if (this.hasRevision && !this.wasDeprecated) {
      return true;
    }
    return false;
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  ngOnInit() {
    if (!this.isEditable) {
      this.actionName = 'View';
      return;
    }

    this.isAddAction = (this.agencyIdListValue.guid === undefined);
    if (this.isAddAction) {
      this.actionName = 'Add';
    } else {
      this.actionName = 'Edit';
    }
  }

  isDisabled() {
    return (this.agencyIdListValue.value === undefined || this.agencyIdListValue.value === '') ||
      (this.agencyIdListValue.name === undefined || this.agencyIdListValue.name === '') ||
      !this.isDirty();
  }

  isDirty(): boolean {
    return this._hashCode !== hashCode(this.agencyIdListValue);
  }

}
