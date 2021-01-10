package tree;
/*
AVL
平衡二叉树

 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        int[] arr = {4,3,6,5,7,8};
        int[] arr2 = {10,12,8,9,7,6};
        int[] arr3 = {10,11,7,6,8,9};
        int[] arr4 = {0,2,1,6,5,7,3};
        AVLTree avlTree = new AVLTree();
        for (int value : arr){
            avlTree.add(new AVL(value));
        }
        System.out.println(avlTree.getRoot());
        avlTree.inFixOrder();

        System.out.println(" 左旋转前 ");
        System.out.println(" 树的高度 " + avlTree.getRoot().height());
        System.out.println(" 左树的高度 " + avlTree.getRoot().leftHeight());
        System.out.println(" 右树的高度 " + avlTree.getRoot().rightHeight());
//        System.out.println(avlTree.getRoot().left);
//        System.out.println(avlTree.getRoot());//根节点不见了
//        System.out.println(avlTree.getRoot().right);

    }
}

//创建树
class AVLTree {
    public AVL root;
    public AVL getRoot(){
        return root;
    }
    //添加
    public void add(AVL avl){
        if (root == null){
            root = avl;
        }else {
            root.add(avl);
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
    public AVL Search(int val){
        if (root == null){
            return null;
        } else {
            return root.Search(val);
        }
    }

    //查找要删除节点的父节点
    public AVL searchParent(int val){
        if (root == null){
            return null;
        }else {
            return root.searchParent(val);
        }
    }

    //查找传入节点的最小值并删除这个节点
    public int delTreeMin(AVL avl){
        AVL target = avl;//临时变量
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
            AVL target = Search(val);

            if (target == null){
                System.out.println(" 没找到 ");
                return;
            }

            if (root.left == null && root.right == null){
                root = null;
                return;
            }

            AVL parent = searchParent(val);

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
class AVL{
    int val;
    AVL left;
    AVL right;

    //左旋转  右边树高度 > 左边树高度 右边高就要平衡一下所以往左边旋转
    private void leftRotate(){
        //创建一个新的节点节点值为根节点的值
        AVL avl = new AVL(val);

        //新节点的左子节点设为当前树的左子节点
        avl.left = left;

        //新节点的右子节点为当前树的右子节点的的左子节点
        avl.right = right.left;

        //新节点的值为当前树的右子节点的值
        val = right.val;

        //将新节点设置为当前树的左子节点
        left = avl;

        //当前树的右子节点为  右子节点的右子节点
        right = right.right;

    }
    //右边旋转  左树高度 > 右树高度 左边高就需要往右边旋转
    private void rightRotate(){
        //创建一个新的节点
        AVL avl = new AVL(val);

        //新节点的左子节点为当前树的左子节点的右子节点
        avl.left = left.right;

        //新节点的右子节点为当前树的右子节点
        avl.right = right;

        //将新节点的值设为当前树的左子节点的值
        val = left.val;

        //将新节点设为当前树的右子节点
        right = avl;

        //当前树的左子节点为左子节点的左子节点
        left = left.left;

    }

    //返回左子树的高度
    public int leftHeight(){
        if (this.left == null){
            return 0;
        }
        return left.height();
    }
    //返回右子树的高度
    public int rightHeight(){
        if (right == null){
            return 0;
        }
        return right.height();
    }
    //返回当前树的高度
    public int height(){
        return Math.max(left == null ? 0 : left.height() , right == null ? 0 : right.height()) + 1;
    }
    //添加方法
    public void add(AVL avl){
        if (avl == null){
            System.out.println(" 节点为空无法添加 ");
            return;
        }
        //如果添加进来的节点值小于当前根节点就添加到左叶子节点上
        if (this.val > avl.val){
            if (this.left == null) {
                this.left = avl;
            }else {
                this.left.add(avl);
            }
        }else {
            if (this.right == null){
                this.right = avl;
            }else{
                this.right.add(avl);
            }
        }

        //左旋 右树高度比左树高度高过1就进行左旋
//        if (rightHeight() - leftHeight() > 1){
//
//            // 当这棵树的右子树的左子树 比 这棵树的右子树的右子树高 这棵右子树需要先进行向右旋转
//            // 再进行左旋
//            if (right != null && right.leftHeight() > right.rightHeight()){
//                right.rightHeight();
//            }
//            leftRotate();
//            return;
////            if (right != null && right.leftHeight() > right.rightHeight()){
////                right.rightHeight();
////                leftRotate();
////            }else {
////                leftRotate();
////            }
//
//        }
//        //右旋 左树的高度比右树的高度高过1就要向右边旋转
//        if (leftHeight() - rightHeight() > 1){
//
//            // 如果这棵树的左子树的右子树 比 这棵树的左子树的左子树高 就要先对这棵左子树进行向左旋转
//            // 再进行向右旋转
//            if (left != null && left.rightHeight() > left.leftHeight()){
//                left.leftRotate();
//            }
//            rightRotate();
//        }
    }

    //查找要删除的节点
    public AVL Search(int val){
//        if (this.val == val){
//            return this;
//            //要找的节点小于当前节点就从左边递归继续找
//        } else if (val < this.val){
//            if (this.left == null){
//                return null;
//            }
//            return this.left.Search(val);
//            //要找的节点小于当前节点就从右边递归继续找
//        } else {
//            if (this.right == null){
//                return null;
//            }
//            return this.right.Search(val);
//        }
        if (this.val == val){
            return this;
        }
        if (val < this.val && this.left != null){
            return this.left.Search(val);
        }
        if (this.right != null){
            return this.right.Search(val);
        }
        return null;
    }

    //查找要删除节点的父节点
    public AVL searchParent(int val){
        //通过他的父个节点去找要删除的节点
        if ( (this.left != null && this.left.val == val) || (this.right != null && this.right.val == val) ){
            return this;
            //要找的节点小于当前节点就从左边递归继续找
        }else if (this.left != null && val < this.val){

            return this.left.searchParent(val);
            //要找的节点小于当前节点就从右边递归继续找
        }else if (val >= this.val && this.right != null){

            return this.right.searchParent(val);
        }else {
            //找不到返回null
            return null;
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
    public AVL (int val ){
        this.val = val;
    }

    @Override
    public String toString() {
        return "avl{" +
                "val=" + val +
                '}';
    }
}

