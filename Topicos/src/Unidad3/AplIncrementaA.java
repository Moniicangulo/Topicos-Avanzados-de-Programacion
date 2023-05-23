package Unidad3;

public class AplIncrementaA {
    public static void main(String[] args) {

        final int tam = 100;
        IncrementaA[] a = new IncrementaA[tam];

        for (int i = 0; i < a.length; i++) {
            a[i] = new IncrementaA();
        }
        for (int i = 0; i < a.length; i++) {
            a[i].start();
        }
        while (noMoridos(a));

        System.out.println("A = " + a[0]);

        /* incremnentando la A con 3 hilos
        IncrementaA obj1 = new IncrementaA();
        obj1.start();
        IncrementaA obj2 = new IncrementaA();
        obj2.start();
        IncrementaA obj3 = new IncrementaA();

        obj3.start();

        while (obj1.isAlive() || obj2.isAlive() || obj3.isAlive());

        System.out.println("A = " + obj1);
        */
    }
    public static boolean noMoridos(IncrementaA[] a){
        for (int i = 0; i < a.length; i++) {
            if(a[i].isAlive()) return true;
        }
        return false;

    }
}
