#include <stdio.h>
#include <stdlib.h>
#define MEMORY_SIZE 1000
int memory[MEMORY_SIZE] = {0};
typedef struct Block{
    int start;
    int size;
    int free;
    struct Block *next;
} Block;
Block *head = NULL;
void initializeMemory(){
    head = (Block*)malloc(sizeof(Block));
    (*head).start = 0;
    (*head).size = MEMORY_SIZE;
    (*head).free = 1;
    (*head).next = NULL;
}
int allocateNormally(int size){
    Block *current = head;
    while(current != NULL){
        if((*current).free && (*current).size >= size){
            int start = (*current).start;
            for(int i = start; i < start + size; i++){
                memory[i] = 1;
            }
            if((*current).size > size){
                Block *newBlock = (Block*)malloc(sizeof(Block));
                (*newBlock).start = (*current).start + size;
                (*newBlock).size = (*current).size - size;
                (*newBlock).free = 1;
                (*newBlock).next = (*current).next;
                (*current).next = newBlock;
            }
            (*current).size = size;
            (*current).free = 0;
            return start;
        }
        current = (*current).next;
    }
    return -1;
}
int detectFragmentation(int requestedSize){
    int totalFree = 0;
    int largestFreeBlock = 0;
    Block *current = head;
    while(current != NULL){
        if((*current).free){
            totalFree += (*current).size;
            if((*current).size > largestFreeBlock){
                largestFreeBlock = (*current).size;
            }
        }
        current = (*current).next;
    }
    return (totalFree >= requestedSize && largestFreeBlock < requestedSize);
}
void defragment(){
    Block *current = head;
    Block *newHead = NULL;
    Block *last = NULL;
    int nextStart = 0;
    while(current != NULL){
        Block *next = (*current).next;
        if(!(*current).free){
            (*current).start = nextStart;
            nextStart += (*current).size;
            (*current).next = NULL;
            if(newHead == NULL){
                newHead = current;
                last = current;
            }
            else{
                (*last).next = current;
                last = current;
            }
        }
        else{
            free(current);
        }
        current = next;
    }
    if(nextStart < MEMORY_SIZE){
        Block *freeBlock = (Block*)malloc(sizeof(Block));
        (*freeBlock).start = nextStart;
        (*freeBlock).size = MEMORY_SIZE - nextStart;
        (*freeBlock).free = 1;
        (*freeBlock).next = NULL;
        if(newHead == NULL){
            newHead = freeBlock;
        }
        else{
            (*last).next = freeBlock;
        }
    }
    head = newHead;
    for(int i = 0; i < MEMORY_SIZE; i++){
        memory[i] = 0;
    }
    current = head;
    while(current != NULL){
        if(!(*current).free){
            for(int i = (*current).start;i < (*current).start + (*current).size;i++){
                memory[i] = 1;
            }
        }
        current = (*current).next;
    }
}
int myMALLOC(int size){
    int p = allocateNormally(size);
    if(p != -1){
        return p;
    }
    if(detectFragmentation(size)){
        printf("\nFragmentation detectee.\n");
        printf("Defragmentation en cours...\n");
        defragment();
        p = allocateNormally(size);
        if(p != -1){
            printf("Allocation reussie apres defragmentation.\n");
        }
        return p;
    }
    return -1;
}
void myFREE(int start){
    Block *current = head;
    while(current != NULL){
        if((*current).start == start){
            (*current).free = 1;
            for(int i = start;i < start + (*current).size;i++){
                memory[i] = 0;
            }
            break;
        }
        current = (*current).next;
    }
    current = head;
    while(current != NULL &&
          (*current).next != NULL){
        if((*current).free && (*((*current).next)).free){
            Block *temp = (*current).next;
            (*current).size += (*temp).size;
            (*current).next = (*temp).next;
            free(temp);
        }
        else{
            current = (*current).next;
        }
    }
}
void displayBlocks(){
    Block *current = head;
    printf("\nEtat de la memoire :\n");
    while(current != NULL){
        printf("Start=%d  Size=%d  Free=%d\n",
               (*current).start,
               (*current).size,
               (*current).free);
        current = (*current).next;
    }
}
int main(){
    initializeMemory();
    displayBlocks();
    int p1 = myMALLOC(300);
    int p2 = myMALLOC(200);
    printf("\nApres allocations :\n");
    displayBlocks();
    myFREE(p1);
    printf("\nApres liberation du bloc de 300 :\n");
    displayBlocks();
    printf("\nDemande d'un bloc de 800...\n");
    int p3 = myMALLOC(800);
    if(p3 != -1){
        printf("Bloc de 800 alloue a l'indice %d\n", p3);
    }
    else{
        printf("Allocation impossible.\n");
    }
    displayBlocks();
    return 0;
}