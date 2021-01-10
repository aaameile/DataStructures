package tree;

/*
二叉排序树
任何一个左叶子节点的值都比右叶子节点的值小
如果有相同的值可以将该节点放在当前节点的左子节点 或右子节点
 */
public class BinarySortTree {
    public static void main(String[] args) {
        int[] arr = {0, 3, 10, 12, 5, 1, 9,0,4,2,90};
        BSTree tree = new BSTree();
        for (int value : arr) {
            tree.add(new Bst(value));
        }

        System.out.println(tree.getRoot());//根节点被删除了
        tree.inFixOrder();

        tree.Del(1);
        tree.Del(5);
        tree.Del(9);
        tree.Del(12);
        System.out.println(tree.getRoot());//根节点被删除了

//        tree.Del(3);
//        tree.Del(7);
//        tree.Del(10);
//        tree.Del(0);
        tree.inFixOrder();



    }
}

//创建树
class BSTree{
    public Bst root;
    public Bst getRoot(){
        return root;
    }
    //添加
    public void add(Bst bst){
        if (root == null){
            root = bst;
        }else {
            root.add(bst);
        }
    }

    //中序遍历
    public void inFixOrder(){
        if (root != null){
            root.inFixOrder();
        }else {
            System.out.println(" 空树 ");
        }
    }

    //查找要删除节点
    public Bst Search(int val){
        if (root == null){
            return null;
        } else {
            return root.Search(val);
        }
    }

    //查找要删除节点的父节点
    public Bst searchParent(int val){
        if (root == null){
            return null;
        }else {
            return root.searchParent(val);
        }
    }

    //查找传入节点的最小值并删除这个节点
    public int delTreeMin(Bst bst){
        Bst target = bst;//临时变量
        while (target.left != null){
            target = target.left;
        }
        Del(target.val);
        return target.val;
    }

    //删除节点
    public void Del(int val){
        if (root == null){
            return;
        }else {
            Bst target = Search(val);

            if (target == null){
                System.out.println(" 没找到 ");
                return;
            }

            if (root.left == null && root.right == null){
                root = null;
                return;
            }

            Bst parent = searchParent(val);

            if (target.left == null && target.right == null){//  叶子节点 没有后续节点了可以直接删除

                //看看是父节点的左节点还是又节点利用父节点直接置空
                if (parent.left != null && parent.left.val == val){
                    parent.left = null;

                }else if (parent.right != null && parent.right.val == val){
                    parent.right = null;
                }

            }else if (target.left != null && target.right != null){// 有2个子节点的节点

                //为了保证二叉排序树的特性 从要删除节点的右边节点往下找 找打后替换为当前要删除的节点的值
                target.val = delTreeMin(target.right);

            }else {//只有一颗子树的节点
                if (parent != null){
                    //先判断这个节点是父节点的左还是右
                    //然后把这个节点的下一个节点直接赋值给 前面判断出来的结果
                    if (target.left != null){//目标节点的左边有一个节点
                        if (parent.left.val == val){//目标节点是父节点的左节点
                            parent.left = target.left;//将目标节点的左节点 值赋给他的父节点的左节点 相当于链表删除节点
                        }else {//目标节点是父节点的右节点
                            parent.right = target.left;
                        }
                }else {
                        root = target.left;
                    }

                }else {//目标节点的右边有一个节点
                    if (parent != null){
                        if (parent.left.val == val){
                            parent.left = target.right;
                        }else {
                            parent.right = target.right;
                        }
                    }else{
                        root = target.right;
                    }

                }
            }
        }

    }


}


//节点
class Bst{
   public int val;
   public Bst left;
   public Bst right;

    //添加方法
    public void add(Bst bst){
        if (bst == null){
            System.out.println(" 节点为空无法添加 ");
            return;
        }
        //如果添加进来的节点值小于当前根节点就添加到左叶子节点上
        if (this.val > bst.val){
            if (this.left == null) {
                this.left = bst;
            }else {
                this.left.add(bst);
            }
        }else {
            if (this.right == null){
                this.right = bst;
            }else{
                this.right.add(bst);
            }
        }
    }

    //查找要删除的节点
    public Bst Search(int val){
        if (this.val == val){
            return this;
        //要找的节点小于当前节点就从左边递归继续找
        } else if (val < this.val){
            if (this.left == null){
                return null;
            }
           return this.left.Search(val);
        //要找的节点小于当前节点就从右边递归继续找
        } else {
            if (this.right == null){
                return null;
            }
         return this.right.Search(val);
        }
//        if (this.val == val){
//            return this;
//        }
//        if (val < this.val && this.left != null){
//            return this.left.Search(val);
//        }
//        if (this.right != null){
//            return this.right.Search(val);
//        }
//        return null;
    }

    //查找要删除节点的父节点
    public Bst searchParent(int val){
        //通过他的父个节点去找要删除的节点
        if ( (this.left != null && this.left.val == val) || (this.right != null && this.right.val == val) ){
            return this;

        }else {//要找的节点小于当前节点就从左边递归继续找

            if (this.left != null && val < this.val){

                return this.left.searchParent(val);
                //要找的节点小于当前节点就从右边递归继续找
            }else if (this.right != null && val >= this.val){

                return this.right.searchParent(val);
            }else {
                //找不到返回null
                return null;
            }
        }

        //
    }

    //中序遍历
    public void inFixOrder(){

        if (this.left != null){
            this.left.inFixOrder();
        }

        System.out.println(this);


        if (this.right != null){
            this.right.inFixOrder();
        }

    }
    public Bst (int val ){
        this.val = val;
    }

    @Override
    public String toString() {
        return "bst{" +
                "val=" + val +
                '}';
    }
}
