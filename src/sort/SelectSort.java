package sort;

import java.util.Arrays;

/*
选择排序
    从数据中按指定的规则选出某一个元素，再按规定交换位置达到排序目的
    找到数组中最小的数和第一个数交换
    再从其余的数组中找最小的数和第二个数交换
    以此类推
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = new int[]{3,1,5,4,6,2};

        //假设数组中第一个值是最小的 然后和第二个数开始比较
        //当假设的最小值 比 后者 大
        //就把假设的最小值 重置为后者
        //此时最小值就是后者
        //然后再接着比较直到找出真正最小值

        for (int i = 0; i < arr.length - 1; i++) {
            int min = arr[i];
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {

                if (min > arr[j]){
                    min = arr[j];
                    minIndex = j;
                }

            }
            //将数组的第一个元素和最小值元素的位置交换
            //如果在剩下的几个数中找最小值
            //发现目前假设的值就是这几个数中的最小值
            //就不交换位置了
            //判断条件是
            //没发生重置就说明这个数就是这几个数中最小的
            //minIndex 和 i是相等的  就不进行交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
            System.out.println("第" + (i + 1 ) + "轮交换");
            System.out.println(Arrays.toString(arr));
        }

    }
}
