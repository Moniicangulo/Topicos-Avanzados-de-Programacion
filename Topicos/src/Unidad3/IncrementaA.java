package Unidad3;

public class IncrementaA extends Thread{
   static int a;
   public IncrementaA(){
       a = 0;
   }

   public void run(){
       suma();
   }
   private static synchronized void suma(){
       a++;
   }
   public String toString(){
       return a + "";
   }
}
