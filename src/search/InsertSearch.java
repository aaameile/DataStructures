package search;

/*
插值查找
公式 midIndex = left + (right - left) * (val - arr[left]) / (arr[right] - arr[left])

数据量大 关键字分布比较均匀 采用插值查找
关键字分布不均的情况下该方法不一定有二分好用
 */
public class InsertSearch {
    public static void main(String[] args) {
        int[] arr =new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i;
        }
//        System.out.println(Arrays.toString(arr));
        int i = insertSearch(arr, 0, arr.length - 1, 1);
        System.out.println(i);
    }
    public static int insertSearch(int[] arr,int left,int right, int val){
        System.out.println("查询次数");
//        int mid = (left + right) / 2; 二分查找
        //插入查找
        int mid = left + (right - left) * (val - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (left > right || val < arr[0] || val > arr[arr.length-1]){
            return -1;
        }

        if (val > midVal){
            return insertSearch(arr, mid + 1,right,val);
        }
        if (val < midVal){
            return insertSearch(arr,left,mid - 1,val);
        }
        return mid;



    }
}
