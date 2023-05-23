package Unidad3;

public class Pong extends Thread{
    Semaforo sPing, sPong;

    public Pong(Semaforo sPing, Semaforo sPong){
        this.sPing = sPing;
        this.sPong = sPong;
    }
    public void run(){
        for(int i = 0; i < 100;i++){
            sPong.espera();
            System.out.println("POGN");
            sPing.libera();
        }
    }

}
