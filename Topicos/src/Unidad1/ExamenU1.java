package Unidad1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class ExamenU1 extends JFrame implements ActionListener {


    JButton btnsBarcos[];
    JLabel lbCantidadPuertos[][];
    Barco barcos[];
    public ExamenU1 (){
        makeInterface();
        makeListeners();
    }

    public void makeInterface(){
        this.setSize(700, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel aux = new JLabel(" ");

        this.setLayout(new GridLayout(0, 9));

        add(aux);
        String stgPuertos[] = {"Ensenada", "La Paz", "3", "4", "5", "6", "7", "8"};
        JLabel puertos[] = new JLabel[8];
        for (int i = 0; i < puertos.length; i++) {
            puertos[i] = new JLabel(stgPuertos[i]);
            add(puertos[i]);
        }

        makeBarcos();

    //    barcos[1].viaje();

        setVisible(true);
    }

    public void addBarcos(){

    }

    public void makeBarcos(){
        int auxBarcos = new Random().nextInt(11 - 5) + 5;
        System.out.println(auxBarcos);
        btnsBarcos = new JButton[auxBarcos];
        barcos = new Barco[auxBarcos];
        lbCantidadPuertos = new JLabel[auxBarcos][8];
        for (int i = 0; i < barcos.length; i++) {
            barcos[i] = new Barco();
            btnsBarcos[i] = new JButton("Barco" + (i +1));
                btnsBarcos[i].addActionListener(this);  // agregre doble para checar si era por esto

            add(btnsBarcos[i]);
            System.out.println("Barco " + i+ "Capacidad " + barcos[i].getCapacidad());
        //    btnsBarcos[i] = new JButton("Barco " + i);

            for (int j = 0; j < lbCantidadPuertos[i].length; j++) {

                lbCantidadPuertos[i][j] = new JLabel("" + barcos[i].getCantidadPuertos(j));
                add(lbCantidadPuertos[i][j]);
                 System.out.println(barcos[i].getCantidadPuertos(j));

            }
        }
    }

    public void actualizar(){
        for (int i = 0; i < barcos.length; i++) {
            for (int j = 0; j < lbCantidadPuertos[i].length; j++) {

                lbCantidadPuertos[i][j].setText("" + barcos[i].getCantidadPuertos(j));
             //   System.out.println(barcos[i].getCantidadPuertos(j));

            }
        }
    }
    public void makeListeners(){
        for (int i = 0; i < btnsBarcos.length; i++) {
            btnsBarcos[i].addActionListener(this);
        }

    }

    public static void main(String[] args)  {
        new ExamenU1();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      //  if(e.getSource() == btnsBarcos[1])    barcos[1].viaje();

        for (int i = 0; i < btnsBarcos.length; i++) {


            if(e.getSource() == btnsBarcos[i]){
                System.out.println("pulsadp" + i);
                barcos[i].viaje();
                actualizar();
            }
        }


    }

}
