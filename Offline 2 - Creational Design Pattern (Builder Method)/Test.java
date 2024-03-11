public class Test {
    public static void main(String[] args) {
         int n=50000;
         int x = n;
        int k=40;
        int count =0 ;
        while(n>=k){
            n-=k;
            n++;
            count++;
        }
        System.out.println(count+x);

    }
}
