package Unidad3;

import java.util.Random;

class Consumidor extends Thread{

    int[] productos;
    Semaforo[] semaforos;


    public Consumidor(int[] productos, Semaforo[] semaforos, int numero){
        this.productos = productos;
        this.semaforos = semaforos;
        this.setName("Consumidor " + numero);
    }

    public void run(){
        while (true){
            int id = new Random().nextInt(productos.length);
            int cantidad = new Random().nextInt(4) + 1;
            semaforos[id].espera();

            if(productos[id] < 0 || productos[id] < cantidad){
                System.out.println(getName() + " No pudo realizar la comprar :(");
                semaforos[id].libera();
                continue;
            }
            productos[id] -= cantidad;
            System.out.println(getName() + " Realizo la compra del producto " + id);

            semaforos[id].libera();
            try {
                this.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}

class Productor extends Thread{
    int[] productos;
    Semaforo[] semaforos;
    int maximoProductos;

    public Productor(int[] productos, Semaforo[] semaforos){
        this.productos = productos;
        this.semaforos = semaforos;
        maximoProductos = (int) (productos.length + (productos.length * .5));
        this.setName("Productor");
    }

    public void run(){
        while (true){
            int actividad = new Random().nextInt(2) + 1;
            if(actividad == 1){
                System.out.println(getName() + " Actividad 1");
                int id = new Random().nextInt(productos.length);
                int cantidad = new Random().nextInt(3) + 5;
                semaforos[id].espera();
                productos[id] += cantidad;
                System.out.println(getName() + " Agrego mas productos a " + id);
                semaforos[id].libera();
                try {
                    this.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else {
                if(productos.length < maximoProductos){
                    System.out.println(getName() + " Actividad 2");
                    for (int i = 0; i < productos.length; i++) {
                        semaforos[i].espera();
                    }
                    int[] newProductos = new int[productos.length + 1];
                    Semaforo[] newSemaforos = new Semaforo[productos.length + 1];
                    int nuevo = productos.length;
                    for (int i = 0; i < productos.length; i++) {
                        newProductos[i] = productos[i];
                        newSemaforos[i] = semaforos[i];
                    }
                    newProductos[nuevo] = new Random().nextInt(11) + 10;
                    newSemaforos[nuevo] = new Semaforo(1);

                    productos = newProductos;
                    semaforos = newSemaforos;
                    for (int i = 0; i < productos.length; i++) {
                        semaforos[i].libera();
                    }
                    System.out.println(getName() + " Se agrego un nuevo producto");

                    try {
                        this.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

}

public class ProductorComsumidor {

    public static void main(String[] args) {
        int cantProductos = new Random().nextInt(51) + 50;
        int consumidores = new Random().nextInt(8) + 3;

        int[] productos = new int[cantProductos];
        Semaforo[] semaforos = new Semaforo[cantProductos];
        Consumidor[] consumidors = new Consumidor[consumidores];
        for (int i = 0; i < semaforos.length; i++){
            productos[i] = new Random().nextInt(11) + 20;
            semaforos[i] = new Semaforo(1);
        }

        for (int i = 0; i < consumidors.length; i++)
            consumidors[i] = new Consumidor(productos, semaforos, (i + 1));

        Productor productor = new Productor(productos, semaforos);

        for (int i = 0; i < consumidors.length; i++)
            consumidors[i].start();
        productor.start();
    }
}
