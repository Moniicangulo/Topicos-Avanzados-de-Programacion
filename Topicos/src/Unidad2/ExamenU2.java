package Unidad2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



/*
 Angulo Sanchez Monica
 Examen Unidad 2
 Topicos Avanzados de programacion
 */
public class ExamenU2 extends JPanel implements KeyListener, ComponentListener, ActionListener {

    String[] imagenes;
    JTextField txtBusqueda;
    JLabel lbltipo;
    String tipo;
    JButton btnImagenJpg;
    JButton btnExplicacion;
    JButton btnImagenPng;
    ImageIcon imagenJpg;
    ImageIcon imagenPng;


    public ExamenU2(String tipo, String[] imagenes) {
        this.tipo = tipo;
        this.imagenes = imagenes;
        hazInterfaz();
        hazEscuchas();
    }

    public void hazInterfaz() {
        this.setSize(400, 400);
        this.setLayout(null);

        imagenJpg = new ImageIcon();
        imagenPng = new ImageIcon();

        txtBusqueda = new JTextField(tipo);
        btnExplicacion = new JButton("Explicacion");
        lbltipo = new JLabel(tipo);

        btnImagenJpg = new JButton();
        btnImagenJpg.setVisible(false);
        add(btnImagenJpg);
        btnImagenPng = new JButton();
        btnImagenPng.setVisible(false);
        add(btnImagenPng);
        add(lbltipo);
        lbltipo.setVisible(false);
        add(txtBusqueda);
        add(btnExplicacion);
        btnExplicacion.setVisible(false);


    }

    public void hazEscuchas() {
        txtBusqueda.addKeyListener(this);
        btnExplicacion.addActionListener(this);
        this.addComponentListener(this);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        int h = this.getHeight();
        int w = this.getWidth();

        btnImagenJpg.setBounds((int) (w * 0.10), (int) (h * 0.10), (int) (w * 0.10), (int) (h * 0.10));
        lbltipo.setBounds((int) (w * 0.20), (int) (h * 0.10), (int) (w * 0.3), (int) (h * 0.1));
        txtBusqueda.setBounds((int) (w * 0.10), (int) (h * 0.20), (int) (w * 0.50), (int) (h * 0.1));
        btnExplicacion.setBounds((int) (w * 0.60), (int) (h * 0.20), (int) (w * .20), (int) (h * 0.1));
        btnImagenPng.setBounds((int) (w * 0.10), (int) (h * 0.30), (int) (w * 0.50), (int) (h * 0.3));

    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource() == txtBusqueda) {

            if (txtBusqueda.getText().equals(tipo)) {
                lbltipo.setVisible(true);
                txtBusqueda.setText("");

            }

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == txtBusqueda) {

            String busqueda = txtBusqueda.getText().toLowerCase();
            txtBusqueda.setText(busqueda);


            if (txtBusqueda.getText().length() == 0) {
                txtBusqueda.setText(tipo);
                lbltipo.setVisible(false);
                btnImagenJpg.setVisible(false);
                return;
            }

            for (int i = 0; i < imagenes.length; i++) {

                if (txtBusqueda.getText().equals(imagenes[i].toLowerCase())) {
                    btnExplicacion.setVisible(true);
                    btnImagenJpg.setVisible(true);
                    imagenJpg = new ImageIcon(imagenes[i] + ".jpg");
                    imagenPng = new ImageIcon(imagenes[i] + ".png");
                    btnImagenJpg.setIcon(imagenJpg);
                    this.updateUI();
                    return;
                } else {
                    btnImagenJpg.setVisible(false);
                    btnExplicacion.setVisible(false);
                    btnImagenPng.setVisible(false);
                }
            }

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnExplicacion) {
            btnImagenPng.setIcon(imagenPng);
            btnImagenPng.setVisible(true);
        }
    }
    public static ImageIcon AjustarImagen(String ico,int Ancho,int Alto)
    {
        ImageIcon tmpIconAux = new ImageIcon(ico);
        //Escalar Imagen
        ImageIcon tmpIcon = new ImageIcon(tmpIconAux.getImage().getScaledInstance(Ancho,
                Alto, Image.SCALE_SMOOTH));//SCALE_DEFAULT
        return tmpIcon;
    }
}
