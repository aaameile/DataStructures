package tree;
/*
二叉树
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        HeroNode root = new HeroNode(1,"tom1");
        HeroNode node2 = new HeroNode(2,"tom2");
        HeroNode node3 = new HeroNode(3,"tom3");
        HeroNode node4 = new HeroNode(4,"tom4");
        HeroNode node5 = new HeroNode(5,"tom5");

        //手动创建一棵二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setLeft(node4);
        node3.setRight(node5);
        tree.setRoot(root);
//        tree.infixOrder();
//        System.out.println("  ");
//        tree.postSearch(5);
//        System.out.println("  ");
//        tree.infixSearch(5);
//        System.out.println("  ");
//        tree.preSearch(5);
//        if (heroNode == null){
//            System.out.println(" 没找到 ");
//        }else {
//            System.out.println( " 找到 " + heroNode);
//        }

        System.out.println(" 删除前 ");
        tree.preOrder();
        tree.delNode(3);
        System.out.println(" 删除后 ");
        tree.preOrder();



    }

}

//初始化树二叉树
class BinaryTree{
     private HeroNode root;//根节点
    //传一个节点作为根节点
     public void setRoot(HeroNode root){
         this.root = root;
     }



     //先序遍历
    public void preOrder(){
         if (this.root != null){
             this.root.preOrder();
         }else {
             System.out.println(" 树为空 ");
         }

    }
    //中序遍历
    public void infixOrder(){
        if (this.root != null){
            this.root.infixOrder();
        }else {
            System.out.println(" 树为空 ");
        }
    }
    //后序遍历
    public void postOrder(){
        if (this.root != null){
            this.root.postOrder();
        }else {
            System.out.println(" 树为空 ");
        }
    }




    //先序查找
    public HeroNode preSearch(int no){
         HeroNode temp = null;
         if (root != null){
             temp = root.proSearch(no);
         }
         if (temp == null){
             System.out.println(" 没找到 ");
         }else {
             System.out.println(" 找到 " + temp);
         }
         return temp;
    }
    //中序查找
    public HeroNode infixSearch(int no){
        if (root != null){
            return root.infixSearch(no);
        }else {
            return null;
        }
    }
    //后序查找
    public HeroNode postSearch(int no){
        if (root != null){
            return root.postSearch(no);
        }else {
            return null;
        }
    }



    //删除节点
    public void delNode(int no){
         if (root != null){
             if (root.getNo() == no){
                 root = null;//root 节点是要删除的节点就直接将root删除
             }else{
                 root.delNode(no);//递归删除
             }
         }else {
             System.out.println(" 空树 ");
         }
    }
}



//节点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;//默认为null
    private HeroNode right;//默认为null

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }




    // 先序遍历 根左右
    public void preOrder(){
        //输出父节点
        System.out.println(this);

        //递归输出左节点  每一次递归都会输出一个节点
        if (this.left != null){
            this.left.preOrder();
        }

        //递归输出右节点
        if (this.right != null){
            this.right.preOrder();
        }
    }
    // 中序遍历 左根右
    public void  infixOrder(){
        if (this.left != null){
            this.left.infixOrder();
        }

        System.out.println(this);

        if (this.right != null){
            this.right.infixOrder();
        }
    }
    // 后序遍历 左右根
    public void postOrder(){
        if (this.left != null){
            this.left.postOrder();
        }

        if (this.right != null){
            this.right.postOrder();
        }

        System.out.println(this);
    }





    //先序查找
    public HeroNode proSearch(int no){
//        System.out.println(" 次数+1 ");
        HeroNode temp = null;
        //找到就返回
        if (this.no == no){
            return this;
        }

        //左递归找
        if (this.left != null){
            temp = this.left.proSearch(no);
        }

        //左递归找到就直接返回
        if (temp != null){
            return temp;
        }

        //右递归找
        if (this.right != null){
            temp = this.right.proSearch(no);
        }
        return temp;
    }
    //中序查找
    public HeroNode infixSearch(int no){

        HeroNode temp = null;
        //左递归找
        if (this.left != null){
            temp = this.left.infixSearch(no);
        }
        if (temp != null){
            return temp;
        }
        //找到就返回
        System.out.println(" 次数+1 ");
        if (this.no == no){
            return this;
        }

        //右递归找
        if (this.right != null){
            temp = this.right.infixSearch(no);
        }
        return temp;
    }
    //后序查找
    public HeroNode postSearch(int no){

        HeroNode temp = null;

        //左递归找
        if (this.left != null){
            temp = this.left.postSearch(no);
        }

        //左递归找到就直接返回
        if (temp != null){
            return temp;
        }

        //右递归找
        if (this.right != null){
            temp = this.right.postSearch(no);
        }

        if (temp != null){
            return temp;
        }
        System.out.println(" 次数+1 ");
        if (this.no == no){
            return this;
        }

        return temp;
    }



    //删除节点 找到左右节点的上一个节点
    //叶子节点直接删除 非叶子节点直接删除树
    public void delNode(int no){
        if (this.left != null && this.left.no == no ){//左子树查找到就置空 没找到就找右子树
            this.left =null;
            return;
        }
        if (this.right != null && this.right.no == no){//右子树查找到就置空 没找到就递归 左 右
            this.right = null;
            return;
        }
        if (this.left != null){
            this.left.delNode(no);
        }
        if (this.right != null){
            this.right.delNode(no);
        }
    }


















}
