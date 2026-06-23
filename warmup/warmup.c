#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

char* reverse(const char* str, char reversed){
    int len = strlen(str);
    char* y = malloc(len + 1);
    if (y == NULL) {
        return NULL;
    }
    for (int i=0; i<len;i++){
        y[i]=str[len-1-i];
    }
    y[len]='\0';
    reversed=y;
    free(y);
    return reversed;
}
int isPalindrome(const char* str){
    int len= strlen(str);
    int i=0;
    while (str[i]==str[len-i-1]){
        i++;
    }
    return (int)(i==len);
}
int main() {
    char str_reversed=reverse("haithem","");
    if (str_reversed != NULL) {
        printf("%s\n",str_reversed);
        free(str_reversed);
    }
    char* str= "aziza";
    int res=isPalindrome(str);
    if (res ==1){
        printf("%s est un palindrome",str);
    }else{
        printf("%s n'est pas un palindrome",str);
    }
    return 0;
}