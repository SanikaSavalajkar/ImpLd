public class Palindrome {
    public static void main(String[] args) {
        int n = 121;
        int rev;
        int sum=0;
        int temp;
        temp=n;
        while(n>0){
            rev = n%10; // 1 // 2
            sum = (sum*10)+rev; // 1 // 12
            n=n/10; // 12 // 1
        }
        if(temp==sum){
            System.out.println("Palindrome");
        } else {
            System.out.println("Not Palindrome");
        }
    }
}
