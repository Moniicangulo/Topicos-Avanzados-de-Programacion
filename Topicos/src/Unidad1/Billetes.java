package Unidad1;


import Unidad2.AplExamenU2;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;

public class Billetes implements Serializable {


    static File f;
    static RandomAccessFile arch;

    public static void main(String[] args) {
        try {
            Abrir();
            Captura();
            ImprimeDenominaciones();
            Cerrar();

        } catch (IOException e) {
            System.out.println("se presentó algún error");
            return;
        }

    }

    public static void Abrir() throws IOException{
        f=new File("BILLETES.DAT");
        arch=new RandomAccessFile(f,"rw");
    }

    public static void Cerrar() throws IOException{
        arch.close();

    }
    public static void Imprime() {

        int nombre;
        int nc;
        try {
            arch.seek(0);
            while (true) {
                nc=arch.readInt();
                nombre=arch.readInt();
                System.out.printf("%10d\t %10d\n", nc,nombre);
            }
        } catch (IOException e) {
            System.out.println("+++++ fin de contenido ++++++");
            return;
        }
    }
    public static void ImprimeDenominaciones() {

        int nombre;
        int nc;

        int [] d= {1000,500,200,100,50,20,10,5,2,1};
        try {
            for(int i=d.length-1 ; i>=0  ; i--) {
                arch.seek(  (d[i]-1)*8 );

                nc=arch.readInt();
                nombre=arch.readInt();
                System.out.printf("%10d\t %10d\n", nc,nombre);

            }
        } catch (IOException e) {
            System.out.println("+++++ fin de contenido ++++++");
            return;
        }
    }
    public static void Captura() throws IOException{
        arch.seek(0);
        for(int i=1 ; i<1001 ; i++) {
            arch.writeInt(i);
            if("1*2*5*10*20*50*100*200*500*1000".indexOf((i)+"")>-1) {

                arch.writeInt(AplExamenU2.Rutinas.nextInt(5,15));
            }
            else {
                arch.writeInt(0);
            }
        }
    }

}
