package Unidad3;

import Unidad1.Rutinas;

public class LiebreYTortuga {
    static class Liebre extends Thread{
        private int kilometros;
        public Liebre(){
            kilometros = 0;
        }
        public void run(){
            int num =0;
            while (kilometros < 100){
                kilometros+= Rutinas.nextInt(1,3);
                System.out.println(this.getName() + "km -> " +kilometros );
                num++;
                try {
                    sleep(100);
                } catch (Exception e) {

                }
            }
            System.out.println("Ha terminado la carrera");

        }
    }
    static class Tortuga extends Thread{
        private int kilometros;
        public Tortuga(){
            kilometros = 0;
        }
        public void run(){
            while (kilometros < 100){
                kilometros++;
                System.out.println(this.getName() + "km -> " +kilometros );
            }
            System.out.println("Ha terminado la carrera");
        }
    }

    public static void main(String[] args) {
        Liebre liebre = new Liebre();
        Tortuga tortuga = new Tortuga();

        liebre.start();
        tortuga.start();
    }
}
