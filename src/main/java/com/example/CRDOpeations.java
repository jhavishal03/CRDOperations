package com.example;

class MyThread1 extends Thread{
    TTLDataStore t=new TTLDataStore(2L);
    MyThread1(TTLDataStore t){
        this.t=t;
    }
    public void run(){
        try {
            t.put("xyz","ABCD");
            t.put("21x","Vishal Jha");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(t.get("xyz"));
        t.remove("21x");
    }

}
class MyThread2 extends Thread{
    TTLDataStore t=new TTLDataStore(100000L);
    MyThread2(TTLDataStore t){
        this.t=t;
    }
    public void run(){
        try {
            t.put("abc","ABCDE");
            t.put("32x","Rahul");

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(t.get("abc"));
        t.remove("32x");
        t.getAll();
    }


}

public class CRDOpeations {

    public static void main(String[] args) throws Exception {
	// write your code here
        TTLDataStore obj=new TTLDataStore(6000000L);
        obj.put("xyz","vishal");
        obj.put("12d","ram kumar");
        System.out.println(obj.get("12d"));
        obj.getAll();
        MyThread1 t1=new MyThread1(obj);
        t1.start();
        MyThread2 t2=new MyThread2(obj);
        t2.start();
    }
}
