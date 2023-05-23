package Unidad3;

import Unidad2.ConeccionSingleton;
import Unidad2.Lugar;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AplPingPong {
    public static void main(String[] args) {
        Semaforo sping = new Semaforo(1);
        Semaforo spong = new Semaforo(1);
        Ping ping = new Ping(sping, spong);
        Pong pong = new Pong(sping, spong);

      // pong.setPriority(Thread.MIN_PRIORITY);
     //  ping.setPriority(Thread.MAX_PRIORITY);
       ping.start();
       pong.start();

    }
}
