package linkedlist;
/*
单链表
    head
    不存放具体数据 只代表单链表表头
    节点

 */

public class SingleLinkedList {
    public static void main(String[] args) {
        HeroNode node = new HeroNode();
        System.out.println(node.next);
        HeroNode heroNode = new HeroNode(1, "林冲", "豹子头");
        HeroNode heroNode2 = new HeroNode(2, "武松", "打虎");
        System.out.println(node.next = heroNode);
        SingleLinked linked = new SingleLinked();
        linked.Add(heroNode);
        linked.Add(heroNode2);
        linked.list();

        //求节点个数
        System.out.println("节点个数为");
        LinkedTest test = new LinkedTest();
        System.out.println(test.getLength(linked.head));

        //倒数第k个节点
        System.out.println("倒数第k个节点为");
        System.out.println(test.findLastNode(linked.head, 1));

        //逆序输出链表节点
        System.out.println(" 逆序输出 ");
        test.reversPrint(linked.head);
    }

}

//创建单链表 管理数据
class SingleLinked{
    //初始化头结点 不存放任何数据 为了找到头位置
    public HeroNode head = new HeroNode(0,"","");

    //添加节点 到单向链表
    // 不考虑编号顺序
    // 找到当前链表的最后节点 头结点不能动 定义一个辅助变量代表头结点 然后遍历链表 找到最后节点
    // 将这个节点的next指向新的节点
    public void Add(HeroNode node){
        //定义一个辅助变量代表头结点 然后遍历链表 找到最后节点 将他指向新的节点
        HeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    //添加时按照排名插入到指定位置
    // 如果有排名 则添加失败 并给出提示
    public void AddByOrder(HeroNode node){
        //任然通过辅助指针来找到添加的位置
        //如果辅助变量的next域大于要添加节点的编号那么就可以添加
        //添加在辅助变量的next域前
        //将辅助变量的next 赋值给 新节点的next 此时2个节点指向 辅助节点的next
        //再将新节点指向赋值变量的next
        // a  b  c     把b添加到a和c中     ==    c的地址赋值给b的node属性   b的地址赋值给a的node属性
    }
//删除原理  在java中 只要一个对象的地址没有被指针引用就会被回收

    //遍历链表
    public void list(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println(" 没有数据 ");
        }
        //不为空再遍历
        HeroNode temp = head;
//        while (temp.next != null){
//            temp = temp.next;
//            System.out.println("temp = " + temp);
//        }
        while (temp != null) {
            System.out.println("temp = " + temp);
            temp = temp.next;
        }

        }

}

//定义链表节点
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;//下一个节点

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }
    public HeroNode(){

    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname +
                '}';
    }
}