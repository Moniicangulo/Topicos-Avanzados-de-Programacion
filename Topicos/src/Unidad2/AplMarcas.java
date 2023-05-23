package Unidad2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AplMarcas extends JFrame implements ActionListener {
    SeleccionMarcas marcas;
    ExamenU2 e;
    String[] anima;
    JButton btnObtener;
    ArrayList<Marca> seleccion;

    public AplMarcas(){
        super("Marcas");
        seleccion = new ArrayList<>();
        anima = new String[]{"Leon", "Sapo", "Perro"};
        hazInterfaz();
        hazEscuchas();
    }

    public void hazInterfaz(){
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
      //  e = new ExamenU2("Animal", anima );
       // add(e);

     //   this.setLayout();
     //    this.setResizable(false);

     //   btnObtener = new JButton("Obtener informacion");
        marcas = new SeleccionMarcas();
        add(marcas);
      //  add(btnObtener, BorderLayout.SOUTH);


        setVisible(true);
    }
    public void hazEscuchas(){
      btnObtener.addActionListener(this);
    }


    public static void main(String[] args) {
        new AplMarcas();


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnObtener){
            seleccion = marcas.getSeleccion();
            for (Marca select : seleccion ) {
                System.out.println("id:" + select.getPkMarca() + " N:" + select.getNombre());
            }
        }
    }
}
