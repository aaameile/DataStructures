package tree;

import java.util.ArrayList;
import java.util.Collections;

/*
哈弗曼树
前缀编码 字符的编码都不能是其他的字符编码的前缀
将一个数组从小到大排序 取最小权值的2个数组成新的二叉树 根节点的权值为2数之和
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = new int[]{13,7,8,3,29,6,1};
        Huffman root = createHuffmanTree(arr);
        Order(root);

    }
    //先序遍历
    public static void Order(Huffman huffman){
        if (huffman !=null){
            huffman.preOrder();
        }else{
            System.out.println(" 树为空 ");
        }
    }

    //创建哈夫曼树
    public static Huffman createHuffmanTree(int[] arr){
        //使用集合好操作 将数组中的每一个元素作为节点的权值添加到集合里 然后排好序
        ArrayList<Huffman> list = new ArrayList<>();
        for (int val : arr){
            list.add(new Huffman(val));
        }

//        System.out.println("list = " + list);

        //循环构建新的二叉树最后就会形成哈弗曼树 最后会添加一个节点进去所以只需要判断大小是否为1就知道完没完
        while (list.size() > 1) {
            Collections.sort(list);
            //取出数组中最小的2个节点 构建新的二叉树
            Huffman left = list.get(0);
            Huffman right = list.get(1);

            //新的树的根节点是叶子节点权值之和 构建新的二叉树
            Huffman root = new Huffman (left.val + right.val);
            root.left = left;
            root.right = right;

            //把处理过的节点从数组中删除 将新构建的节点加进去
            list.remove(left);
            list.remove(right);
            list.add(root);
        }
//        System.out.println("left = " + list);
        //最后返回这个节点就是哈弗曼树的根节点
        return list.get(0);
    }



}



//节点
class Huffman implements Comparable<Huffman>{
    int val;//权值
    Huffman left;
    Huffman right;
    public Huffman(int val){
        this.val = val;
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);

        if (this.left != null){
             this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Huffman{" +
                "val=" + val +
                '}';
    }


    @Override
    public int compareTo(Huffman o) {
        return this.val - o.val;
    }
}
