package math;



public class Test {
    public static void test1(){//二进制 左移最高位
        int a = 10;
        System.out.println(Integer.toBinaryString(a));
        int b = a>>1 ;
        System.out.println(b);
        System.out.println(Integer.toBinaryString(b));
    }

    public static void main(String[] args) {
        System.out.println(Math.PI);
    }

    public static  int[] mySort(int a[]){
        for(int i=1;i<a.length;i++){
            for(int j=0;j<a.length-i;j++){
                if(a[j] > a[j+1]){
                   a[j] = a[j]^a[j+1];
                   a[j+1] = a[j]^a[j+1];
                   a[j] = a[j]^a[j+1];
                }
            }
        }
        return a;
    }


}
