package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/*
图
v1
    二维数组的第几个元素
v2
    二维数组的第几个元素内的第几个元素
 */
//无向图
public class Graph {
    private ArrayList<String> vertexList;//顶点
    private int[][] edges;//邻接矩阵 边
    private int numOfEdge;//边的数量
    private boolean[] isVisited;//记录顶点是否被访问到

    public Graph(int n){//顶点有几个n就为几
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdge = 0;
        isVisited = new boolean[n];
    }

    //插入顶点 将顶点插入到ArrayList
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    //插入边
    // v1 二维数组的第几个元素
    // v2 二维数组的第几个元素中的第几个值
    // weight 1 和 0  1 代表能直接连接 0 表示不能
    public void insertEdge(int v1 ,int v2 ,int weight){
        edges[v1][v2] = weight;

        //无向图所以要考虑反方向
        edges[v2][v1] = weight;
        numOfEdge ++;
    }

    //返回节点的个数 数组的大小
    public int getVertexNumber(){
        return vertexList.size();
    }

    //得到边条数
    public int getNumOfEdge(){
        return numOfEdge;
    }

    //返回节点i在ArrayList 的下标对应的数据
    public String getValue(int i){
        return vertexList.get(i);
    }

    //得到权值
    public int getWeight(int v1, int v2){
        return edges[v1][v2];
    }

    //显示图对应的矩阵 遍历二维数组
    public void show(){
        for (int[] link : edges){
            System.out.println(Arrays.toString(link));
        }
    }

    //得到第一个邻接节点的下标 w
    public int getFirstNeighbor(int index){
        for (int j = 0 ; j < vertexList.size() ; j++){
            if(edges[index][j] > 0){
                return j;
            }
        }
        return -1;
    }

    //根据前一个邻接节点的下标获取下一个邻接节点下标
    public int getNextNeighbor(int v1 , int v2){
        for (int j = v2 + 1 ; j < vertexList.size() ; j++){
            if (edges[v1][j] > 0){
                return j;
            }

        }
        return -1;
    }

    //深度优先遍历
    // i表示从第一个顶点开始深度遍历
    private void DFS(boolean[] isVisited , int i){
        System.out.print(getValue(i));//输出第一个访问的顶点
        isVisited[i] = true;//标记第一个顶点为被访问
        int w = getFirstNeighbor(i);//获取他的第一个邻接顶点的下标  w
        while (w != -1){//获取到了w
            if (!isVisited[w]){//并且没被访问过
                DFS(isVisited,w);//将w作为新的i继续递归深度遍历
            }
            w = getNextNeighbor(i,w);//被访问过  就找下一个没被访问的顶点 继续递归 i 为二维数组的内层 w 为二维数组的外层
        }
        //如果没有获取到会执行getNextNeighbor（）
    }

    //遍历所有结点进行dfs
    public void DFS(){
        for (int i = 0; i < vertexList.size(); i++){
            if (!isVisited[i]) {
                DFS(isVisited,i);
            }
        }
    }

    //广度优先遍历
    public void BFS(boolean[] isVisited , int i){
        int u;//队列头结点对应的下标
        int w;//邻接节点w
        LinkedList<Object> queue = new LinkedList<>();
        System.out.println(getValue(i));
        isVisited[i] = true;
        queue.addLast(i);
        while (!queue.isEmpty()){
             u = (Integer) queue.removeFirst();
             w = getFirstNeighbor(u);
             while (w != -1){
                 if (!isVisited[w]){
                     System.out.println(getValue(w));
                     isVisited[w] = true;
                     queue.addLast(w);
                 }
                 w = getNextNeighbor(u,w);//回溯
             }
        }

    }

    //广度优先
    public void BFS(){
        for (int i = 0 ;i < getVertexNumber();i++){
            if (!isVisited[i]){
                DFS(isVisited,i);
            }
        }
    }



    public static void main(String[] args) {
        int n = 5;
        String[] Vertex = {"A","B","C","D","E"};
        Graph graph = new Graph(n);
        for (String vertex : Vertex){
            graph.insertVertex(vertex);
        }
        // AB AC BC BD BE
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);

        graph.show();
//        graph.DFS();
        graph.BFS();
    }
}
