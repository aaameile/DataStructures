package search;

import java.util.Arrays;

/*
斐波那契额数列
f[k] = f[k-1] + f[k-2]
f[k] - 1 = (f[k-1]-1) +  (f[k-2]-1) + 1
因为斐波那契数列的第一个数是1
所以 f[k] = left + f(k - 1) - 1
mid = left + f(k - 1) - 1
需要用一个斐波那契数列
 */
public class FibonacciSearch {
    public static void main(String[] args) {
        int [] arr = new int[]{1,8,10,89,1000,1234};
        System.out.println(fibonacciSearch(arr, 1));

    }
    //斐波那契数列
    public static int[] Fibonacci(){
        int[] fbi = new int[20];
        fbi[0] = 1;
        fbi[1] = 1;
        for (int i = 2; i < 20; i++) {
            fbi[i] = fbi[i - 1] + fbi[i -2];
        }
        System.out.println(Arrays.toString(fbi));
        return fbi;
    }

    public static int fibonacciSearch(int[] arr, int val){
        int left = 0;
        int right = arr.length - 1;
        int k = 0; //斐波那契的下标
        int mid = 0;//mid值
        int fbi[] = Fibonacci();

        //k的计算方法
        while (right > fbi[k] - 1){
            k++;
        }

        //
        int[] temp = Arrays.copyOf(arr,fbi[k]);

        for (int i = right + 1; i < temp.length; i++) {
            temp[i] = arr[right];
        }

        while (left <= right){
            mid = left + fbi[k - 1] - 1;
            if (val < temp[mid]){
                right = mid - 1;
                k --;
            }
            if (val > temp[mid]){
                left = mid + 1;
                k -= 2;
            }
            if (mid <= right){
                return  mid;
            }else {
                return right;
            }
        }

        return -1;
    }

}

