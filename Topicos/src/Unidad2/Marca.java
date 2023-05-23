package Unidad2;
/*
    El objeto Marca se devuelve en el componente 'SeleccionMarcas'
 */

public class Marca {
    private int pkMarca;
    private String nombre;

    public Marca(int pkMarca, String nombre){
        this.pkMarca = pkMarca;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPkMarca() {
        return pkMarca;
    }
}
