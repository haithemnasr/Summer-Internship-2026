#include <stdio.h>

int coinChange(int coins[], int n, int amount){
    int test[n];
    int table[amount + 1];
    table[0] = 0;
    for (int i = 1; i <= amount; i++){
        table[i] = amount + 1;
    }
    for (int i = 1; i <= amount; i++){
        for (int j = 0; j < n; j++){
            if (coins[j] <= i){
                test[j] = 1 + table[i - coins[j]];
            }
            else{
                test[j] = amount + 1;
            }
        }
        int min = test[0];
        for (int j = 1; j < n; j++){
            if (test[j] < min){
                min = test[j];
            }
        }
        table[i] = min;
    }
    if (table[amount] == amount + 1){
        return -1;
    }
    return table[amount];
}

int main() {
    int coins[] = {50, 100, 200, 500, 1000, 2000, 5000};
    int n = sizeof(coins) / sizeof(coins[0]);
    int p = coinChange(coins, n, 7250);
    printf("%d", p);
    return 0;
}