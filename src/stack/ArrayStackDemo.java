package stack;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        stack.Push(1);
        stack.Push(2);
        stack.Push(3);
        stack.Push(4);
        stack.List();
//        stack.Pop();
//        stack.List();

    }

}

//定义栈结构
class ArrayStack{
    private int maxSize;//栈大小
    private  int[] stack;//数组模拟栈 数据就放这个数组中
    private int top = -1;//栈顶初始化为-1表示没有数据

    //构造一个栈
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //栈满
    public boolean isFull(){
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈
    public void Push(int stack1){
        if (isFull()){
            System.out.println(" 栈满 ");
            return;
        }
        top ++;
        stack[top] = stack1;
    }

    //出栈
    public int Pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历 需要从栈顶开始
    public void List(){
        if (isEmpty()){
            System.out.println(" 没有数据 ");
            return;
        }
        for (int i = top; i >= 0 ; i--) {
            System.out.println(i + "=" + stack[i]);
        }

    }
}