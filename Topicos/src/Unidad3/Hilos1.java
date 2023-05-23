package Unidad3;

public class Hilos1 {
    static public final int VECES = 20;

    public static void main(String[] args) {
        Caballo1 c1 = new Caballo1();
        Caballo2 c2 = new Caballo2();
        Caballo3 c3 = new Caballo3();
        System.out.println("CARRERA DE CABALLOS");
        c1.start();
        c2.start();
        c3.start();
        System.out.println("casa");
    }
    static class Caballo1 extends Thread{
        public void run(){
            for (int i = 0; i < VECES; i++) {

                System.out.println("Caballo 1 ");
            }
        }

    }

    static class Caballo2 extends Thread{
        public void run(){
            for (int i = 0; i < VECES; i++) {

                System.out.println("Caballo 2 ");
            }
        }

    }
    static class Caballo3 extends Thread{
        public void run(){
            for (int i = 0; i < VECES; i++) {

                System.out.println("Caballo 3 ");
            }
        }

    }
}
