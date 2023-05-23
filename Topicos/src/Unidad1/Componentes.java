package Unidad1;

import javax.swing.*;

public class Componentes {

    public static int getTxtNumber(JTextField txt) {
        int numero;
        try {
            numero = Integer.parseInt(txt.getText());
            if (numero > 0) {
                txt.setText("");
                return numero;
            }
        } catch (Exception ex) {
            System.out.println("No puede ingresar letras");
        }
        txt.setText("");
        MessajeError("Debe de ingresar una cantidad valida > 0", "Error al ingresar los datos.");
        return 0;
    }

    public static void MessajeError(String texto, String title) {
        JOptionPane.showMessageDialog(null,texto, title ,JOptionPane.ERROR_MESSAGE);
        //int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro?");
    }

}
