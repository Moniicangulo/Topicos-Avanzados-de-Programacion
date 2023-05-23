package MVC;

import java.awt.event.*;

public class Controlador implements ActionListener {

    private Vista vista;
    private Modelo modelo;

    public Controlador(Vista vista, Modelo modelo){
        this.vista = vista;
        this.modelo = modelo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnDorales){
            long amount = vista.getCantidad();
            float dolares = modelo.dolares(amount);
            vista.setResultado(dolares);
            return;
        }
        if (e.getSource() == vista.btnPesos){
            vista.setResultado(modelo.pesos(vista.getCantidad()));
        }
    }
}
