package Unidad2;

/*
    Proyecto: Componente de Combos Dependientes
    Alumna: Monica Angulo Sanchez
    No. Control: 20170592
    Docente: Dr. Clemente Garcia Gerardo
    Fecha de entrega: 27/10/2022
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CombosDependientes extends JPanel implements ItemListener {

    JComboBox cmbEstados, cmbMunicipios, cmbCiudades;
    String estado;
    String municipio;
    static final String SELECT = "Seleccionar";

    public CombosDependientes(String estado, String municipio) {

        this.estado = estado;
        this.municipio = municipio;

        cmbEstados = new JComboBox(new String[]{estado});
        cmbMunicipios = new JComboBox(new String[]{municipio});
        cmbCiudades = new JComboBox(new String[] {SELECT});

        hazInterfaz();
        hazEscuchas();
    }
    public CombosDependientes(){

        this(SELECT, SELECT);
    }
    public CombosDependientes(String estado){
        this(estado, SELECT);
    }

    public void hazInterfaz() {
        setSize(400, 200);
        this.setLayout(new FlowLayout());

        Box box1 = Box.createHorizontalBox();
        Box box2 = Box.createHorizontalBox();
        Box box3 = Box.createHorizontalBox();

        addEstados();
        box1.add(new JLabel("Estados", JLabel.RIGHT));
        box1.add(cmbEstados);
        add(box1);
        box2.add(new JLabel("Municipios", JLabel.LEFT));
        box2.add(cmbMunicipios);
        add(box2);
        box3.add(new JLabel("Ciudades", JLabel.LEFT));
        box3.add(cmbCiudades);
        add(box3);
    }

    public void hazEscuchas(){
      cmbEstados.addItemListener(this);
      cmbMunicipios.addItemListener(this);
      cmbCiudades.addItemListener(this);
    }

    //Este metodo nos añade las ciudades tomando el estado y la ciudad
    public void addCiudades(){
        cmbCiudades.removeAllItems();
        cmbCiudades.addItem(SELECT);
        try {
            Connection conec = ConeccionSingleton.getConnection();
            Statement state = conec.createStatement();

            String query = "SELECT * FROM DBO.FN_GETCUIDADES('" + estado + "','" + municipio + "')";
            ResultSet rs = state.executeQuery(query);
            while (rs.next()) {
                cmbCiudades.addItem(rs.getString("CIUDADES"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /*
    CREATE FUNCTION DBO.FN_GETCUIDADES(@ESTADO NVARCHAR(50), @MUNICIPIO NVARCHAR(50))
    RETURNS TABLE AS
    RETURN(
	SELECT CIUDADES = C.NOMBRE
	FROM CIUDADES C
	INNER JOIN MUNICIPIOS M ON M.IDMUNICIPIO = C.IDMUNICIPIO
	INNER JOIN ESTADOS E ON E.IDESTADO = C.IDESTADO
	WHERE M.NOMBRE = @MUNICIPIO AND E.NOMBRE = @ESTADO
    )
    GO
    */

    //Este metodo nos añade los municipios tomando el estado al que pertenecen
    public void addMunicipios() {
        cmbMunicipios.removeAllItems();
        if(!municipio.equals(SELECT)){
            cmbMunicipios.addItem(municipio);
            addCiudades();
            return;
        }
        cmbCiudades.setEnabled(false);
        cmbMunicipios.addItem(SELECT);
        try {
            Connection conec = ConeccionSingleton.getConnection();
            Statement state = conec.createStatement();
            String bd = "SELECT * FROM DBO.FN_GETMUNICIPIOS('" + estado + "')" ;

            ResultSet res = state.executeQuery(bd);
            while (res.next()) {
                cmbMunicipios.addItem(res.getString("MUNICIPIOS"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    /*
    CREATE FUNCTION DBO.FN_GETMUNICIPIOS(@ESTADO NVARCHAR(50))
    RETURNS TABLE AS
    RETURN (
	SELECT MUNICIPIOS = M.NOMBRE
	FROM MUNICIPIOS M
	INNER JOIN ESTADOS E ON E.IDESTADO = M.IDESTADO
	WHERE E.NOMBRE = @ESTADO
    )
     */

    // Este metodo nos añade los estados
    public void addEstados() {
        if(!estado.equals(SELECT)){
            addMunicipios();
            return;
        }
        cmbMunicipios.setEnabled(false);
        cmbCiudades.setEnabled(false);

        try {
            Connection conec = ConeccionSingleton.getConnection();
            Statement state = conec.createStatement();
            String query = "SELECT * FROM ESTADOS";
            ResultSet rs = state.executeQuery(query);
            while (rs.next()) {
                cmbEstados.addItem(rs.getString("NOMBRE"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Lugar getLugar(){
        int est = 0, mun = 0, ciu = 0;
        try {
            Connection conec = ConeccionSingleton.getConnection();
            Statement state = conec.createStatement();

            String query = "SELECT * FROM ESTADOS WHERE NOMBRE = '" + estado + "'";
            ResultSet rs = state.executeQuery(query);
            rs.next();
            est = rs.getInt("IDESTADO");

            query = "SELECT IDMUNICIPIO FROM MUNICIPIOS WHERE NOMBRE = '" + municipio + "' AND IDESTADO = " + est;
            rs = state.executeQuery(query);
            rs.next();
            mun = rs.getInt("IDMUNICIPIO");

            query = "SELECT IDCIUDAD FROM CIUDADES WHERE NOMBRE = '" + cmbCiudades.getSelectedItem() + "' AND IDESTADO = " +
                    est + " AND IDMUNICIPIO = " + mun;
            rs = state.executeQuery(query);
            rs.next();
            ciu = rs.getInt("IDCIUDAD");
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return new Lugar(est, mun, ciu);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() != ItemEvent.SELECTED )
            return;

        if(e.getSource() == cmbEstados){
           if(cmbEstados.getSelectedItem().equals(SELECT)){
               cmbMunicipios.setEnabled(false);
               cmbCiudades.setEnabled(false);
               return;
           }
            System.out.println(cmbEstados.getSelectedItem() + "!");
            estado = (String) cmbEstados.getSelectedItem();
            municipio = SELECT;
            addMunicipios();
            cmbMunicipios.setEnabled(true);
            return;
        }
        if(e.getSource() == cmbMunicipios){
            if(cmbMunicipios.getSelectedItem().equals(SELECT)){
                cmbCiudades.setEnabled(false);
                return;
            }
            System.out.println(cmbMunicipios.getSelectedItem()+"=");
            municipio = (String) cmbMunicipios.getSelectedItem();
            addCiudades();
            cmbCiudades.setEnabled(true);
        }
    }

}
