import java.util.List;

public class Main {

    public static void main(String[] args) {

        Servicios s = new Servicios("camiones.csv", "paquetes.csv");

        List<Camion> camiones = s.getCamiones();
        List<Paquete> paquetes = s.servicio2(false);

        Greedy g = new Greedy(paquetes, camiones);
        System.out.println(g.asignar());

        Backtracking bt = new Backtracking(camiones, paquetes);
        System.out.println(bt.asignar());
    }
}