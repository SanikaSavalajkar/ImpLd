import java.util.Scanner;

public class PrimeNum {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter num: ");
        int num = sc.nextInt();
        int m = num/2;
        if(num<=1){
            System.out.println("Not Prime");
        }
        for(int i=2; i<=m; i++){
            if(num%i==0){
                System.out.println("Not Prime");
            } else {
                System.out.println("Prime Num");
            }
        }
    }
}
