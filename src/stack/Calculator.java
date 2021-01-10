package stack;
/*
栈实现计算器
栈原理 先入后出 看表达式的符合优先级
优先级高就出栈计算再入栈
 */
public class Calculator {
    public static void main(String[] args) {
        String str = "200+1*2/2-5" ;
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        int index = 0;//扫描是符号还是数字
        int num1;
        int num2;
        int ope;
        int res;
        char ch = ' ';//将每次扫描到的char保存到ch
        String keepNum= "";

        do {
//            ch = str.substring(index, index + 1).charAt(0);//扫描字符串转为单个字符
            ch = str.charAt(index);
            if (operStack.isOper(ch)) {
                if (!operStack.isEmpty()) {
                    if (operStack.Priority(ch) <= operStack.Priority(operStack.Peek())) {
                        num1 = numStack.Pop();
                        num2 = numStack.Pop();
                        ope = operStack.Pop();
                        res = numStack.Cal(num1, num2, ope);
                        numStack.Push(res);
                    }
                }
                operStack.Push(ch);
            } else {
                //不能发现一个数就入栈可能是多位数
                //扫描到数的时候向这个数的后面再看看是否还是数
                //是数就扫描 是符号就入栈
//                numStack.Push(ch - 48);//1和 字符1 相差48
                keepNum += ch;
                if (index == str.length()- 1){
                    numStack.Push(Integer.parseInt(keepNum));
                }else {
                    if (operStack.isOper(str.substring(index + 1,index +2).charAt(0))){
                        numStack.Push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }

            }
            index++;
        } while (index < str.length());
        while (!operStack.isEmpty()) {
            num1 = numStack.Pop();
            num2 = numStack.Pop();
            ope = operStack.Pop();
            res = numStack.Cal(num1, num2, ope);
            numStack.Push(res);
        }
        System.out.println("str = " + str + " = " + numStack.Pop());
    }
}
//定义栈结构
class ArrayStack2 {
    private int maxSize;//栈大小
    private int[] stack;//数组模拟栈 数据就放这个数组中
    private int top = -1;//栈顶初始化为-1表示没有数据

    //构造一个栈
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }
    //返回栈顶值但是不将他弹出
    public int Peek(){
        return stack[top];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void Push(int stack1) {
        if (isFull()) {
            System.out.println(" 栈满 ");
            return;
        }
        top++;
        stack[top] = stack1;
    }

    //出栈
    public int Pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历 需要从栈顶开始
    public void List() {
        if (isEmpty()) {
            System.out.println(" 没有数据 ");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.println(i + "=" + stack[i]);
        }

    }

    //返回运算级的优先级 自定义 用数字表示 优先级越大 数字越大
    //java char 和 int 可以同级 因为char 底层就是数字 所以传int型的字符 和数字比较
    //目前只有 + - * /
    public int Priority(int oper){
        if (oper == '*' || oper == '/'){
            return 1;
        }else if(oper == '+' || oper == '0'){
            return 0;
        }else {
            return -1;
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val){
        return val == '+' || val == '-' || val =='*' || val == '/';
    }

    //计算方法
    public int Cal(int num1,int num2,int oper){
        int res = 0;//存放计算结果
        switch (oper){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}