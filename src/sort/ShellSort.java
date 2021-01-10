package sort;

/*
希尔排序
 */
public class ShellSort {
    public static void main(String[] args) {
        int sum = 0;
        int n = 100;
        sum = (n + 1) * n / 2;
        System.out.println(sum);
        System.out.println(jieChen(3));
    }
    public static long jieChen(long n){
        if (n == 1){
            return n;
        }
        return n * jieChen(n - 1);
    }


}
