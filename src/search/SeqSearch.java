package search;
/*
线性查找
 */
public class SeqSearch {
    public static void main(String[] args) {
        int [] arr = new int[]{1,2,4,5,6,6,};
        int index = searchOne(2,arr);
        if ( index == -1)
        System.out.println(" 没找到 ");
        System.out.println(" 找到了下标为 " + index);

    }

    //找到一个满足的就返回 如果后面还有相同的val
    public static int searchOne(int val,int [] arr){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == val){
                return i;
            }
        }
        return -1;
    }
}
