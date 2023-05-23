package Unidad3;

/*
    Alumna: Monica Angulo Sanchez
    NoControl: 20170592
    Docente: Dr. Clemente Garcia Gerardo
    Examen Unidad 3
 */

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Semaphore;

//clase de los clientes
class Cliente extends Thread {
    //  int cantidad;
    int nProducto;
    int cantidad;
    boolean[] banderas;
    int compra;
    Semaphore[] semaforos;
    int[] productos;
    boolean activo;

    public Cliente() {
        referenciar();
        activo = true;
    }

    public void run() {
        while(true) {
            referenciar();
            while (activo){
                nProducto = new Random().nextInt(cantidad);
                System.out.println(getName() + " " + nProducto + "pruducto a comprar");
                compra = new Random().nextInt(4) + 1;
                System.out.println(getName() +  " "+ productos[nProducto]+ "|" + compra + " cantidad a comprar");

                if (semaforos[nProducto].tryAcquire()) {
                    if(!banderas[nProducto]){
                        banderas[nProducto] = true;
                        productos[nProducto] -= compra;
                        System.out.println(getName() + " compra realizada " +productos[nProducto]);
                        dormir(1000);
                    }
                    banderas[nProducto] = false;
                }
                semaforos[nProducto].release();
            }

        }
    }
    public void referenciar(){
        semaforos = VendeMucho.semaforos;
        productos = VendeMucho.productos;
        banderas = VendeMucho.banderas;
        cantidad = VendeMucho.cantidad;

    }
    public void setActivo(boolean estado){
        this.activo = estado;
    }

    public void dormir(int tiempo) {
        try {
            this.sleep(tiempo);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}

//clase de los proveedores
class Proveedor extends Thread {
    int cantidad;
    int maxProductos;
    int producto, agregaCantidad;
    Semaphore[] semaforos;
    boolean[] banderas;
    int[] productos;
    Cliente[] clientes;

    public Proveedor(Cliente[] clientes) {
        referenciar();
        this.maxProductos = VendeMucho.tamanio;
        this.clientes = clientes;
    }

    public void run() {
        int accion;
        while(true) {
            accion = new Random().nextInt(2) + 1;
            System.out.println("Accion a realizar" + accion);
            if (accion == 1) {
                producto = new Random().nextInt(cantidad);
                if (semaforos[producto].tryAcquire()) {
                    if (!banderas[producto]) {
                        banderas[producto] = true;
                        agregaCantidad = new Random().nextInt(6 - 3) + 5;
                        productos[producto] += agregaCantidad;
                        System.out.println(getName() + " se agrego a " + producto + " la cant " + productos[producto]);
                        dormir(2000);
                    }
                    banderas[producto] = false;
                }
                semaforos[producto].release();
            }else {
                if(cantidad < maxProductos){
                    for (int j = 0; j < clientes.length; j++) {
                        clientes[j].setActivo(false);
                    }
                    for (int i = 0; i < banderas.length; i++) {
                        while (banderas[i]);

                    }
                    VendeMucho.cantidad++;
                    VendeMucho.iniciarArrays();
                    for (int j = 0; j < VendeMucho.productos.length; j++) {
                        if(j == (VendeMucho.cantidad - 1)){
                            VendeMucho.productos[j] = new Random().nextInt(21 - 10) + 10;
                        }else {
                            VendeMucho.productos[j] = productos[j];
                            System.out.print(VendeMucho.productos[j] + "|");
                        }
                    }
                    referenciar();
                    for (int j = 0; j < clientes.length; j++) {
                        clientes[j].setActivo(true);
                    }
                }
            }

        }

    }

    public void referenciar(){
        semaforos = VendeMucho.semaforos;
        productos = VendeMucho.productos;
        banderas = VendeMucho.banderas;
        cantidad = VendeMucho.cantidad;
    }


    public void dormir(int tiempo) {
        try {
            this.sleep(tiempo);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


public class VendeMucho {
    static int cantidad;
    static int tamanio;
    static int[] productos;
    static boolean[] banderas;
    static Semaphore[] semaforos;

    static public void main(String[] args) {
        cantidad = new Random().nextInt(101 - 50) + 50;
        tamanio = (int) (cantidad + (cantidad * .5));
        iniciarArrays();

        System.out.println(cantidad);
        System.out.println(tamanio);

        for (int i = 0; i < productos.length; i++) {
            productos[i] = new Random().nextInt(31 - 20) + 20;
            System.out.print(productos[i] + "|");
        }


        int nCli = new Random().nextInt(11 - 3) + 3;

        Cliente[] clientes = new Cliente[nCli];
        for (int i = 0; i < clientes.length; i++) {
            clientes[i] = new Cliente();
            clientes[i].setName("Cliente" + (i + 1));
        }
        Proveedor prov = new Proveedor(clientes);
        prov.setName("Proveedor");
        prov.start();

        for (int i = 0; i < clientes.length; i++) {
            clientes[i].start();
        }

    }

    static public void iniciarArrays(){
        productos = new int[cantidad];
        semaforos = new Semaphore[cantidad];
        banderas = new boolean[cantidad];
        Arrays.fill(banderas, false);
        //  Arrays.fill(semaforos,  new Semaforo(1));
        System.out.println("cantidad de productos " + cantidad);
        for (int i = 0; i < semaforos.length; i++) {
            semaforos[i] = new Semaphore(1);
        }
    }

}
