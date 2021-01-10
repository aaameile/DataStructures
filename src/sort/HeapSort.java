package sort;

import java.util.Arrays;

/*
堆排序
大顶堆
根节点大于左右叶子节点
小顶堆
根节点小于左右叶子节点
拿根节点的左右叶子节点进行比较 然后和根交换形成大顶堆或者小顶堆
从上往下 从左到右
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] arr =new int[]{1,2,3,4,5};
        heapSort(arr);
        int a = 0;
        a= 5/2;
        System.out.println(a);


    }
    //堆排序
    public static void heapSort(int[] arr){

//        adjustHeap(arr,0,arr.length);
//        adjustHeap(arr,1,arr.length);

        for (int i = arr.length / 2 - 1; i >=0;i --){
            adjustHeap(arr,i,arr.length);
        }
//
        for (int j = arr.length - 1;j > 0;j --){
            int temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,j);//从0开始因为已经是大顶堆了 下面都是越来越小的就不需要从下往上把大的找上来
        }

        System.out.println("arr = " + Arrays.toString(arr));
    }
    //调整成为大顶堆  从下往上 从左到右
    public static void adjustHeap(int[] arr,int i,int length){
        int temp = arr[i];
        //k = i * 2 + 1; 代表以i为根的左叶子
        //k * 2 + 1 代表 当前节点的左子节点
        //
        for (int k = i * 2 + 1;k < length; k = k * 2 + 1){
            if ( k + 1 < length && arr[k] < arr[k + 1]  ){//找到大的叶子节点
                k++;
            }
            if (arr[k] > temp){//将大的叶子节点和当前根节点进行交换
                arr[i] = arr[k];
                arr[k] = temp;
                i = k;//i 指向 k 继续循环比较
            }else {
                break;
            }

        }
//        arr[i] = temp;
    }
}
