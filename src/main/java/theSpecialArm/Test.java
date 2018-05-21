package theSpecialArm;


public class Test {
    public static void main(String[] args) {
        String a ="你好";
        System.out.println(String.format(a, "ISO-8859-1"));
    }
    public static void test1(){
        Integer A = 1;
        Integer B = 1;
        Integer C = 200;
        Integer D = 200;

        System.out.println(A == B);
        System.out.println(C == D);

        int a = 0123;
        System.out.println(Integer.parseInt("0123" , 8));
    }
}
