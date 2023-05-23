package MVC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControladorHanoi implements ActionListener{
    VistaHanoi vista;
    ModeloHanoi modelo;
    Timer t;
    ArrayList<Movimiento> movimientos;
    int noMov;
    public ControladorHanoi(VistaHanoi vista,ModeloHanoi modelo) {
        this.vista = vista;
        this.modelo = modelo;
        movimientos = new ArrayList<>();
        noMov = 0;

        t = new Timer(10,this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnIniciar) {
            int discos = modelo.noDiscos;
            modelo.Hanoi('A','B','C', discos);
            vista.generarDiscos();
            movimientos = modelo.getMovtos();
            t.start();
            vista.btnIniciar.setEnabled(false);
            return;
        }

        if (e.getSource() == t){
            if(noMov == movimientos.size()){
                t.stop();
                return;
            }
            if ( vista.mover( movimientos.get( noMov ) ) ) noMov++;
        }

    }

}

