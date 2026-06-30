import { Injectable } from '@angular/core';
import { Node } from './node.model';
@Injectable({
  providedIn: 'root',
})

export class HashtableService {
    private size = 10;
    private table: (Node | null)[] = new Array(this.size).fill(null);

    hash(key: string): number {
        let hashValue = 0;
            for (let i = 0; i< key.length; i++){
                hashValue = (hashValue * 31 + key.charCodeAt(i)) % this.size;
            }
            return hashValue;
    }
    add(key: string): void {
        const index = this.hash(key);
            let current = this.table[index];
            while (current !== null) {
                if (current.key === key){
                    return;
                }
                current = current.next;
            }
            const newNode = new Node(key);
            newNode.next = this.table[index];
            this.table[index] = newNode;
    }
    delete(key: string): boolean {
        const index = this.hash(key);
            let current = this.table[index];
            let prev = null;
            while (current !== null){
                if (current.key === key){
                    if (prev === null){
                        this.table[index] = current.next;
                    }else{
                        prev.next = current.next;
                    }
                    return true;
                }
                prev = current;
                current = current.next;
            }
            return false;
    }
    find(key: string): boolean {
        const index = this.hash(key);
            let current = this.table[index];
            while (current !== null){
                if (current.key === key){
                    return true;
                }
                current = current.next;
            }
            return false;
    }
    getTable(): (Node | null)[] { 
        return this.table; 
    }
    getTableAsArray(): (string[] | null)[] {
        return this.table.map(bucket => {
        if (!bucket) return null;

        const result: string[] = [];
        let current: Node | null = bucket;

        while (current !== null) {
        result.push(current.key);
        current = current.next;
        }

        return result;
    });
    }
}
