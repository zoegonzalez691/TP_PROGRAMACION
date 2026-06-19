import java.util.List;
//en esta seccion vas a poder probar todos los servicios del programa
public class Main {

    public static void main(String[] args) {

        Servicios s = new Servicios("camiones.csv", "paquetes.csv");

        List<Camion> camiones = s.getCamiones();
        List<Paquete> paquetes = s.getPaquetes();

        Greedy g = new Greedy(paquetes, camiones);
        System.err.println("GREEDY");
        System.out.println(g.asignar());

        Backtracking bt = new Backtracking(camiones, paquetes);
        System.out.println("BACKTRACKING");
        System.out.println(bt.asignar());

        System.out.println("SERVICIO 1");
        System.out.println(s.servicio1("COD1"));

        System.out.println("SERVICIO 2 - CON ALIMENTOS");
        System.out.println(s.servicio2(true));

        System.out.println("SERVICIO 2 - SIN ALIMENTOS");
        System.out.println(s.servicio2(false));

        System.out.println("SERVICIO 3");
        System.out.println(s.servicio3(2, 5));
    }
}