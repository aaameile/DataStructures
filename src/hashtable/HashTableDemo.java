package hashtable;

/*
散列表
 */

public class HashTableDemo {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(5);
        Emp emp = new Emp(1,"tom");
        Emp emp1 = new Emp(2,"tom");
        Emp emp2 = new Emp(3,"tom");
        Emp emp3 = new Emp(4,"tom");
        Emp emp4 = new Emp(6,"tom4");
        Emp emp5 = new Emp(6,"tom5");
        Emp emp6 = new Emp(11,"tom6");
        hashTable.AddEmpLink(emp);
        hashTable.AddEmpLink(emp1);
        hashTable.AddEmpLink(emp2);
        hashTable.AddEmpLink(emp3);
        hashTable.AddEmpLink(emp4);
        hashTable.AddEmpLink(emp5);
        hashTable.AddEmpLink(emp6);

        hashTable.ListLinked();

        hashTable.findById(6);

        hashTable.delById(11);

        hashTable.ListLinked();
        System.out.println(11%5);






    }

}



//哈希表
class HashTable{
    private EmpLinkedList[] empLinkedList;
    private int size;//用来计算哈希值

    public HashTable(int size){
        this.size = size;
        empLinkedList = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLinkedList[i] = new EmpLinkedList();
        }
    }

    //哈希值
    public int Hashcode(int id){
        return id % size;
    }

    //添加方法
    void AddEmpLink(Emp emp){
        int hashcode = Hashcode(emp.id);
        empLinkedList[hashcode].AddEmp(emp);
    }

    //遍历哈希表 调用的是员工的遍历
    void ListLinked(){
        for (int i = 0; i < size; i++) {
            empLinkedList[i].List(i);
        }
    }

    //根据id往表里查找员工
    void findById(int id){
        int hashcode = Hashcode(id);
        Emp emp = empLinkedList[hashcode].findByEmpId(id);
        if (emp == null){
            System.out.println(" 该哈希表中没有这个员工 ");
        }else {
            System.out.println(" 找到链表 " + hashcode + " " + emp + " " + id);
        }
    }

    //删除员工
    void delById(int id){
        int hashcode = Hashcode(id);
        int i = empLinkedList[hashcode].delById(id);
        if (i == 0){
            System.out.println(" 表为空 ");
        }else
        if (i == -1){
            System.out.println(" 没找到 ");
        }else
        if (i == 1){
            System.out.println(" 删除成功 ");
        }

    }

}





//创建emp链表
class EmpLinkedList{
    private Emp head = null; //头结点 使他为空 当添加第一个元素的时候才给他赋值
    //添加方法
    void AddEmp(Emp emp){
        //如果头结点为空就直接给头结点赋值
        if (head == null){
            head = emp;
//            System.out.println("emp = " + emp);
            return;
        }
        //默认员工id是自动增长所以添加直接从链表最后一个元素添加
        Emp temp = head;
        //找到最后一个元素 然后添加进去
        while (temp.next != null){
            temp = temp.next;
        }
        temp.next = emp;
    }

    //遍历员工
    void List (int num){
        if (head == null){
            System.out.println(" 链表 " + num + " 为空 ");
            return;
        }

        Emp temp = head;//只要节点不为空就一直输出 输出一次往下移一次
        while (temp != null){

            System.out.print(" 链表 " + num + " " + temp);
            temp = temp.next;
        }
        System.out.println();
//        while (true){
//            System.out.printf(" 链表 " + num , temp.id,temp.name);
//            if (temp.next == null){
//                break;
//            }
//            temp = temp.next;
//        }
//        System.out.println();
    }

    //查找员工
    Emp findByEmpId(int id){
        Emp temp = head;
        if (temp == null){//头结点为空就不用找了直接返回null
            return null;
        }
        while (id != temp.id) { // 找到就拿着找到的节点退出 没有找到就返回null
            if (temp.next == null) {
                temp = null;
                break;
            }
            temp = temp.next;
        }
        return temp;
    }

    //删除员工
    int delById(int id){
//        int val = -1;
        Emp temp = head;
//        boolean flag = false;

//        while (true) {

            if (temp == null){//空链表就直接退出
//                val = 0;
//                break;
                return 0;
                }

            if (head.id == id){//头结点是要删除的节点 直接将头节点的下一个节点当做新的头结点
                head = head.next;
    //                val = 1;
    //                break;
                return 1;
            }

//            if (temp.next == null) {//没找到
//                return -1;
//            }

            while (temp.next != null){//找到就删除
                if (temp.next.id == id) {
                    temp.next = temp.next.next;
//                flag = true;
//                break;

                    return 1;
                }
                temp = temp.next;//下移
            }
//        }

         // 删除 将下下个节点的地址拿到当前节点的下一个节点上
//            val = 1;
        return -1;

    }

}




//员工类
class Emp{
    int id;
    String name;
    Emp next;//下一个节点

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", next=" + next +
                '}';
    }
}



