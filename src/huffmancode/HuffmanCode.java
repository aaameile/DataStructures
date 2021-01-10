package huffmancode;

import java.io.*;
import java.util.*;

/*
赫夫曼编码
 */
public class HuffmanCode {
    public static void main(String[] args) {
        String str = "i like like like java do you like a java";

        //将字符串转换成字节存到数组里方便后面统计
        byte[] strBytes = str.getBytes();//一个字节表示一个字符 utf-8
//        System.out.println(Arrays.toString(strBytes));
        //封装好的方法
        byte[] bytes = huffmanZip(strBytes);
        System.out.println(Arrays.toString(bytes));

        byte[] bytes1 = deCode(mapCode, bytes);
//        System.out.println(Arrays.toString(bytes1));
        System.out.println(new String(bytes1));

        String srcFile = "C:\\workspace.idea\\DataStructures\\data.txt";
        String dstFile = "C:\\workspace.idea\\DataStructures\\data.zip";

        String aaa = "C:\\workspace.idea\\DataStructures\\data2.txt";
        zipFile(srcFile,dstFile);
        unZipFile(dstFile,aaa);
        System.out.println("压缩成功");

        //统计字符个数
//        ArrayList<Node> node = getNode(strBytes);
//        System.out.println(node);
//
//        //各个字符按照哈夫曼树存储
//        Node huffman = creatHuffman(node);
//        huffman.preOrder();
//
//        //生成哈夫曼编码
//        getCode(huffman,"",stringBuilder);
//        System.out.println("哈夫曼编码" + map);
//
//        //测试数据压缩
//        byte[] zip = Zip(strBytes, map);
//        System.out.println(Arrays.toString(zip));

//        System.out.println(map.get(2));

//        char[] chars = str.toCharArray();
//        for (char c : chars){
//            System.out.println("c = " + c); //输出字符 utf-8
//        }
//        System.out.println(str.length());
//        System.out.println(strBytes.length);
    }

    //将所有方法封装为一个
    public static byte[] huffmanZip(byte[] bytes){
        ArrayList<Node> node = getNode(bytes);
        Node huffman = creatHuffman(node);
        getCode(huffman,"",stringBuilder);
        return Zip(bytes, mapCode);
    }


    //前序遍历
    public static void preOrder(Node node){
        if (node != null){
            node.preOrder();
        }else {
            System.out.println(" 树为空 ");
        }
    }



    //把传过来的数组里的字节进行处理 计算出每个字节各有多少个
    private static ArrayList<Node> getNode(byte[] bytes) {
        //用来装处理过后的节点方便后面排序取值 构建哈弗曼树
        ArrayList<Node> list = new ArrayList<>();

        //利用map 的key val原理  key 代表字符 val 代表个数将数据进行统计
        // 一串字符串中相同字符出现多少次
        HashMap<Byte, Integer> map = new HashMap<>();
        for (byte b : bytes) {

            //jdk8新增 这里是计算一串字符串中相同字符出现多少次 同时将这一对值put进入map中
            map.merge(b, 1, Integer::sum);
//            Integer count = map.get(b);
//            if (count == null) {
//                map.put(b, 1);
//            } else {
//                map.put(b, count + 1);
//            }
        }

        //取出Entry数组中每个元素的值  HashMap中获取key val的方式
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            //分别获取key作为节点的data val作为节点的weight 再一个一个添加到ArrayList数组中
            list.add(new Node(entry.getKey(), entry.getValue()));
        }
        //返回的数组就是用来创建哈夫曼数的节点数组
        return list;
    }



    //构建哈夫曼树
    private static Node creatHuffman(ArrayList<Node> list){
        while (list.size() > 1){
            Collections.sort(list);
            Node left = list.get(0);
            Node right = list.get(1);
            Node root = new Node(null,left.weight + right.weight);
            root.right = right;
            root.left = left;

            list.remove(left);
            list.remove(right);
            list.add(root);
        }
        return list.get(0);
    }



    //获取哈夫曼编码
    //定义一个map来装路径值 某个字符在哈弗曼树上的路径
    static Map<Byte,String> mapCode = new HashMap<>();
    //用来拼接到达某个字符节点的路径
    static StringBuilder stringBuilder = new StringBuilder();
    //递归寻找每个字符 同时在路径上设置值 左边为0 右边为1  找到一个字符就将他的路径和具体值存放到map中 路径用code表示 0 1
    private static void getCode(Node node,String code,StringBuilder stringBuilder){
        //拿着传进来的路径进行拼接
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        stringBuilder1.append(code);


        if (node != null){
            if (node.data == null){
                //左右递归查找字符 并记录他的路径值
                getCode(node.left,"0",stringBuilder1);
                getCode(node.right,"1",stringBuilder1);
            }else {
                //将每个找到的字符及路径值存放到map中
                mapCode.put(node.data,stringBuilder1.toString());
            }
        }

    }



    //将字符串对应的byte数组 通过生成的哈夫曼表 返回一个哈夫曼编码 压缩后的byte[]
    private static byte[] Zip(byte[] bytes, Map<Byte,String> map){
        StringBuilder builder = new StringBuilder();

        //通过map的get方法传入key查询value值  查出原始字符串转换后的的每个字符对应的哈夫曼编码再拼接起来
        for (Byte b : bytes){
            builder.append(map.get(b));
        }
//        System.out.println(builder.toString());

        //根据拼接后的字符串按照二进制位数 求存放压缩后数据的数组长度
        int length;
        if (builder.length() % 8 == 0){
            length = builder.length() / 8;
        }else {
            length = builder.length() / 8 + 1;
        }

        //创建数组存放压缩后的数据
        byte[] zip = new byte[length];
        int index = 0;
        for (int i = 0; i < builder.length() ; i += 8){

            //将之前拼接的哈夫曼编码按照二进制位数取出
            String str ;
            if (i + 8 > builder.length() ){
              str = builder.substring(i);//8个数为一组截取
            }else{
              str = builder.substring(i,i + 8);
            }

            //将取出的字符串转换为-88这种 二进制
            zip[index] = (byte) Integer.parseInt(str,2);
            index ++;

        }
        return zip;
    }



    //解压缩 拿到压缩过的数组进行还原成哈夫曼编码表 再用这个哈夫曼编码表 反向查找 字符 最后转换就还原成功
    private static byte[] deCode(Map<Byte,String> codeMap,byte[] zip){
        // 使用StringBuilder拼接每个解压缩的字符 还原到原来的哈夫曼编码表
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < zip.length;i++){
            byte b = zip[i];
            boolean flag = (i == zip.length - 1);
            String s = byteToBitString(!flag, b);
            builder.append(s);
        }
//        System.out.print(builder.toString());
//        System.out.println();

        // 拿之前存在map中的哈夫曼编码表 取出key、字符  value、编码存到新的map
        // key value 调换 放进一个新的map中 key为路径 value 为字符  反向查找 根据哈夫曼编码找字符
        HashMap<String, Byte> map = new HashMap<>();
        for(Map.Entry<Byte,String> entry : codeMap.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }
//        System.out.println(map.toString());
        //将查找到的字符装到list里
        ArrayList<Byte> list = new ArrayList<>();
        for (int i = 0;i < builder.length();){
            int count = 1;//标记
            boolean flag = true;
            Byte b = null;//用来代表查找到的字符
            while (flag){
                String s = builder.substring(i, i + count);//从字符串0开始截取查找每次查找都会往后面加1直到找到一个对应的字符
                b = map.get(s);
                if (b == null){
                    count ++;
                }else {
                    flag = false;//退出
                }
            }
            list.add(b);
            i += count;//i直接移动到count 意味着找到一个字符后下次直接从这个字符的下一个开始
        }
        //将list中的字符取出放进数组里
//        System.out.println(list.toString());

        byte[] bytes = new byte[list.size()];
        for (int i = 0;i < bytes.length;i++){
             bytes[i] = list.get(i);
        }
        return bytes;

    }



    //将压缩后的数据还原成哈夫曼编码表
    public static String byteToBitString(boolean flag,byte b){
        int temp = b;//将数组b成int
        //如果是正数需要补高位
        if (flag){
            temp |= 256;//按位与256 1 0000 0000 | 0000 0001 -> 1 0000 0001
        }
        String str = Integer.toBinaryString(temp);//返回的是temp对应的二进制补码
        if (flag){
            return str.substring(str.length() - 8);
        }else {
            return  str;
        }
    }



    //压缩文件
    public static void zipFile(String srcFile,String dstFile){
        try (FileInputStream in = new FileInputStream(srcFile);
             FileOutputStream out = new FileOutputStream(dstFile);
             ObjectOutputStream oOut = new ObjectOutputStream(out)) {
            byte[] bytes = new byte[in.available()];
            int read = in.read(bytes);
            byte[] zip = huffmanZip(bytes);
            oOut.writeObject(zip);
            oOut.writeObject(mapCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //文件解压
    public static void unZipFile(String zipFile ,String dstFile){
        InputStream in = null;
        ObjectInputStream oIn = null;
        FileOutputStream out = null;
        try{
             in = new FileInputStream(zipFile);
             oIn = new ObjectInputStream(in);
             out = new FileOutputStream(dstFile);
            byte [] zip = (byte[]) oIn.readObject();
            Map<Byte,String>  Code= (Map<Byte, String>) oIn.readObject();
            byte[] bytes4 = deCode(Code, zip);
            out.write(bytes4);
        }catch (Exception e){
            e.getMessage();
        }finally {
            try {
                assert in != null;
                in.close();
                assert oIn != null;
                oIn.close();
                assert out != null;
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}





//节点
class Node implements Comparable<Node>{
    Byte data;//存放的数据 字符 'a' 97  ' ' 32
    int weight;//权重 这里代表的是每个字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }


    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }
}