package sort;

import java.util.Arrays;

/*
归并排序

 */
public class MergetSort {
    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 4, 7, 5, 9};
        int[] temp = new int[arr.length];
        mergeSort(arr,0,arr.length -1,temp);
        System.out.println(Arrays.toString(arr));

    }

    // arr 要排序的数组
    // left 数组最左边
    // mid  数组中间
    // right 数组最右边

    //分合的方法
    public static void mergeSort(int[] arr , int left,int right ,int[] temp){
        if (left < right){
            int mid = (left + right) / 2;
            mergeSort(arr ,left ,mid ,temp);//左边
            mergeSort(arr,mid + 1,right,temp);
            merge(arr ,left ,mid ,right ,temp);
        }
    }

    //合并的方法
    public static void merge(int[] arr , int left, int mid ,int right ,int[] temp){
        int i = left;
        int j = mid + 1;
        int t = 0;

        //将比mid小的数依次填充到临时数组里
        //
        while (i <= mid && j <= right){
            if (arr[i] <= arr[j]){
                temp[t] = arr[i];
                t += 1;
                i += 1;
            }else {
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }

        //把剩余的数填充到临时数组
        while (i <= mid){
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }

        while (j <= right){
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        //合并
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right){
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
//        for( i = left; i <= right; i++){
//            arr[i] = temp[i];
//        }

    }
}
