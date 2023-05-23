package AplPackage;

import Unidad2.*;

import javax.swing.*;
import java.awt.*;

public class AplUnidad2 extends JFrame{

    // clase donde pruebo los componentes :D
    JLeeEntero txtEntero;
    JLeeDecimal txtDecimal;


    public AplUnidad2 (){
        super("Pruebas :D");
        hazInterfaz();
    }

    public void hazInterfaz(){

        this.setSize(500, 250);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        txtEntero = new JLeeEntero();
        txtDecimal = new JLeeDecimal();

        setLayout(new GridLayout(0,2));
        add(new JLabel("Entero: "));
        add(txtEntero);
        add(new JLabel("Decimal: "));
        add(txtDecimal);

        setVisible(true);
    }

    public static void main(String[] args)  {
        new AplUnidad2();
    }
}
