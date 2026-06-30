import { Component } from '@angular/core';
import { HashtableVisualizer } from './hashtable-visualizer/hashtable-visualizer';

@Component({
  selector: 'app-root',
  imports: [HashtableVisualizer],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {}