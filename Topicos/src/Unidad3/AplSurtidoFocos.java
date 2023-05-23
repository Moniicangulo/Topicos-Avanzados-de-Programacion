package Unidad3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

class Bodegero extends Thread{

    Bodega bodega;
    Caja[] cajas;
    Caja cajaActual;
    int idActual;


    public Bodegero(Bodega bodeg, int idActual){
        this.bodega = bodeg;
        cajas = bodega.getCajas();
      //  this.cajaActual = cajaActual;
        this.idActual = idActual;
        this.setName("Bodeguero");
    }

    public void run(){
        System.out.println(cajas.length);
        for (int i = 0; i < cajas.length; i++) {
            System.out.println(getName() + " llevo la caja:" + cajas[i].getIdCaja());

            cajaActual = cajas[i];
          while (cajaActual.getExistencia() > 0){
              System.out.println(cajaActual.getIdCaja() + " | "+ cajaActual.getExistencia());
          }
        }
    }
    public void mesaTrabajo(){

    }



}
class Surtidor extends Thread{
    Bodega bodega;
    ArrayList<Orden> ordenes;
    private int invetario;
    private int idSurtidor;
    static private int noOrden;
    Caja cajaActual;
    Caja[] cajas;
    Semaforo semOrden;
    Semaforo semaFoco;
    int idActual;
    int actOrden;
    static boolean bandera;

    public Surtidor(Bodega bodega, int idSurtidor, int idActual) {
        this.bodega = bodega;
        ordenes = new ArrayList<>();
        this.idSurtidor = idSurtidor;
        noOrden = 1;
        semOrden = new Semaforo(1);
        semaFoco = new Semaforo(1);
        this.idActual = idActual;
        cajas = bodega.getCajas();
        actOrden = 0;
        bandera = false;
        //this.cajaActual = cajaActual;
        this.setName("Surtidor " + idSurtidor);
       // invetario = get
    }
    public void run(){
        while (bodega.getInventario() > 0){
            semOrden.espera();
            int noFocos = new Random().nextInt(98) + 3;
            ordenes.add( new Orden(noOrden, idSurtidor, noFocos));
            noOrden++;
            semOrden.libera();
            cajaActual = cajas[idActual];
            for (int i = 0; i < noFocos; i++) {
                semaFoco.espera();
                if(!bandera){
                    bandera = true;
                    if(cajaActual.getExistencia() > 0){
                        if(cajaActual.validarFoco()){
                            ordenes.get(actOrden).addFocos(cajaActual.getIdFoco());
                            System.out.println(getName() + " agrego el foco: " + cajaActual.getIdFoco());
                        }

                    }
                    else {
                        System.out.println("No more focos");
                    }
                }
                bandera = false;
                semaFoco.libera();

            }
            actOrden++;
        }

    }
}
class Orden {

    private int idOrden;
    private int noFocos;
    private int iterador;
    private String[] focos;
    private int idSurtidor;
    public Orden(int idOrden, int idSurtidor, int noFocos){
        this.idOrden = idOrden;
        this.idSurtidor = idSurtidor;
        this.noFocos = noFocos;
        iterador = 0;
        focos = new String[noFocos];
    }

    public void addFocos(String idFoco){

        focos[iterador] = idFoco;
        iterador++;
    }

}


class Caja{
    private int idCaja;
    Semaforo[] semaforosCaja;

    public int getIdCaja() {
        return idCaja;
    }

    Random R=new Random();
    private String [] focos;
    private ArrayList<String> focoss;
    private String idFoco;
    private int existencia;
    private int focosSacados;
    boolean[] banderas;
    public Caja(int idCaja) {
        this.idCaja=idCaja;
        this.focos=new String[200];
        focoss = new ArrayList<>();
        existencia = 200;
        focosSacados = 0;
        semaforosCaja = new Semaforo[200];
        banderas = new boolean[200];
        Arrays.fill(banderas, false);
        for(int i=0 ; i <semaforosCaja.length ; i++) {
            semaforosCaja[i] = new Semaforo(1);
        }
        for(int i=0 ; i < 200 ; i++) {
            idFoco= PonCeros(idCaja)+"-"+R.nextInt(1000)+1;
            focoss.add(idFoco);
        }
        Collections.sort(focoss);
        for(int i=0 ; i < focos.length ; i++) {
            idFoco= PonCeros(idCaja)+"-"+R.nextInt(1000)+1;
            focos[i]=idFoco;
        }
    }

    public String getIdFoco() {
        existencia--;
        return focoss.get(focosSacados - 1);
    }
    public boolean validarFoco(){
        if(focosSacados == 200){
            return false;
        }
        if(banderas[focosSacados])
            return false;

        else{
            focosSacados++;
            return true;
        }
    }

    public int getExistencia(){
        return existencia;
    }

    public String getFoco(int min){
        existencia--;
        return focoss.remove(min);
    }



    private String PonCeros(int idCaja) {
        String cad=idCaja+"";
        for(int i=0 ; cad.length()<4;i++)
            cad="0"+cad;
        return cad;
    }
}
class Bodega {
    private Caja [] cajas;
    private int inventario;
    static int cajaActual;
    public Bodega() {
        cajaActual = 0;
        cajas = new Caja[10000];
        for(int i=0 ; i<cajas.length ; i++) {
            cajas[i] = new Caja(i + 1);
        }

        inventario = 10000 * 200;
    }

    public Caja getCaja(int id) {
        return cajas[id];
    }

    public int getInventario(){
        return inventario;
    }
    public void setInventario(){
        inventario--;
    }


    public Caja[] getCajas() {
        return cajas;
    }
}
public class AplSurtidoFocos {
    static int caja;
    public static void main(String[] args) {
        Bodega bodega = new Bodega();
      //  Caja caja = bodega.getCaja(0);
        caja = 0;
        Bodegero bodegero= new Bodegero(bodega, caja);
        Surtidor[] surtidors = new Surtidor[3];
        //     public Surtidor(Bodega bodega, int idSurtidor, Caja cajaActual) {
        for (int i = 0; i < surtidors.length; i++) {
            surtidors[i] = new Surtidor(bodega, (i+1), caja);
        }
        bodegero.start();
        for (int i = 0; i < surtidors.length; i++) {
            surtidors[i].start();
        }


    }
}
