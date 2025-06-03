public class Fibonacci {
    public static int fib(int n){
        if (n<=1){
            return n;
        } else {
            return fib(n-1) + fib(n-2);
        }
    }

    public static void main(String[] args) {
        int n = 9;
        if(n<0){
            System.out.println("Fibonacci num not defined");
        } else {
            System.out.print(fib(n)+" ");
        }
    }
}
