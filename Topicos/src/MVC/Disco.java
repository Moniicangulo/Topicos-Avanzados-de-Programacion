package MVC;

public class Disco {
    private int numero;
    private int px, py, width, heigth;


    public Disco(int numero, int px, int py, int width, int heigth){
        this.numero = numero;
        this.px = px;
        this.py = py;
        this.width = width;
        this.heigth = heigth;
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeigth() {
        return heigth;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }

    public int getPy() {
        return py;
    }

    public void setPy(int py) {
        this.py = py;
    }

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

}
