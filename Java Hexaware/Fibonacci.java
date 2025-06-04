// 0 1 1 2 3 5 8 13

public class Fibonacci {
    public static void main(String[] args) {
        int a = 0;
        int b = 1;
        int n = 5;
        System.out.print(a);
        System.out.print(" "+b);
        for(int i=0; i<=n; i++){
            int c = a+b;
            System.out.print(" "+c);
            a=b;
            b=c;
        }
    }
}
