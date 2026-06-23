#include <stdio.h>
#include <stdlib.h>
int stableEVENoddSORT(int arr[],int n){
    int* temp=malloc(n * sizeof(int));
    if (temp==NULL){
        printf("temp creation denied");
        return -1;
    }
    int j=0;
    for (int i=0; i<n; i++){
        if (arr[i]%2==0){
            temp[j]=arr[i];
            j++;
        }
    }
    int c=j;
    for (int i=0; i<n; i++){
        if (arr[i]%2!= 0){
            temp[j]=arr[i];
            j++;
        }
    }
    for (int i=0;i<n;i++){
        arr[i]=temp[i];
    }
    free(temp);
    return c;
}
void merge (int array[], int left, int mid, int right){ 
    int n1 = mid - left + 1;
    int n2 = right - mid;
    int* L= malloc(n1*sizeof(int));
    int* R= malloc(n2*sizeof(int));
    
    if (L == NULL || R == NULL){
        free(L);
        free(R);
    return;
}
    for (int i=0; i<n1; i++){
        L[i]=array[left+i];
    }
    for (int j=0; j<n2; j++){
        R[j]=array[mid+1+j];
    }
    int i=0,j=0, k=left;
    while (i<n1 && j<n2){
        if (L[i] <= R[j]) {
            array[k++] = L[i++];
        } else {
            array[k++] = R[j++];
        }
    }
    while (i < n1) {
        array[k++] = L[i++];
    }
    while (j < n2) {
        array[k++] = R[j++];
    }
    free(L);
    free(R);
}
void mergeSort(int array[], int left, int right){
    if (left < right){
        int mid = left + (right - left)/2;

        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);

        merge(array, left, mid, right);
    }
}
int main(){
    int tab[]={10,2,5,8,9,16,18,9,11};
    int n = sizeof(tab) / sizeof(tab[0]); 
    int c= stableEVENoddSORT(tab,n);
    mergeSort(tab, 0, c - 1);
    mergeSort(tab, c, n - 1);
    printf("Sorted:   \n");
    for (int i=0;i<n;i++){
        printf("%d\n",tab[i]);
    }
    return 0;
}