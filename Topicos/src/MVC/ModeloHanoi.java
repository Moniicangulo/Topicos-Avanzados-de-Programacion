package MVC;

import java.util.ArrayList;

public class ModeloHanoi {
    int noDiscos;
    private ArrayList<Movimiento> movtos;
    public ModeloHanoi(int noDiscos) {
        this.noDiscos = noDiscos;
        movtos = new ArrayList();

    }
    public void Hanoi(char Inicial, char Central, char Final, int noDiscos) {
        if( noDiscos == 1 ) {
            movtos.add( new Movimiento( Inicial, Final, noDiscos ) );
            return;
        }
        Hanoi( Inicial, Final, Central, noDiscos - 1 );
        movtos.add( new Movimiento( Inicial, Final, noDiscos ) );
        Hanoi( Central, Inicial, Final, noDiscos - 1 );
    }
    public ArrayList<Movimiento> getMovtos() {
        return movtos;
    }



}