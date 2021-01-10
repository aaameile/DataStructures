package search;

import java.util.ArrayList;

/*
二分查找
要求数组必须是有序的
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = new int[]{0,1,1,1,2,3,4};
        ArrayList<Integer> i = binarySearch(arr, 0, arr.length-1, 0);
        if (i.equals(new ArrayList<Integer>())){
            System.out.println(" 没找到 ");
        }else {
            System.out.println(" 找到了" + i);
        }


    }
    public static ArrayList<Integer> binarySearch(int[] arr , int left ,int right ,int val){
        System.out.println(100/2);
        //退出递归 没找到就退出
        if (left > right){
            return new ArrayList<>();
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];


        //从左边找
        if (val < midVal) {
            return binarySearch(arr, left, mid - 1, val);
        }
        //从右边找
        if (val > midVal){
            return binarySearch(arr,mid + 1,right,val);
        }

        //return midVal;  只找一个

        //如果有相同的 要找的值    找 多个
        //从找到的元素左边扫描看看还有没有相同的了
        ArrayList<Integer> arrayList = new ArrayList<>();
        //从左边
        int temp = mid -1;
        while (temp >= 0 && arr[temp] == val) {
            arrayList.add(temp--);

        }

        // 从右边
        temp = mid + 1;
        while (temp <= arr.length-1  && arr[temp] == val) {
            arrayList.add(temp++);
        }

        arrayList.add(mid);
        return arrayList;
    }
}
