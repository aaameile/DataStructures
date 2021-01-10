package sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {1,-2,-3,4,2,0,8,-9};
        quickSort(arr,0,arr.length - 1);
        System.out.println(Arrays.toString(arr));

    }
    public static void quickSort(int[] arr,int left,int right){
        int l = left; //左下标
        int r = right; //右下标
        int pivot = arr[ (left + right) / 2]; //中轴数

        //比中轴数小的放左边  比中轴数大的放右边
        while (l < r){

            //在左边找
            while (arr[l] < pivot){
                l += 1;
            }

            //在右边找
            while (arr[r] > pivot){
                r -= 1;
            }

            //说明数组已经是左边是比中轴小的 右边是比中轴大的了
            if (l >= r){
                break;
            }

            //交换
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            // 现在左边都是比中轴数小的 右边都是比中轴数大的
            // 如果交换后的数刚好和中轴数相等
            // 在接下来的比较中就不需要多比一次了
            if (arr[r] == pivot){
                l += 1;
            }

            if (arr[l] == pivot){
                r -= 1;
            }

        }

        //此时让l继续增加作为左边的r   r继续减作为右边的left
        if (l == r){
            l += 1;
            r -= 1;
        }

        //向左递归
        if(left < r ){
            quickSort(arr,left,r);

        }

        //向右递归
        if (right > l){
            quickSort(arr,l,right);
        }






    }
}
