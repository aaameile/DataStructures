package leetcode;
/*
查找数组中的相同元素
 */
import java.util.HashSet;
import java.util.Set;

public class reArray {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,4,1,2};

    }

    public boolean duplicate(int[] arr,int length,int [] duplication) {
        if (length == 0|| arr == null){
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int arr1 : arr){
            if (!set.add(arr1)){
                duplication[0] = arr1;
                return true;
            }
        }

        return false;


    }
}
