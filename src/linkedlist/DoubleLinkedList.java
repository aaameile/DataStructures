package linkedlist;

public class DoubleLinkedList {
}
class DoubleLinked{
    public DoubleNode head = new DoubleNode(0,"","");//头结点

    //遍历
    public void list(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println(" 没有数据 ");
        }
        //不为空再遍历
        DoubleNode temp = head;
//        while (temp.next != null){
//            temp = temp.next;
//            System.out.println("temp = " + temp);
//        }
        while (temp != null) {
            System.out.println("temp = " + temp);
            temp = temp.next;
        }

    }

    //添加
    public void Add(DoubleNode node){
        //定义一个辅助变量代表头结点 然后遍历链表 找到最后节点 将他指向新的节点
        DoubleNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;//双链表的最后一个节点的地址赋给temp的next属性 temp的next指针指向node
        node.pre = temp;//temp的地址赋给双向链表最后一个节点的pre属性  node 的pre指向temp
    }

    //删除
    //temp = head.next
    //temp.pre.next = temp.next
    //temp.next.pre = temp.pre

}
class DoubleNode{
    public int no;
    public String name;
    public String nickname;
    public DoubleNode next;//下一个节点 默认为null
    public DoubleNode pre;//前一个节点

    public DoubleNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "DoubleNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}