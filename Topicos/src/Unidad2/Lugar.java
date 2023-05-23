package Unidad2;
/*
    Cree un objeto Lugar el cual sera devuelto en el componentes de combos dependientes
    este objeto tiene como atributos las pk's del 'lugar' que se es seleccionada
 */

public class Lugar{
    private int pkEstado, pkMunicipio, pkCiudad;

    public Lugar(int pkEstado, int pkMunicipio, int pkCiudad){
        this.pkEstado = pkEstado;
        this.pkMunicipio = pkMunicipio;
        this.pkCiudad = pkCiudad;
    }

    public int getPkEstado(){
        return pkEstado;
    }
    public int getPkMunicipio(){
        return pkMunicipio;
    }
    public int getPkCiudad(){
        return pkCiudad;
    }
    public  void setPkEstado(int pkEstado){
        this.pkEstado = pkEstado;
    }
    public  void setPkMunicipio(int pkMunicipio){
        this.pkMunicipio = pkMunicipio;
    }
    public  void setPkCiudad(int pkCiudad){
        this.pkCiudad = pkCiudad;
    }
}
