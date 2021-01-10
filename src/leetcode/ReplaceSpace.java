package leetcode;

/*
请实现一个函数，将一个字符串中的每个空格替换成“%20”。
例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */
public class ReplaceSpace {
    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer("we are happy");
        System.out.println(new ReplaceSpace().replaceSpace(stringBuffer));
        long star = System.currentTimeMillis();

        System.out.println(new ReplaceSpace().replaceSpace2(stringBuffer));
        long stop = System.currentTimeMillis();
        System.out.println((stop - star));
    }
    public String replaceSpace(StringBuffer s){
        char[] arr = new char[s.length() * 3];
        int size = 0;
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (c == ' '){
                arr[size ++] = '%';
                arr[size ++] = '2';
                arr[size ++] = '0';
            }else {
                arr[size ++] = c;
            }
        }
        return new String(arr,0,size);
    }

    //方式2
    public String replaceSpace2(StringBuffer s) {

        return s.toString().replaceAll(" ", "%20");


    }
}
