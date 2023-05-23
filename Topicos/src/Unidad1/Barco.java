package Unidad1;

import java.util.Random;

public class Barco {

    public static void main(String[] args)  {
        Barco b = new Barco();
        b.viaje();
    }
    private int capacidad;
    private int almacen;
    private int numeroBarcos;
    private int barcos[];
    private int gasolina;
    private int cantidadPuertos[];
    private int red;

    public Barco(){
     //   this.numeroBarcos =  new Random().nextInt(11 - 5) + 5;
        this.gasolina = 0;
        this.red = 0;
        this.capacidad = new Random().nextInt(91 - 60) + 60;
        cantidadPuertos = new int[8];
        for (int i = 0; i < cantidadPuertos.length; i++) {
            cantidadPuertos[i] = 0;
        }

    }

    //public int capacidad70(){

   // }

    public  void viaje(){
        int tiro = 0;
        int visitas = new Random().nextInt(8 ) + 1;
        System.out.println("Numero dem visita " + visitas );
        gasolina = new Random().nextInt(101 - 95) + 95;

        System.out.println(capacidad + " jjj " +  capacidad*70/100);
        for (int i = 0; i < visitas; i++) {
            System.out.println("Visita n "+ i);
            do {
                tiro++;
                red = new Random().nextInt(31 - 20) + 20;
                System.out.println("Cantidad qu pesco " + red);
                almacen += red;
                System.out.println("canridad en almacen "+ almacen);

                if(( capacidad*70/100)  < almacen){
                    cantidadPuertos[i + 1] += almacen;
                    almacen = 0;
                    tiro = 2;
                }

            }while (tiro != 2);

            if(i == visitas -1) {
                System.out.println("Segunda Vuelta  ");
                gasolina = new Random().nextInt(101 - 95) + 95;

                tiro = 0;
                cantidadPuertos[i + 1] += almacen;
                almacen = 0;
                for (int j = visitas - 1; j > 0; j--) {
                    do {
                        tiro++;
                        red = new Random().nextInt(31 - 20) + 20;
                        System.out.println("Cantidad qu pesco " + red);
                        almacen += red;
                        System.out.println("canridad en almacen " + almacen);

                        if ((capacidad * 70 / 100) < almacen) {
                            cantidadPuertos[j - 1] += almacen;
                            almacen = 0;
                            tiro = 2;
                        }

                    } while (tiro != 2);
                }
            }

        }

        System.out.println("AAAAAA");
        for (int i = 0; i < cantidadPuertos.length; i++) {
            System.out.println("puerto" + i + " Canti " + cantidadPuertos[i]);
        }
    }

    public int getCapacidad(){
        return capacidad;
    }

    public int getCantidadPuertos(int i){
        return cantidadPuertos[i];
    }





}
