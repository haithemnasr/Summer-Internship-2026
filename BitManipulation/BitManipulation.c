#include <stdio.h>
int countbit(int n){
    int count=0;
    while (n >0) {
        n=n&(n-1);
        count++;
    }
    return count;
}
void swapXor(int *x, int *y){
    if (x!=y){
        *x=*x^*y;
        *y=*y^*x;
        *x=*x^*y;
    }

}
int main(){
    int number=12;
    printf("%d\n", countbit(number));

    int x=10;
    int y=15;
    swapXor(&x,&y);
    printf("x=%d et y=%d",x,y);
    return 0;
}