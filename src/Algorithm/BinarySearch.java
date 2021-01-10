package Algorithm;
/*
二分查找非递归
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        System.out.println("下标为" + binarySearch(arr, 7));
    }

    public static int binarySearch(int [] arr , int target){
        int left = 0;
        int right = arr.length-1;
        while (left <= right){
            int mid = (left + right) / 2;
            if (arr[mid] == target){
                return mid;
            }else if (arr[mid] > target){
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return -1;

    }
}
