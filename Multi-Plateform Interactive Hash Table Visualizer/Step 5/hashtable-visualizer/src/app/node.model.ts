export class Node {
  key: string;
  next: Node | null = null;

  constructor(key: string) {
    this.key = key;
  }
}