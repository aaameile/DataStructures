package tree;
/*
数组二叉树  顺序存储二叉树  堆排序中用的到

通常考虑完全二叉树
n表示二叉树的第几个元素（从0 开始）
第n个元素的左子节点的下标为 2*n+1
第n个元素的右子节点的下标为 2*n+2
第n个元素的父节点的下标为 （n-1）/2
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7};
        ArrBinaryTree tree = new ArrBinaryTree(arr);
        tree.preOrder();

        tree.postOrder(0);
    }
}

//创建一个顺序存储二叉树
class ArrBinaryTree{
    private int[] arr;
    public  ArrBinaryTree(int[] arr){
        this.arr = arr;
    }

    public void preOrder(){
        this.preOrder(0);
    }

    //中序遍历
    public void infixOrder(int index){
        if (arr == null || arr.length == 0){
            System.out.println(" 数组为空无法遍历 ");
        }



        //左递归  index * 2 + 1 代表左子节点 下标
        if ((index * 2 + 1) < arr.length){
            infixOrder(index * 2 + 1);
        }
        System.out.println(arr[index]);

        //右递归
        if (index * 2 + 2 < arr.length){
            infixOrder(index * 2 + 2);
        }
    }
    //后序遍历
    public void postOrder(int index){
        if (arr == null || arr.length == 0){
            System.out.println(" 数组为空无法遍历 ");
        }



        //左递归  index * 2 + 1 代表左子节点 下标
        if ((index * 2 + 1) < arr.length){
            postOrder(index * 2 + 1);
        }


        //右递归
        if (index * 2 + 2 < arr.length){
            postOrder(index * 2 + 2);
        }
        System.out.println(arr[index]);

    }
    //先序遍历
    public void preOrder(int index){
        if (arr == null || arr.length == 0){
            System.out.println(" 数组为空无法遍历 ");
        }

        System.out.println(arr[index]);

        //左递归  index * 2 + 1 代表左子节点 下标
        if ((index * 2 + 1) < arr.length){
            preOrder(index * 2 + 1);
        }

        //右递归
        if (index * 2 + 2 < arr.length){
            preOrder(index * 2 + 2);
        }

    }
}
