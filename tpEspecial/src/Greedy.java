import java.util.ArrayList;
import java.util.List;



/*En el algoritmo Greedy decidi priorizar asignar los paquetes con mayor peso 
primero y los camiones de mayor capacidad primero para asi minimizar el peso de los paquetes que no fueron asignados*/

public class Greedy {
    
    private List<Paquete> paquetes;
    private List<Camion> camiones;
    private Solucion solucion;

    public Greedy(List<Paquete> paq, List<Camion> cam){
        this.paquetes= paq;
        this.camiones=cam;
        this.solucion= new Solucion(cam);

    }

    public Solucion asignar(){
        //ordeno los paquetes y camiones
        //paquetes mayor peso primero
        //camiones mayor capacidad primero
        //recorro los camiones uno a uno 
        //asigno la mayor cant de paquetes a cada uno
        //llevo la cuenta de cuantos camiones tuve en cuenta para la metrica
        //cuando asigno un paquete a un camion lo elimino
    }



    
}
