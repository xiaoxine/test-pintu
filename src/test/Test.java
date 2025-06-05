package test;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        //test 一维数组 打乱 4个一组存放到二维

        int[] tempArray ={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};

        Random random = new Random();
        //打乱
        for (int i = 0; i < tempArray.length; i++) {
            int index = random.nextInt(tempArray.length);
            //交换
            int temp = tempArray[i];
            tempArray[i] = tempArray[index];
            tempArray[index] = temp;
        }
        //输出
        for (int i = 0; i < tempArray.length; i++) {
            System.out.print(tempArray[i]+" ");
        }
        System.out.println();
        int index =0;
        //二维数组，way1
        int[][] data =new int[4][4];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = tempArray[index];//或index=0，index++，，， tempArray[i*4+j]
                System.out.print(data[i][j]+" ");
                index++;
            }
            System.out.println();
        }
        //way2,一层循环，data[i/4][i%4] = tempA[i];
    }
}
