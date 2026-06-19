import java.util.List;

public class Backtracking {
    private List<Camion> camiones;
    private List<Paquete> paquetes;
    private Solucion mejorSolucion;
    private int contEstados;
    private double  pesoNoAsignadoActual;
    
    /*en mi algoritmo de Backtracking opte por hacer un recorrido total buscando encontrar 
    la mejor solucion(la que tenga menor peso no asignado), mi caso base seria cuando llego al fin de mis paquetes 
    y mi caso de poda lo implemente parsa que cuando ya vaya sumando mas de peso no asignado que lo que 
    tengo en la mejor solucion ya no siga intentando por ahi porque no va a ser mejor que la mejor hasta el momento */
    
    public Backtracking(List<Camion> cam, List<Paquete> paq){
        this.camiones= cam;
        this.paquetes=paq;
        this.mejorSolucion=new Solucion();
        this.contEstados=0;
        this.pesoNoAsignadoActual=0;
        mejorSolucion.setPesoNoAsignado(Double.MAX_VALUE);
    }

    public Solucion asignar(){
        Solucion solucionActual= new Solucion();
        int indicePaq= 0;
        
        asignar(solucionActual,indicePaq, pesoNoAsignadoActual);
        mejorSolucion.setMetrica(contEstados);


        return mejorSolucion;
    }

    private void asignar(Solucion solucionActual, int indicePaq, double  pesoNoAsignadoActual){
       contEstados++;

        if(indicePaq==paquetes.size()){
            if(pesoNoAsignadoActual<mejorSolucion.getPesoNoAsig()){
               mejorSolucion=solucionActual.copiarSolucion();
               mejorSolucion.setPesoNoAsignado(pesoNoAsignadoActual);
            }
             return;
        }

        if(pesoNoAsignadoActual >= mejorSolucion.getPesoNoAsig()){
            return;
        }
        Paquete paq= paquetes.get(indicePaq);
        for(Camion c: camiones){
            if(solucionActual.puedeAsignarse(c,paq)){
                solucionActual.asignarPaquete(c, paq);
                asignar(solucionActual,indicePaq + 1,pesoNoAsignadoActual);
                solucionActual.desasignarPaquete(c,paq);
               
            }
        }
        
        asignar(solucionActual,indicePaq+1,pesoNoAsignadoActual + paq.getPesoKg());
    }

    
}
