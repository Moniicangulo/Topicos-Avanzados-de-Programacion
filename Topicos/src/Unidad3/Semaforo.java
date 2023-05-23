package Unidad3;

public class Semaforo {
    private int recursos;
    public Semaforo(int recursos){
        this.recursos = recursos;
    }

    public synchronized void espera(){
        while (recursos < 1){
            try {
                wait();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        recursos--;
    }

    public synchronized void libera() {
        recursos++;
        notifyAll();
    }
}
