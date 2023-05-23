package Unidad3;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/*
        Topicos avanzados de programacion
        Proyecto: filosofos comensales
        Alumna: Monica Angulo Sanchez
        No. Control: 20170592
        Docente: Dr. Clemente Garcia Gerardo
        Fecha: 16.11.22
 */
class Filosofo extends Thread {
    static Semaforo[] semaforos;

    static boolean[] tenedores;
    private int num;
    private int izq, der;
    private int estado;
    private int numeroCenas;

    public Filosofo(int num, int izq, int der) {
        this.num = num;
        this.izq = izq;
        this.der = der;
        this.numeroCenas = 0;
        if (tenedores == null) {
            tenedores = new boolean[5];
            Arrays.fill(tenedores, false);
        }
        if (semaforos == null) {
            semaforos = new Semaforo[5];
            for (int i = 0; i < semaforos.length; i++) {
                semaforos[i] = new Semaforo(1);
            }
        }
    }

    public boolean[] getTenedores() {
        return tenedores;
    }

    //la variable estado nos indica en que estado se encuentra el filosofo  0- no tiene ningun tenedor.
    //1 - tiene tenedor izq. 2- tiene los 2 tenedores. 3- esta comiendo. 4 - esta filosofando.
    public int getEstado() {
        return this.estado;
    }

    public int getNumeroCenas() {
        return this.numeroCenas;
    }

    public void run() {
        while (true) {
            estado = 0;
            semaforos[izq].espera();
            if (!tenedores[izq]) {
                tenedores[izq] = true;
                estado = 1;
                dormir(1000);
            } else {
                dormir(1000);
                semaforos[izq].libera();
                continue;
            }
            semaforos[izq].libera();

            semaforos[der].espera();
            if (tenedores[izq] && !tenedores[der]) {
                tenedores[der] = true;
                estado = 2;
                dormir(1000);
            } else {
                tenedores[izq] = false;
        //        semaforos[izq].libera();
                estado = 0;

                dormir(1000);
                semaforos[der].libera();
                continue;
            }
            semaforos[der].libera();

            if (tenedores[izq] && tenedores[der]) {
                estado = 3;
                dormir(4000);

                numeroCenas++;
                estado = 4;
                dormir(6000);

                tenedores[izq] = tenedores[der] = false;
                estado = 0;
                dormir(1000);
            }
        }
    }

    public void dormir(int tiempo) {
        try {
            this.sleep(tiempo);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class FilosofosComensales extends JFrame {

    Filosofo[] filosofos;
    boolean[] tenedores;
    JPanel[] panelsFilosofos, panelsTenedores, panelCenas;
    JLabel[] numCenas;

    public FilosofosComensales() {
        String[] nombres = new String[] {"Pitagoras", " Aristoteles", "Socrates", "Platon", "Tales de Mileto"};

        filosofos = new Filosofo[5];
        for (int i = 0; i < filosofos.length; i++) {
            int j = i == 0 ? 4 : i - 1;
            filosofos[i] = new Filosofo(i + 1, i, j);
            filosofos[i].setName(nombres[i]);
        }

        for (int i = 0; i < filosofos.length; i++)
            filosofos[i].start();
        tenedores = filosofos[0].getTenedores();
        hazInterfaz();
        cena();
    }

    public void hazInterfaz() {
        setSize(1135, 830);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        //fondo
        ImageIcon imagen = new ImageIcon("FILOSOFOS COMENSALES.png");
        JLabel fondo = new JLabel(imagen);
        fondo.setBounds(0, 0, 1135, 811);
        add(fondo);
        //Panel's de los los filosofos
        panelsFilosofos = new JPanel[5];
        for (int i = 0; i < filosofos.length; i++) {
            panelsFilosofos[i] = new JPanel();
            panelsFilosofos[i].add(new JLabel(filosofos[i].getName()));
            add(panelsFilosofos[i]);
        }
        panelsFilosofos[0].setBounds(68, 220, 80, 40);
        panelsFilosofos[1].setBounds(500, 130, 80, 40);
        panelsFilosofos[2].setBounds(685, 430, 80, 40);
        panelsFilosofos[3].setBounds(585, 750, 80, 40);
        panelsFilosofos[4].setBounds(35, 640, 100, 40);
        //panel's de los tenedores
        panelsTenedores = new JPanel[5];
        for (int i = 0; i < panelsTenedores.length; i++) {
            panelsTenedores[i] = new JPanel();
            panelsTenedores[i].add(new JLabel("" + (i + 1)));
            add(panelsTenedores[i]);
        }
        panelsTenedores[0].setBounds(300, 250, 30, 40);
        panelsTenedores[1].setBounds(560, 290, 30, 40);
        panelsTenedores[2].setBounds(500, 490, 30, 40);
        panelsTenedores[3].setBounds(290, 570, 30, 40);
        panelsTenedores[4].setBounds(150, 360, 30, 40);
        //panel's del numero de cenas de cada filosofo
        panelCenas = new JPanel[5];
        numCenas = new JLabel[5];
        for (int i = 0; i < numCenas.length; i++) {
            panelCenas[i] = new JPanel();
            numCenas[i] = new JLabel(filosofos[i].getNumeroCenas() + "");
            numCenas[i].setFont(new Font("Arial", Font.PLAIN, 20));
            panelCenas[i].add(numCenas[i]);
            panelCenas[i].setBackground(new Color(195, 195, 195));
            add(panelCenas[i]);
        }
        panelCenas[0].setBounds(970, 290, 50, 35);
        panelCenas[1].setBounds(970, 400, 50, 35);
        panelCenas[2].setBounds(970, 520, 50, 35);
        panelCenas[3].setBounds(970, 630, 50, 35);
        panelCenas[4].setBounds(970, 740, 50, 35);

        setVisible(true);
    }

    //este metodo actualiza el estado de los tenedores, filosofos y el numero de cenas
    public void cena() {
        while (true) {
            for (int i = 0; i < numCenas.length; i++) {
                numCenas[i].setText("" + filosofos[i].getNumeroCenas());
                panelCenas[i].updateUI();

                if (tenedores[i])
                    panelsTenedores[i].setBackground(new Color(12, 101, 234));
                else
                    panelsTenedores[i].setBackground(new Color(232, 126, 176));
                panelsTenedores[i].updateUI();

                switch (filosofos[i].getEstado()) {
                    case 0 -> panelsFilosofos[i].setBackground(new Color(241, 11, 11));
                    case 1 -> panelsFilosofos[i].setBackground(new Color(238, 102, 15, 224));
                    case 2 -> panelsFilosofos[i].setBackground(new Color(229, 210, 23));
                    case 3 -> panelsFilosofos[i].setBackground(new Color(82, 190, 51));
                    case 4 -> panelsFilosofos[i].setBackground(new Color(171, 12, 234));
                }
                panelsFilosofos[i].updateUI();
            }
        }
    }

    public static void main(String[] args) {
        new FilosofosComensales();
    }

}