package sparsearray;
/*
稀疏数组
 */
public class SparseArray {
    public static void main(String[] args) {
        //原始二维数组、
        int[][] chessArray = new int[4][5];
        chessArray[0][3] = 1;
        chessArray[1][2] = 4;
        chessArray[1][4] = 4;
        chessArray[2][1] = 2;
        chessArray[3][2] = 3;
        chessArray[3][3] = 6;
        for (int[] row : chessArray){
            for (int data : row){
                System.out.print("  " + data);
            }
            System.out.println();
        }
        System.out.println(" 原始二维数组 ");

        //二维数组转稀疏数组
        //先遍历二维数组 得到有效值的个数
        int sum = 0;
        for (int i = 0; i < 4;i++){
            for (int j = 0; j < 5; j++) {
                if (chessArray[i][j] != 0){
                    sum ++;
                }
            }
        }
        System.out.println("sum = " + sum);
        //创建对应的稀疏数组并 赋值 根据稀疏数组的特性
        int[][] sparseArr = new int [sum + 1][3];
        sparseArr[0][0] = 4;
        sparseArr[0][1] = 5;
        sparseArr[0][2] = 5;
        int count = 0;
        for (int i = 0; i < 4;i++){
            for (int j = 0; j < 5; j++) {
                if (chessArray[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArray[i][j];
                }
            }
        }
        System.out.println();

        //输出稀疏数组
        for (int[] row : sparseArr){
            for (int data : row){
                System.out.print("  " + data);
            }
            System.out.println();
        }
        System.out.println(" 稀疏数组 ");
        System.out.println();

        //稀疏数组转换成二维数组 根据稀疏数组特性
        int [][] chessArray2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            chessArray2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        //遍历
        for (int[] row : chessArray2){
            for (int data : row){
                System.out.print("  " + data);
            }
            System.out.println();
        }
        System.out.println(" 原始数组 ");

    }



}
