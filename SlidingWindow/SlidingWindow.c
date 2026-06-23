#include <stdio.h>
int maxSubarraySum(int* arr, int n, int k){
    int tabsum[n-k+1];
    int sum=0;
    for (int i=0; i<k;i++){
        sum+=arr[i];
    }
    tabsum[0]=sum;
    for (int i=0; i<n-k; i++){
        sum=sum-arr[i]+arr[i+k];
        tabsum[i+1]=sum;
    }
    int maxsum= tabsum[0];
    for (int i=1; i<n-k+1;i++){
        if (tabsum[i]>maxsum){
            maxsum=tabsum[i];
        }
    }
    return maxsum; 
}
int main(){
    int tab[]={10,2,5,8,9,16,18,9,11};
    printf("%d",maxSubarraySum(tab,9,4));
    return 0;
}