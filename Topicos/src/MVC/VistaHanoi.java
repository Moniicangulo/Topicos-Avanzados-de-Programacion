package MVC;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VistaHanoi extends JFrame {
    Graphics g;
    Image frame;
    JButton btnIniciar;
    int noDiscos;
    int inicio;
    Disco[] discos;
    final int ALTURA_MAX = 120;
    int mvoHorizontal;
    int[] torres;
    final int INC = 10;
    boolean mvoArriba;
    ArrayList<Movimiento> movimientos;
    int[] aumento;

    Color [] colores;
    public VistaHanoi() {
        this(3);
    }

    public VistaHanoi(int noDiscos) {
        super("Torre de Hanoi");
        this.noDiscos = noDiscos;
        generarAuxiliares();
        HazInterfaz();
        frame = createImage(getWidth(), getHeight());
        g = frame.getGraphics();

    }

    private void HazInterfaz() {
        setSize(1190, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        btnIniciar = new JButton("Iniciar");
        add(btnIniciar, BorderLayout.NORTH);
        setVisible(true);
    }
    public void paint( Graphics gl) 	{
        paint2();
        gl.drawImage(frame, 0, 0, getWidth(), getHeight(), this);
    }

    public void paint2() {
        if(g == null) return;
        super.paint(g);
        Color cafe = new Color(138, 69, 29);
        g.setColor(new Color(181, 156, 205));
        g.fillRect(0, 55, 1200, 800);

        // genera las torres
        g.setColor(cafe);
        for (int i = 0; i < 1000; i += 380) {
            g.fillRect(30 + i, 720, 350, 45);
            g.fillRect(200 + i, 300, 10, 430);
        }

        // generar los discos
        for (int i = 0; i < noDiscos; i++) {
            g.setColor(colores[i]);
            g.fillRoundRect(discos[i].getPx(), discos[i].getPy(), discos[i].getWidth(), discos[i].getHeigth(), 20, 20);
        }

    }
    public void generarAuxiliares(){
        //valores iniciales para algunas variables
        inicio = 0;
        movimientos = new ArrayList<>();
        discos = new Disco[noDiscos];
        torres = new int[3];
        mvoHorizontal = 0;
        //genera el array para recorrer la posicion
        aumento = new int[noDiscos];
        int aumen = noDiscos*10;
        for (int i = 0; i < aumento.length; i++) {
            aumento[i] = aumen;
            aumen-=INC;
        }
        //generar los colores
        colores = new Color[10];
        colores[0] = new Color(238, 102, 15);
        colores[1] = new Color(229, 210, 23);
        colores[2] = new Color(82, 190, 51);
        colores[3] = new Color(18, 211, 138);
        colores[4] = new Color(16, 152, 211);
        colores[5] = new Color(12, 101, 234);
        colores[6] = new Color(112, 12, 234);
        colores[7] = new Color(171, 12, 234);
        colores[8] = new Color(234, 12, 112);
        colores[9] = new Color(239, 18, 18);
    }


    public void generarDiscos() {
        int x = 45, y = 680, w = 320;
        int aux = noDiscos;
        for (int i = 0; i < noDiscos; i++) {
            discos[aux - 1] = new Disco(aux, x, y, w, 40);
            System.out.println(aux);
            x += 10;
            y -= 40;
            w -= 20;
            aux--;
        }
        torres[0] = y;
        torres[1] = torres[2] = 680;
    }

    // este metodo nos retorna un numero negativo si va hacia la derecha y positivo si va a la izquierda
    public int sentido(char inicio, char fin, int disco) {
        mvoHorizontal = aumento[disco - 1];
        switch (inicio) {
            case 'A' -> mvoHorizontal += Character.compare(inicio, fin) == -1 ? 410 : 790;
            case 'B' -> mvoHorizontal += Character.compare(inicio, fin) == 1 ?  40 : 790;
            case 'C' -> mvoHorizontal += Character.compare(inicio, fin) == 1 ?  420 : 40;
        }
        return Character.compare(inicio, fin) < 1 ? 1 : 2;
    }

    public boolean mover(Movimiento mvo) {
        int n = mvo.getDisco() - 1;
        int direccion = sentido(mvo.getIni(), mvo.getFin(), mvo.getDisco());
        int aux = 65;

        // Movimiento hacia arriba
        if (!mvoArriba) {
            if (discos[n].getPy() > ALTURA_MAX) {
                discos[n].setPy( discos[n].getPy() - INC);
                repaint();
                return false;
            }
            mvoArriba = true;
            mvoHorizontal += discos[n].getPx();
            for (int i = 0; i < torres.length; i++) {
                if (mvo.getIni() == aux) {
                    torres[i] += 40;
                    return false;
                } else aux++;
            }
        }
        //Movimiento Horizontal
        if (mvoHorizontal > discos[n].getPx() && direccion == 1) {
            discos[n].setPx( discos[n].getPx() + INC);
            repaint();
            return false;
        } else if (mvoHorizontal < discos[ n ].getPx() && direccion == 2) {
            discos[n].setPx( discos[n].getPx() - INC);
            repaint();
            return false;
        }
        //Movimiento hacia abajo
        for (int i = 0; i < torres.length; i++) {
            if (mvo.getFin() == aux) {
                if (torres[i] > discos[n].getPy()) {
                    discos[n].setPy( discos[n].getPy() + INC );
                    repaint();
                    return false;
                } else torres[i] -= 40;
            }
            aux++;
        }
        mvoArriba = false;
        return true;
    }

    public void setCntrolador(ControladorHanoi c) {
        btnIniciar.addActionListener(c);
    }

}