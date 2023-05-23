package Unidad3;

public class Ping extends Thread{
    Semaforo sPing, sPong;

    public Ping(Semaforo sPing, Semaforo sPong){
        this.sPing = sPing;
        this.sPong = sPong;
    }
    public void run(){
        for(int i = 0; i < 100;i++){
            sPing.espera();
            System.out.println("P I N G");
            sPong.libera();
        }
    }
}
