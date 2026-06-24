#include <stdio.h>
#include <stdlib.h>
void stableEVENoddSORT(int arr[],int n){
    for (int i = 1; i < n; i++) {
        int key = arr[i];
        int j = i - 1;

        while (j >= 0 && ((arr[j] % 2 != 0 && key % 2 == 0) || (arr[j] % 2 == key % 2 && arr[j] > key))) {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = key;
    }
}
int main(){
    int tab[]={10,2,5,8,9,16,18,9,11};
    int n = sizeof(tab) / sizeof(tab[0]); 
    stableEVENoddSORT(tab,n);
    printf("Sorted:   \n");
    for (int i=0;i<n;i++){
        printf("%d\n",tab[i]);
    }
    return 0;
}