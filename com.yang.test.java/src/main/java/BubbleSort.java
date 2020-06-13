import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
//        bubbleSort(new int[] {1, 2, 3, 4, 5, 6});
        bubbleSort(new int[] {6, 5, 4, 3, 2, 1});
    }
    //产生一个随机数组，数组的长度和值都是随机的，
    public static  int[] generateRandomArray(int size,int value){
        //在java中，Math.random() ->double(0,1)
        //(int)((size+1)*Math.random())--->产生的是[0,size]之间的整数
        //生成长度随机的数组，数组的最大长度是size的长度
        int[] arr = new int[(int)((size+1)*Math.random())];
        for(int i = 0 ;i<arr.length;i++){
            //针对数组中的每个值都可以随机一下，一个随机数减去另外一个随机数，可能产生正数，也可能产生负数
            arr[i]=(int)((value+1)*Math.random())-(int)(value*Math.random());//值也可以是随机的
        }
        return arr;
    }
    //复制数组
    public static int[] copyArray(int[] arr){
        if(arr==null){
            return null;
        }
        int[] res = new int[arr.length];
        for(int i = 0 ;i<arr.length;i++){
            res[i]=arr[i]  ;
        }
        return res;
    }
    //绝对正确的方法,这个方法可以时间复杂度很差，但是要保证其准确性
    public static void rightMethod(int[] arr){
        Arrays.sort(arr);
    }
    //
    public static boolean isEqual(int[] arr1,int[] arr2){
        if(arr1==null&&arr2!=null||arr1!=null&&arr2==null){
            return false;
        }
        if (arr1==null&&arr2==null){
            return true;
        }
        if (arr1.length!=arr2.length){
            return false;
        }
        for(int i = 0;i<arr1.length;i++){
            if(arr1[i]!=arr2[i]){
                return false;
            }
        }
        return true;
    }
    //打印出数组
    public static void printArray(int[] arr){
        if(arr==null){
            return;
        }
        for(int i = 0 ;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    //N个数字冒泡排序，总共要进行N-1趟比较，每趟的排序次数为(N-i)次比较
    public static void bubbleSort(int[] arr){
        //一定要记住判断边界条件，很多人不注意这些细节，面试官看到你的代码的时候都懒得往下看，你的代码哪个项目敢往里面加？
        if(arr==null||arr.length<2){
            return;
        }
        //需要进行arr.length趟比较

        for(int i = 0 ;i<arr.length-1;i++){
        	System.out.println("第" + (i+1) + "趟");
            //第i趟比较
            for(int j = 0 ;j<arr.length-i-1;j++){

            	System.out.println("比较第" + (j+1) + "个");
                //开始进行比较，如果arr[j]比arr[j+1]的值大，那就交换位置
                if(arr[j]>arr[j+1]){
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }

        }
//        System.out.println("最终得出的数组为：");
//        for (int k =0 ; k < arr.length;k++){
//            System.out.print(arr[k]+" ");
//        }
    }

    //生成一个对数器。产生一个随机样本的数组，数组的长度和值都是随机的
    //size是生成数组的最大长度
//    public static int[] generateRandomArray(int size,int value){
//        //生成长度随机的数组
//        int[] arr = new int[(int)((size+1)*Math.random())];
//        for(int i = 0 ;i<arr.length;i++){
//            arr[i]=(int)((value+1)*Math.random())-(int)(value*Math.random());
//        }
//        return arr;
//    }

    //for test
    public static  void rightMathods(int[] arr){
        //调用系统调用的函数来进行验证
        Arrays.sort(arr);
    }
}