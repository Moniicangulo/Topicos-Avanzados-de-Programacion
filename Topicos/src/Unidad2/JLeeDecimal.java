package Unidad2;

import java.awt.event.KeyEvent;

public class JLeeDecimal extends JLeeEntero{

    public void keyTyped(KeyEvent e) {
        char car = e.getKeyChar();
        if(car == '.'){
            if(getText().length() == 0){
                setText("0.");
                e.consume();
                return;
            }
            if(getText().length() == 1 && getText().charAt(0) == '-'){
                setText("-0.");
                e.consume();
                return;
            }
            setText( getText() + '.');
        }
        super.keyTyped(e);
    }

    public double getCantidadDecimal(){
        double cantidad =  Double.parseDouble(getText());
        return cantidad;
    }

}
