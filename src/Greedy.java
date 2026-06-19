import java.util.Comparator;
import java.util.List;



/*En el algoritmo Greedy decidi priorizar ordenar primero los paquetes 
con mayor peso y los camiones de mayor capacidad para asi minimizar el peso 
de los paquetes que no fueron asignados, esto lo logro asignando los paquetes mas pesados primero
y tambien dejo los camiones con mas capacidad para que haya mas chances de asignaciones a esos paquetes mas pesados*/

public class Greedy {
    
    private List<Paquete> paquetes;
    private List<Camion> camiones;
    private Solucion solucion;

    public Greedy(List<Paquete> paq, List<Camion> cam){
        this.paquetes= paq;
        this.camiones=cam;
        this.solucion= new Solucion();

    }

    public Solucion asignar(){
        paquetes.sort(Comparator.comparing(Paquete :: getPesoKg).reversed());
        camiones.sort(Comparator.comparing(Camion :: getCapacidadKg).reversed());
        int metrica=0;
        
        for(Camion c: camiones){
            for(Paquete paq: paquetes){
                metrica++;
                if(!solucion.estaAsignado(paq) && solucion.puedeAsignarse(c,paq)){
                    solucion.asignarPaquete(c, paq);
                }
            }
        }
        solucion.setPesoNoAsignado(solucion.calcularPesoNoAsignado(paquetes));
        solucion.setMetrica(metrica);
        
        return solucion;
        
    }
}
