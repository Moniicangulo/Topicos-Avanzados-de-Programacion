package Unidad2;
/*
    Proyecto: Componente de seleccion de marcas
    Alumna: Monica Angulo Sanchez
    No. Control: 20170592
    Docente: Dr. Clemente Garcia Gerardo
    Fecha de entrega: 27/10/2022
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SeleccionMarcas extends JPanel implements KeyListener, ItemListener, ActionListener {
    JTextField txtBusqueda;
    JButton btnLimpiar;
    ArrayList<JCheckBox> checkboxs;
    ArrayList<String> seleccion;
    JScrollPane scroll;
    Box box;
    ArrayList<Marca> marcas;
    Marca marca;

    public SeleccionMarcas() {
        checkboxs = new ArrayList<>();
        seleccion = new ArrayList<>();
        marcas = new ArrayList<>();
        hazInterfaz();
        hazEscuchas();
    }

    public void hazInterfaz() {

        setSize(250, 500);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        txtBusqueda = new JTextField();
        btnLimpiar = new JButton("Limpiar");
        box = Box.createVerticalBox();

        getMarcas("");
        scroll = new JScrollPane(box);


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.005;
        gbc.insets = new Insets(3, 5, 3, 5);
        gbc.fill = GridBagConstraints.BOTH;
        add(txtBusqueda, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.BOTH;
        add(scroll, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.001;
        gbc.fill = GridBagConstraints.WEST;
        add(btnLimpiar, gbc);
    }

    public void hazEscuchas() {
        txtBusqueda.addKeyListener(this);
        btnLimpiar.addActionListener(this);

    }

    public void getMarcas(String busqueda) {
        String bd;
        box.removeAll();
        checkboxs.clear();

        JCheckBox aux;
        try {
            Connection conec = ConeccionSingleton.getConnection();
            Statement state = conec.createStatement();
            bd = "SELECT * FROM MARCAS WHERE NOMBRE LIKE '%" + busqueda + "%' AND VIGENCIA = 'A' ORDER BY NOMBRE";

            ResultSet res = state.executeQuery(bd);
            while (res.next()) {
                aux = new JCheckBox(res.getString("NOMBRE"));
                checkboxs.add(aux);
                aux.addItemListener(this);
                box.add(aux);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        box.validate();
        this.validate();
    }

    public Marca getObjetoMarca(String marca) {
        int id = 0;
        String nombre = "";
        try {
            Connection conec = ConeccionSingleton.getConnection();
            Statement state = conec.createStatement();
            String query = "SELECT * FROM MARCAS WHERE NOMBRE = '" + marca + "' AND VIGENCIA = 'A'";

            ResultSet rs = state.executeQuery(query);
            rs.next();
            id = rs.getInt("ID");
            nombre = rs.getString("NOMBRE");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new Marca(id, nombre);
    }

        public ArrayList<Marca> getSeleccion () {
                marcas.clear();
                for (JCheckBox select : checkboxs) {
                    if (select.isSelected()) {
                        marcas.add( getObjetoMarca( select.getText() ) );
                    }
                }
            return marcas;
        }

        @Override
        public void keyReleased (KeyEvent e){
            String bus = txtBusqueda.getText();
            getMarcas(bus);
            scroll.updateUI();
        }

        @Override
        public void itemStateChanged (ItemEvent e){

        }

        @Override
        public void actionPerformed (ActionEvent e){

            if(txtBusqueda.getText().equals("")){
                for (JCheckBox select : checkboxs) {
                    if (select.isSelected()) {
                        select.setSelected(false);
                    }
                }
                return;
            }
            txtBusqueda.setText("");
            getMarcas("");
            seleccion.clear();
        }

        @Override
        public void keyTyped (KeyEvent e){

        }

        @Override
        public void keyPressed (KeyEvent e){

        }
    }
