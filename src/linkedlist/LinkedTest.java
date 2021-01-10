package linkedlist;

import java.util.Stack;

public class LinkedTest{
    //获取单链表的节点的个数  带头单链表不统计头节点
    public int getLength(HeroNode head ){
        if (head.next == null){
            return 0;
        }
        int count = 0;
        HeroNode temp = head.next;
        while (temp != null){
            count++;
            temp = temp.next;
        }
        return count;
    }
//获取单链表倒数第k的节点
    public HeroNode findLastNode(HeroNode head ,int k){
        if (head.next == null){
            return null;
        }
        int size = this.getLength(head);
        if (k <= 0 || k > size  ){
            return null;
        }
        HeroNode temp = head.next;
        for (int i = 0; i < size - k ; i++){
            temp = temp.next;
        }
        return temp;
    }

//逆序打印链表节点 用栈  先进后出 后进先出 链表结构没有被改写
    public void reversPrint(HeroNode head){
        if (head.next == null){
            return ;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode temp = head.next;
        while (temp != null){
            stack.push(temp);
            temp = temp.next;
        }
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }



}
