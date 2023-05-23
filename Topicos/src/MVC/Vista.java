package MVC;

import Unidad2.*;

import javax.swing.*;
import java.awt.*;

public class Vista extends JFrame{
    JButton btnDorales, btnPesos; JLeeEntero cantidad; JLabel resultado;
    public Vista(){
        super("*** CONVERSIONDE PESOS A DOALRES ***");
        makeInterface();
    }
    public void makeInterface(){
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        this.setLayout(new GridLayout(0,2,5,5));
        btnDorales = new JButton("Dolares");
        btnPesos = new JButton("Pesos");
        cantidadS = new JLeeEntero();
        resultado = new JLabel("");

        add(new JLabel("Cantidad: ", JLabel.RIGHT));
        add(cantidad);
        add(btnDorales);
        add(btnPesos);
        add(new JLabel("Entregar: ", JLabel.RIGHT));
        add(resultado);

        this.setVisible(true);
    }
    public long getCantidad(){
        return cantidad.getCantidad();
    }
    public void setResultado(float importe){
        resultado.setText(importe + "");
    }
    public void setControlador(Controlador c){
        btnDorales.addActionListener(c);
        btnPesos.addActionListener(c);
    }
}
