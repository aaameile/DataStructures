package stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
逆波兰表达式 后缀表达式
中缀表达式转换后缀表达式
将一个表达式转换成后缀表达式用栈进行运算

 */
public class PolandNotation {
    public static void main(String[] args) {
        //（30 + 4） x 5 - 6
        String str = "30 4 + 5 * 6 -";//后缀表达式
        System.out.println(str.length());
        List<String> string = getListString(str);
        System.out.println(string);
        System.out.println(calculate(string));
    }

    //将一个逆波兰表达式放入ArrayList
    public static List<String> getListString(String str){
        //分割
        String[] split = str.split(" ");
        return new ArrayList<>(Arrays.asList(split));
    }

    //完成计算
    public static int calculate(List<String> ls){
        //只需要一个栈
        Stack<String> stack = new Stack<>();
        for (String item : ls){
            if (item.matches("\\d+")){//正则表达式匹配多位数
                stack.push(item);
            }else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")){
                    res = num1 + num2;
                }else if(item.matches("-")){
                    res = num1 - num2;
                }else if (item.equals("*")){
                    res = num1 * num2;
                }else if (item.equals("/")){
                    res = num1 / num2;
                }else {
                    throw new RuntimeException("运算符错误");
                }
                stack.push(res + "");//转换成字符串
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
