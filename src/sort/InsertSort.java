package sort;

import java.util.Arrays;

/*
插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
        int [] arr = new int[]{6,4,2,3,7,0};
        InsertSort.Sort(arr);


    }
    public static  void Sort(int[] a){
        for (int i = 1; i < a.length; i++) {
            for(int j = i; j > 0; j--){
                if (a[j] < a[j - 1]){
                    int temp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = temp;
                }

            }
            System.out.println(" 第 " + i + "次排序的结果" + Arrays.toString(a));
        }
    }
}
