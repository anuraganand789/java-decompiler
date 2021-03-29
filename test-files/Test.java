package test;

public class Test implements Runnable{
    private final int testNumber = 100;
    public static void main(final String ...args){
        final String name = "Anurag";
        final float floatValue = 12;
        final int nameNot = 9;
        System.out.println(name);
        System.out.println(floatValue);
        System.out.println(nameNot);

        interface ITest{
            void test();
        }
    }

    private int intReturningMethod(){
        System.out.println("making a invokd function call");
        return -1;
    }

    record Pair(int x, int y){}
    
    @Override
    public void run() { 
        System.out.println("just a test");
     }
}
