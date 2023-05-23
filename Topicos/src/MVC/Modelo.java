package MVC;

public class Modelo {

    private float cotizacion;

    public Modelo(){
        this(20.00f);
    }
    public Modelo(float cotizacion){
        this.cotizacion = cotizacion;
    }
    public float pesos(long cantidad) {
        return cantidad * cotizacion;
    }

    public float dolares(long cantidad) {

        return cantidad / cotizacion;
    }
}
