package queue;
/*
使用数组模拟队列
不能重复使用

 */
public class ArrayQueue {
    public static void main(String[] args) {
        Queue queue = new Queue(3);
        queue.Add(1);
        queue.Add(2);
        queue.Add(3);
        queue.Show();
        int queue1 = queue.getQueue();
        int queue2 = queue.getQueue();
//        queue.getQueue();
        int i = queue.headQueue();
        System.out.println("i = " + i);
        System.out.println(queue1);
        System.out.println(queue2);
        queue.Show();
        queue.Add(4);

    }
}
class Queue{
    private int maxSize;//最大容量
    private int front;//队列头
    private int rear;//队尾
    private int[] arr;//存放数据模拟队列

    //队列初始化
    public Queue(int maxSize){
        this.maxSize =maxSize;
        arr = new int[maxSize];
        front = -1;//指向队列头部 分析出 front是指向队列头的前一个位置
        rear = -1;//队尾 指向队尾的数据
    }

    //判断队列是否满了
    public boolean isFull(){
        return rear == maxSize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据到队列
    public void Add(int n){
        if (isFull()){
            System.out.println(" 队满 ");
            return;
        }
        arr[++rear] = n;
    }

    //获取数据
    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");//抛出异常程序终止
        }
        front++;
        return arr[front];

    }

    //遍历
    public void  Show(){
        if (isEmpty()){
            System.out.println(" 没有数据 ");
            return;
        }
        for (int i : arr){
            System.out.println("i = " + i);
        }
    }

    //显示队列头
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("没有数据");
        }
        return arr[front + 1];
    }

        }