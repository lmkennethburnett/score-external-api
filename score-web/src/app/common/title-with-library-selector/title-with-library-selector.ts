import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges, ViewEncapsulation} from '@angular/core';
import {Library} from '../../library-management/domain/library';
import {ColumnSelectorModule} from '../column-selector/column-selector.module';
import {MatCardSubtitle} from '@angular/material/card';
import {MatToolbar} from '@angular/material/toolbar';
import {NgIf} from '@angular/common';

@Component({
  selector: 'score-title-with-library-selector',
  templateUrl: './title-with-library-selector.html',
  styleUrls: ['./title-with-library-selector.css'],
  encapsulation: ViewEncapsulation.None,
  imports: [
    ColumnSelectorModule,
    MatCardSubtitle,
    MatToolbar,
    NgIf
  ],
  standalone: true
})
export class TitleWithLibrarySelector implements OnInit, OnChanges {

  @Input() title: string;
  @Input() subtitle: string;
  @Input() libraries: { library: Library, selected: boolean }[] = [];
  @Output() libraryChange = new EventEmitter<Library>();

  filterLibraries: {
    name: string;
    selected: boolean;
  }[] = [];

  ngOnInit(): void {
    this.initializeFilterLibraries();
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes.libraries) {
      this.initializeFilterLibraries();
    }
  }

  private initializeFilterLibraries(): void {
    this.filterLibraries = this.libraries.map(e => ({
      name: e.library.name,
      selected: e.selected
    }));
  }

  get selectedLibrary(): Library | undefined {
    const selectedLibraries = this.libraries.filter(e => e.selected).map(e => e.library);
    return selectedLibraries.length > 0 ? selectedLibraries[0] : undefined;
  }

  onFilterLibraryChange(updatedColumns: { name: string; selected: boolean }[]) {
    let selectedLibrary;
    updatedColumns.filter(c => c.selected).forEach(c => {
      this.libraries.forEach(l => {
        if (c.name === l.library.name) {
          l.selected = true;
          selectedLibrary = l.library;
        } else {
          l.selected = false;
        }
      });
    });

    this.libraryChange.emit(selectedLibrary);
  }

}
