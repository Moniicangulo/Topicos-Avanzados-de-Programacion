package AplPackage;

import Unidad2.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AplComponentes extends JFrame implements ActionListener{

    JButton btn;

   private ComponenteV3 c1, c2, c3;

    public AplComponentes(){
        hazInterfaz();
        hazEscuchas();
    }
    public void hazEscuchas(){
        btn.addActionListener(this);
    }

    public void hazInterfaz(){
        this.setSize(550, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  //     this.setResizable(false);
        this.setLayout(new GridLayout(0,1, 5,5));

        c1 = new ComponenteV3();
        c2 = new ComponenteV3();
        c3 = new ComponenteV3();
        add(c1);
        add(c2);
        add(c3);

        btn = new JButton("Botoncito");
        add(btn);

        setVisible(true);
    }

    public static void main(String[] args)  {
        new AplComponentes();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btn){
            System.out.println(c1.getTxtNoControl());
            System.out.println(c1.getTxtNombre());
            System.out.println(c1.getTxtCarrera());

        }
    }
}
