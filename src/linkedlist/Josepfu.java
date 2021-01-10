package linkedlist;

public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinked linked = new CircleSingleLinked();
        linked.Add(5);
        linked.Print();
    }
}


//环形单向链表
class CircleSingleLinked{
    //创建一个first节点
    public Node first = new Node(-1);

    //创建节点为nums的环形链表
    public void Add(int nums){
        if(nums < 1){
            System.out.println(" 人数不对 ");
            return;
        }
        Node temp = null;
        for (int i = 1; i <= nums; i++){
            Node node = new Node(i);//新加的节点
            if (i == 1){
                first = node;
                node.setNext(first);
                temp = first;
            }else {
                temp.setNext(node);
                node.setNext(first);
                temp = node;
            }
        }
    }


    //遍历
    public void Print(){
        if (first == null){
            System.out.println(" 没有节点 ");
        }
        Node temp = first;
        while (true){
            System.out.println(" 节点 " + temp.getNo());
            if (temp.getNext() == first){
                break;
            }
            temp = temp.getNext();
        }
    }


}
//节点
class Node{
    private int no;
    private Node next;

    public Node(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}