package sort;

import java.util.Arrays;

/*
冒泡排序
    i = 0
    比较数组arr[i]和arr[i + 1]大小
    大于就交换 小于就不变
    每交换一次
    定义的辅助变量就需要后移一位
 */
public class BubbleSort {
    public static void main(String[] args) {
       int[] arr = new int[]{1,2,3,4,1};
       boolean flag = false;
        for (int i = 0; i < arr.length - 1 ; i++) {

            for (int j = 0; j < arr.length - 1 -i; j++) {
                if (arr[j] > arr[j + 1]){
                    flag = true;
                    int temp = arr[j];
                    arr[j] = arr [j + 1];
                    arr[j + 1] = temp;
                }

            }
            System.out.println("第" + (i + 1) + "次排序后的数组" );
            System.out.println(Arrays.toString(arr));
            if (!flag){
                break;//一次没交换过就退出
            }else {
                flag = false;//重置flag进行下次判断
            }
        }
    }
}
