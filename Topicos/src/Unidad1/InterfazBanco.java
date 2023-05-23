package Unidad1;

import Unidad2.AplExamenU2;
import Unidad2.ConeccionSingleton;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class InterfazBanco extends JFrame implements ActionListener, Serializable, ChangeListener {

    /*
    29.09.2022
    Proyecto Banco
    Nombre: Angulo Sanchez Monica
    No Control: 20170592
    Topicos Avanzados de Programacion
    Dr. Clemente Garcis Gerardo
     */

    static File f;
    static RandomAccessFile arch;

    int importe, amount, denomination, cambio;
    int[] denominations;
    Vector fila;
    JPanel panelEtiqueta;
    JTextField txtImporte;
    JButton btnChange, btnAddMoney, btnAccept;
    JLabel labelAvailableMoney, withdrawal;
    JTable tableMoney, tableChange;
    JScrollPane jsp1;
    JTabbedPane tabs;
    DefaultTableModel modelInventory, modelChange;
    Color FOND = new Color(230, 239, 251);

    public InterfazBanco() throws IOException {
        super("Banco de cambio");
        makeInterface();
        makeListener();
        importe = 0;
    }

    public void makeInterface() throws IOException {
        denominations = new int[]{1000, 500, 200, 100, 50, 20, 10, 5, 2, 1};
        open();
        this.setSize(500, 550);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBackground(FOND);
        this.setIconImage(new ImageIcon("monopoly.png").getImage());

        tabs = new JTabbedPane();
        JPanel panelEncabezado = new JPanel();
        JPanel panelbtn        = new JPanel();
        JPanel panelHome       = new JPanel();
        JPanel panelBanco      = new JPanel();
               panelEtiqueta   = new JPanel();
        JLabel title1 = new JLabel("BANCO DE CAMBIO", JLabel.CENTER);
        JLabel title2 = new JLabel("CAJA 1", JLabel.CENTER);
        JLabel labelImport = new JLabel(" ", JLabel.RIGHT);
               withdrawal  = new JLabel(" ", JLabel.CENTER);
               labelAvailableMoney = new JLabel(" ", JLabel.CENTER);
               btnAccept = new JButton("Aceptar");

        String[] columns = {"Denominacion", "Cantidad"};
        tableMoney = new JTable();
        modelInventory = new DefaultTableModel();
        modelInventory.setColumnIdentifiers(columns);
        tableMoney.setModel(modelInventory);
        tableMoney.setBackground(FOND);
        availableMoney();

        tableChange = new JTable();
        modelChange = new DefaultTableModel();
        modelChange.setColumnIdentifiers(columns);
        tableChange.setModel(modelChange);
        tableChange.setBackground(FOND);
        jsp1 = new JScrollPane(tableChange);

        title1.setForeground(Color.white);
        title2.setForeground(Color.white);
        title1.setFont(new Font("Arial", Font.PLAIN, 20));
        title2.setFont(new Font("Arial", Font.PLAIN, 16));
        panelEncabezado.setLayout(new GridLayout(2, 0));
        panelEncabezado.setBackground(new Color(89, 102, 232));
        panelEncabezado.add(title1);
        panelEncabezado.add(title2);
        add(panelEncabezado, BorderLayout.NORTH);

        panelbtn.setLayout(new GridLayout(0, 3));
        txtImporte = new JTextField();
        btnChange = new JButton("Cambiar");
        btnChange.setBackground(new Color(205, 208, 241));
        labelImport.setText("Importe  ");
        labelImport.setFont(new Font("Arial", Font.PLAIN, 16));
        panelbtn.setBackground(FOND);
        panelbtn.add(labelImport);
        panelbtn.add(txtImporte);
        panelbtn.add(btnChange);

        withdrawal.setFont(new Font("Arial", Font.PLAIN, 18));
        panelEtiqueta.setLayout(new GridLayout(2, 0));
        panelEtiqueta.setBackground(FOND);
        panelEtiqueta.add(withdrawal);
        panelEtiqueta.add(btnAccept);

        btnAddMoney = new JButton("Añadir dinero");
        btnAddMoney.setBackground(new Color(205, 208, 241));

        panelHome.setBackground(FOND);
        panelHome.add(panelbtn);
        panelHome.add(jsp1);
        add(panelEtiqueta, BorderLayout.SOUTH);
        btnAccept.setVisible(false);
        jsp1.setVisible(false);

        labelAvailableMoney.setFont(new Font("Arial", Font.PLAIN, 22));
        panelBanco.setBackground(FOND);
        JScrollPane jsp2 = new JScrollPane(tableMoney);
        panelBanco.add(btnAddMoney);
        panelBanco.add(jsp2);

        tabs.addTab("PAGINA DE INICIO", panelHome);
        tabs.addTab("INVENTARIO DEL BANCO", panelBanco);
        tabs.setBackground(new Color(205, 208, 241));
        add(tabs);
        tabs.setSelectedIndex(0);

        showInventory();
        setVisible(true);
    }

    public void makeListener() {
        btnChange.addActionListener(this);
        btnAddMoney.addActionListener(this);
        txtImporte.addActionListener(this);
        btnAccept.addActionListener(this);
        tabs.addChangeListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAddMoney) {
            addMoney();
            showInventory();
            availableMoney();
            return;
        }
        if (e.getSource() == btnAccept) {
            btnAccept.setVisible(false);
            jsp1.setVisible(false);
            withdrawal.setText("");
            btnChange.setEnabled(true);
            txtImporte.setEnabled(true);
            return;
        }
        importe = Componentes.getTxtNumber(txtImporte);
        if (importe != 0) {
            if (validateChange()) {
                doChange();
                showInventory();
                availableMoney();
                btnAccept.setVisible(true);
                jsp1.setVisible(true);
                btnChange.setEnabled(false);
                txtImporte.setEnabled(false);
            }
        }
    }

    public void showInventory() {
        modelInventory.setRowCount(0);
        /*
        try {
            for (int i = 0; i < denominations.length; i++) {
                fila = new Vector();
                arch.seek((denominations[i] - 1) * 8);
                fila.addElement(arch.readInt());
                fila.addElement(arch.readInt());
                modelInventory.addRow(fila);
            }
        } catch (IOException e) {
            System.out.println("* * * * *");
        }
        */

        try {
            Connection conec = ConeccionSingleton.getConnection();
            Statement state = conec.createStatement();
            String bd = "SELECT * FROM BILLETES ORDER BY DENOMINACION DESC";

            ResultSet res = state.executeQuery(bd);
            while (res.next()){
                fila = new Vector();
                fila.addElement(res.getInt("DENOMINACION"));
                fila.addElement(res.getInt("CANTIDAD"));
                modelInventory.addRow(fila);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return;
        }
    }

    public void doChange() {
        modelChange.setRowCount(0);
        withdrawal.setText("Se retiro $" + importe);
        /*
        try {
            for (int i = 0; i < denominations.length; i++) {
                fila = new Vector();
                arch.seek((denominations[i] - 1) * 8 + 4);
                amount = arch.readInt();
                cambio = importe / denominations[i];

                if (cambio > amount) cambio = amount;

                importe = importe - (cambio * denominations[i]);

                if (cambio != 0) {
                    fila.addElement(denominations[i]);
                    fila.addElement(cambio);
                    modelChange.addRow(fila);

                    arch.seek((denominations[i] - 1) * 8 + 4);
                    arch.writeInt(amount - cambio);
                }
            }
        } catch (IOException e) {
            System.out.println("+++++ fin de contenido ++++++ ");
        }
        */

        try {
            Connection conec = ConeccionSingleton.getConnection();
            Statement state = conec.createStatement();
            Statement stat = conec.createStatement();
            String bd = "SELECT * FROM BILLETES ORDER BY DENOMINACION DESC";
            String update;
            ResultSet res = state.executeQuery(bd);
            ResultSet up;
            while (res.next()){
                System.out.println(" a");

                fila = new Vector();
                amount = res.getInt("CANTIDAD");
                cambio = importe / res.getInt("DENOMINACION");

                if (cambio > amount) cambio = amount;
                System.out.println("c" + cambio);
                importe = importe - (cambio * res.getInt("DENOMINACION"));

                if (cambio != 0) {
                    System.out.println("c" + cambio +"b");
                    fila.addElement(res.getInt("DENOMINACION"));
                    fila.addElement(cambio);
                    update = "UPDATE BILLETES SET CANTIDAD = "+ (amount - cambio) +" WHERE DENOMINACION = " + res.getInt("DENOMINACION") ;
                    stat.executeUpdate(update);
                    System.out.println("aqui toi");
                    modelChange.addRow(fila);
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return;
        }
    }

    public boolean validateChange() {
        int importeAux = importe;
        /*
        try {
            for (int i = 0; i < denominations.length; i++) {
                arch.seek((denominations[i] - 1) * 8 + 4);
                amount = arch.readInt();
                cambio = importeAux / denominations[i];
                if (cambio > amount) cambio = amount;

                importeAux = importeAux - (cambio * denominations[i]);
            }
        } catch (IOException e) {
            return false;
        }
        if (importeAux > 0) {
            Componentes.MessajeError("Lo sentimos el banco no cuenta con el dinero sufiente.", "Banco sin fondos.");
            return false;
        }
        return true;
        */

        try {
            Connection conec = ConeccionSingleton.getConnection();
            Statement state = conec.createStatement();
            String bd = "SELECT * FROM BILLETES ORDER BY DENOMINACION DESC";

            ResultSet res = state.executeQuery(bd);
            while (res.next()){
                amount = res.getInt("CANTIDAD");
                cambio = importeAux / res.getInt("DENOMINACION");
                if (cambio > amount) cambio = amount;

                importeAux = importeAux - (cambio * res.getInt("DENOMINACION"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        if (importeAux > 0) {
            Componentes.MessajeError("Lo sentimos el banco no cuenta con el dinero sufiente.", "Banco sin fondos.");
            return false;
        }
        return true;
    }

    public void addMoney() {
        /*
        try {
            for (int i = 0; i < denominations.length; i++) {
                arch.seek((denominations[i] - 1) * 8 + 4);
                amount = arch.readInt();
                arch.seek((denominations[i] - 1) * 8 + 4);
                arch.writeInt(amount + Rutinas.nextInt(10, 20));
            }
        } catch (IOException e) {
            System.out.println("* * * * *");
        }
        */
        String update;
        try {
            Connection conec = ConeccionSingleton.getConnection();
            Statement state = conec.createStatement();
            Statement stat = conec.createStatement();
            String bd = "SELECT * FROM BILLETES ORDER BY DENOMINACION DESC";

            ResultSet res = state.executeQuery(bd);
            while (res.next()) {
                System.out.println("a");
                amount = res.getInt("CANTIDAD");
                amount = amount + AplExamenU2.Rutinas.nextInt(10, 20);
                update = "UPDATE BILLETES SET CANTIDAD = "+ amount +" WHERE DENOMINACION = " + res.getInt("DENOMINACION");
                stat.executeUpdate(update);
            }
            }catch(Exception e){
                System.out.println(e.getMessage());
                return;
            }
        }


    public void availableMoney() {
        int available = 0;
        /*
        try {
            for (int i = 0; i < denominations.length; i++) {
                arch.seek((denominations[i] - 1) * 8 + 4);
                amount = arch.readInt();
                available += amount * denominations[i];
            }
        } catch (IOException e) {
            System.out.println("* * * * *");
        }
        */
        try {
            Connection conec = ConeccionSingleton.getConnection();
            Statement state = conec.createStatement();
            String bd = "SELECT * FROM BILLETES ORDER BY DENOMINACION DESC";

            ResultSet res = state.executeQuery(bd);
            while (res.next()) {
                    amount = res.getInt("CANTIDAD");
                    available += amount * res.getInt("DENOMINACION");

            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            return;
        }

        labelAvailableMoney.setText("Dinero disponible $" + available + " ");
    }

    public static void open() throws IOException {
        f = new File("BILLETES.DAT");
        arch = new RandomAccessFile(f, "rw");
    }

    public static void main(String[] args) throws IOException {
        new InterfazBanco();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (tabs.getSelectedIndex() == 0) {
            this.remove(labelAvailableMoney);
            add(panelEtiqueta, BorderLayout.SOUTH);
        } else {
            this.remove(panelEtiqueta);
            add(labelAvailableMoney, BorderLayout.SOUTH);
        }
        this.update(this.getGraphics());
    }
}
