import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*Se desea establecer una asignación de todos los paquetes a los camiones disponibles con el objetivo de minimizar el peso total de los paquetes que no pudieron ser asignados a ningún camión.
Se sabe que existen ciertas restricciones para asignar un paquete a un camión:
• Primero, ningún camión podrá superar su capacidad máxima de carga. La capacidad de cada camión está definida en el archivo de entrada.
• Segundo, los paquetes que contienen alimentos sólo podrán ser asignados a camiones refrigerados. */
public class Solucion {

    private Map<Camion, List<Paquete>> asignados;
    private double pesoTotalSinAsig;
    private int metrica;

    public Solucion(List<Camion> camiones){
        this.asignados= new HashMap<>();
        cargarCamiones(camiones);
        this.pesoTotalSinAsig=0;
        this.metrica=0;
    }

    public void cargarCamiones(List<Camion> camiones){
        for(Camion c: camiones){
            asignados.put(c, new ArrayList<>());
        }
    }

    public void asignarPaquetes(Camion c, Paquete p ){
        if(asignados.containsKey(c)&& puedeAsignarse(c, p)){
            asignados.get(c).add(p);
        }
        else{
            pesoTotalSinAsig+=p.getPesoKg();
        }
    }
    
    public boolean puedeAsignarse(Camion cam, Paquete paq){
        double pesoActualAsig= pesoAsignado(cam);
        if(paq.contieneAlimentos() && !cam.esRefrigerado()){
            return false;
        }
        if(pesoActualAsig + paq.getPesoKg()>cam.getCapacidadKg()){
            return false;
        }
        return true;
    }

    private double pesoAsignado(Camion cam){
        double totalPeso=0;
        if(asignados.containsKey(cam)){
            for(Paquete p: asignados.get(cam)){
                totalPeso+= p.getPesoKg();
            }
        }
        return totalPeso;
    }

    public double getPesoNoAsig(){
        return this.pesoTotalSinAsig;
    }

    public int getMetrica(){
        return this.metrica;
    }
    


    
}
