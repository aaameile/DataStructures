package queue;
/*
环形队列
修改队头队初始值为0


 */
public class CircleArrayQueue {
    public static void main(String[] args) {
        CircleQueue queue = new CircleQueue(4);
        queue.Show();
        queue.Add(1);
        queue.Add(2);
        queue.Add(3);
//        queue.Add(4);
        queue.Show();
        System.out.println(queue.getQueue());
        queue.Add(4);
        queue.Show();


    }
}

class CircleQueue{
    private int maxSize;//最大容量
    private int front;//队列头 指向队列的第一个数据
    private int rear;//指向最后一个元素的后一个位置空出一个空间作为约定 可空可不空 这只是一种实现环形队列的思路 用来判断是否队满
    private int[] arr;//存放数据模拟队列

    //队列初始化
    public CircleQueue(int maxSize){
        this.maxSize =maxSize;
        arr = new int[maxSize];
    }

    //判断队列是否满了
    public boolean isFull(){
        return (rear + 1) % maxSize == front;
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
        arr[rear] = n;
        rear = (rear + 1) % maxSize;//小算法 a % b = 1  a < b

    }

    //获取数据
    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");//抛出异常程序终止
        }
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;

    }

    //遍历
    public void  Show(){
        if (isEmpty()){
            System.out.println(" 没有数据 ");
            return;
        }
       for (int i = front; i <front + size();i++){
           System.out.println(i % maxSize +"-"+ arr[i % maxSize]);
       }
    }

    public int size(){
        return (rear + maxSize - front) % maxSize;
    }
    //显示队列头
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("没有数据");
        }
        return arr[front];
    }

        }