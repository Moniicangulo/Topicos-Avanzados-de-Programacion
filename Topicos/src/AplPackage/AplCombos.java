package AplPackage;

import Unidad2.CombosDependientes;
import Unidad2.Lugar;
import Unidad2.SeleccionMarcas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AplCombos extends JFrame implements ActionListener {

    CombosDependientes combo1, combo2, combo3, combo4, combo5;
    Lugar lugar;
    JButton btn;
    public AplCombos(){
        super("Combos");
        makeInterface();
    }

    public void makeInterface(){
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(6,0));
       // this.setResizable(false);
        btn = new JButton("Obtener lugar");
        btn.addActionListener(this);

        combo1 = new CombosDependientes();
        combo2 = new CombosDependientes("Jalisco");
        combo3 = new CombosDependientes("Durango", "Topia");
        combo4 = new CombosDependientes("Sinaloa");
        combo5 = new CombosDependientes("Sonora", "Hermosillo");
        add(combo1);
        add(combo2);
        add(combo3);
        add(combo4);
        add(combo5);
        add(btn);


        setVisible(true);
    }


    public static void main(String[] args) {
        new AplCombos();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btn){
            lugar = combo1.getLugar();
            System.out.println("E:" + lugar.getPkEstado() + " M:" + lugar.getPkMunicipio() + " C:" + lugar.getPkCiudad());
        }
    }
}
