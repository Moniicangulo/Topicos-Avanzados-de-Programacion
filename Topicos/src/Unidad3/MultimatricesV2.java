package Unidad3;
class ParImpar extends Thread{
    int[][]  m1, m2, m3;
    int rengInicio, totalHilos;

    public ParImpar(int[][] m1, int[][] m2, int[][] m3, int rengInicio, int totalHilos){
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
        this.rengInicio = rengInicio;
        this.totalHilos = totalHilos;
    }

    public void run(){
        for (int i = rengInicio; i < m1.length; i+=totalHilos) {
            for (int j = 0; j < m1.length; j++) {
                for (int k = 0; k < m1.length ; k++) {
                    m3[j][k] += m1[i][j] * m2[j][i];
                }
            }
        }
    }

}
public class MultimatricesV2 {
    public static void main(String[] args) {
        final int n = 3000;
        int[][] m1 = new int[n][n];
        int[][] m2 = new int[n][n];
        int[][] m3 = new int[n][n];

        llena(m1);
        llena(m2);
        llena(m3);

        ParImpar [] hilos = new ParImpar[20];
        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new ParImpar(m1, m2, m3, i, 20);
        }

        for (int i = 0; i < hilos.length; i++) {
            hilos[i].start();
        }

        while ( noMoridos(hilos) );
        imprime(m1);
        imprime(m2);
        imprime(m3);
    }

    public static boolean noMoridos(ParImpar [] a){
        for (int i = 0; i < a.length; i++)
            if(a[i].isAlive()) return true;
        return false;
    }

    public static void imprime(int [][] m){
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                System.out.printf("%4d", m[i][j]);
            }
            System.out.println();
        }
    }

    public static void llena(int [][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                m[i][j] = (i + 1);
            }
        }
    }
}
