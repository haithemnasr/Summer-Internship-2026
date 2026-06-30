import { Component, ViewChild, ElementRef, AfterViewInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormControl } from '@angular/forms';
import { Subject, timer } from 'rxjs';
import { switchMap, takeUntil } from 'rxjs/operators';
import { HashtableService } from '../hashtable.service';
@Component({
  selector: 'app-hashtable-visualizer',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './hashtable-visualizer.html',
  styleUrl: './hashtable-visualizer.css',
})
export class HashtableVisualizer implements AfterViewInit, OnDestroy {
  @ViewChild('myCanvas') canvasRef!: ElementRef<HTMLCanvasElement>;
  keyControl = new FormControl('');
  private ctx!: CanvasRenderingContext2D;
  private canvas!: HTMLCanvasElement;
  private highlightIndex = -1;
  private highlightColor = 'lightgray';
  private animation$ = new Subject<{ index: number; color: string }>();
  private destroy$ = new Subject<void>();
  tableState: (string[] | null)[] = [];

  constructor(private hashService: HashtableService) {}
  ngAfterViewInit(): void {
    this.canvas = this.canvasRef.nativeElement;
    this.canvas.width = 800;
    this.canvas.height = 610;
    this.ctx = this.canvas.getContext('2d')!;
    this.draw();
    this.updateTableState();

    this.animation$
      .pipe(
        switchMap(({ index, color }) => {
          this.highlightIndex = index;
          this.highlightColor = color;
          this.draw();

          return timer(800);
        }),
        takeUntil(this.destroy$)
      )
      .subscribe(() => {
        this.clearHighlight();
      });
  }
  onInsert(): void {
    const key = this.keyControl.value?.trim();
    if (!key) return;
    const index = this.hashService.hash(key);
    this.hashService.add(key);
    this.updateTableState();
    this.triggerAnimation(index, 'green');
    this.keyControl.setValue('');
  }
  onDelete(): void {
    const key = this.keyControl.value?.trim();
    if (!key) return;
    const index = this.hashService.hash(key);
    this.hashService.delete(key);
    this.updateTableState();
    this.triggerAnimation(index, 'red');
    this.keyControl.setValue('');
  }
  onSearch(): void {
    const key = this.keyControl.value?.trim();
    if (!key) return;
    const index = this.hashService.hash(key);
    const found = this.hashService.find(key);
    this.triggerAnimation(index, 'yellow');
    alert(found ? `Found: ${key}` : `Not found: ${key}`);
    this.keyControl.setValue('');
  }
  private draw(): void {
    const ctx = this.ctx;
    ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);
    const table = this.hashService.getTable();
    const startX = 20;
    const startY = 30;
    const indexBoxWidth = 50;
    const boxWidth = 80;
    const boxHeight = 30;
    const rowHeight = 60;
    const gap = 20;
    for (let i = 0; i < table.length; i++) {
      const y = startY + i * rowHeight;
      const isHighlighted = i === this.highlightIndex;
 
      ctx.fillStyle = isHighlighted ? this.highlightColor : '#ffffff';
      ctx.fillRect(startX, y, indexBoxWidth, boxHeight);
      ctx.strokeRect(startX, y, indexBoxWidth, boxHeight);
 
      ctx.fillStyle = 'black';
      ctx.fillText(`[${i}]`, startX + 10, y + 20);

      let current = table[i];
      let x = startX + indexBoxWidth;
      if (current !== null) {
        ctx.beginPath();
        ctx.moveTo(x, y + boxHeight / 2);
        ctx.lineTo(x + gap, y + boxHeight / 2);
        ctx.stroke();
      }
      x += gap;
 
      while (current !== null) {
        ctx.fillStyle = isHighlighted ? this.highlightColor : 'lightgray';
 
        ctx.fillRect(x, y, boxWidth, boxHeight);
        ctx.strokeRect(x, y, boxWidth, boxHeight);
 
        ctx.fillStyle = 'black';
        ctx.fillText(current.key, x + 5, y + 20);
 
        if (current.next !== null) {
          ctx.beginPath();
          ctx.moveTo(x + boxWidth, y + boxHeight / 2);
          ctx.lineTo(x + boxWidth + gap, y + boxHeight / 2);
          ctx.stroke();
        }
        x += boxWidth + gap;
        current = current.next;
      }
    }
  }
  private triggerAnimation(index: number, color: string): void {
    this.animation$.next({ index, color });
  }
  private clearHighlight(): void {
    this.highlightIndex = -1;
    this.draw();
  }
  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
  private updateTableState(): void {
    this.tableState = this.hashService.getTableAsArray();
  }
}