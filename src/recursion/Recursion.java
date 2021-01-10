package recursion;
/*
每一次递归调用都会在栈内开辟新的空间重新执行方法
不能太深
 */
public class Recursion {
    public static void main(String[] args) {
        //test(3);
        int i = Factorial(3);
        System.out.println(i);


    }

    //递归
    public static int test(int n){
        if (n > 2){
           return test(n - 1);
        }else {
            System.out.println("n = " + n);
        }
        return 0;
    }

    //阶乘
    public static int Factorial(int n){
        if (n == 1){
            return 1;
        }
            return Factorial(n -1) * n;

    }


}
