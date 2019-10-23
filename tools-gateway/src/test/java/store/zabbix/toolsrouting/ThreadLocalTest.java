package store.zabbix.toolsrouting;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalTest {

    private static String aString;
    private static ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();

    public static String setA(String a) {
        // stringThreadLocal.set(a);
        // System.out.println("set"+stringThreadLocal.get());
        aString = a ;
        // System.out.println("set"+aString);
        return aString;
    }

    public static boolean getA(String b) {
        // System.out.print(stringThreadLocal.get());
        // System.out.println("get"+aString);
        // System.out.println();
        return aString.equals(b);
        // System.out.println("get"+stringThreadLocal.get());

        // return stringThreadLocal.get();
    }

    public static void main(String[] args) {
        // ThreadLocalTest threadLocalTest = new ThreadLocalTest();
        // Thread t1 =new Thread(threadLocalTest);
        // Thread t2 =new Thread(threadLocalTest);
        // t1.start();
        // t2.start();
        //
        // Runnable task2 = () -> { setA("Runnable");
        //     getA(); };
        //
        // new Thread(task2).start();
        // // Java 8之前：
        // new Thread(() -> {
        //     setA("Thread");
        //     getA();
        // }).start();
        // new Thread(() -> {
        //     setA("Thread1");
        //     getA();
        // }).start();
        // new Thread(() -> {
        //     setA("Thread2");
        //     getA();
        // }).start();
        // new Thread(() -> {
        //     setA("Thread3");
        //     getA();
        // }).start();
        // new Thread(() -> {
        //     setA("Thread4");
        //     getA();
        // }).start();

        int thread = 100;
        while (true){
            if(thread >0){
                thread--;
                int finalThread = thread;
                final String[] a = new String[1];
                new Thread(() -> {
                     a[0] = setA("Thread"+ finalThread);
                }).start();
                new Thread(() -> {
                    System.out.println(getA(a[0]));
                }).start();

            }else {
                break;
            }

        }
    }


    // @Override
    // public void run() {
    //     while (true){
    //         if(thread > 0 ){
    //             // System.out.println("bbb"+thread--);
    //             // stringThreadLocal.set("bbb"+thread--);
    //             // System.out.println(stringThreadLocal.get());
    //             setA("-------"+thread--);
    //             getA();
    //         }else{
    //             break;
    //         }
    //     }
    // }

    // Runnable task1 = () - > {
    //     setA("AAAAA");
    // };
    // Runnable task2 = () -> { System.out.println("Task #2 is running"); };



}
