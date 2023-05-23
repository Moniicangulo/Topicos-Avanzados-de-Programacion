package MVC;

public class AplHanoi {
    public static void main(String [] a) {
        System.out.println("________________");
        int n = 5;
        VistaHanoi vista = new VistaHanoi(n);
        ModeloHanoi modelo = new ModeloHanoi(n);
        ControladorHanoi controlador=new ControladorHanoi(vista,modelo);
        vista.setCntrolador(controlador);

    }
}
