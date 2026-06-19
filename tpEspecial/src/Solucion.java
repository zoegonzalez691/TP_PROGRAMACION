import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


/*Se desea establecer una asignación de todos los paquetes a los camiones disponibles con el objetivo de 
minimizar el peso total de los paquetes que no pudieron ser asignados a ningún camión.
Se sabe que existen ciertas restricciones para asignar un paquete a un camión:
• Primero, ningún camión podrá superar su capacidad máxima de carga. La capacidad de cada camión está 
definida en el archivo de entrada.
• Segundo, los paquetes que contienen alimentos sólo podrán ser asignados a camiones refrigerados. */

public class Solucion {

    private Map<Camion, List<Paquete>> asignados;
    private double pesoTotalSinAsig;
    private int metrica;
    private HashSet<Paquete> paquetesAsignados;

    public Solucion(){
        this.asignados= new HashMap<>();
        this.paquetesAsignados= new HashSet<>();
        this.pesoTotalSinAsig=0;
        this.metrica=0;
    }

    public void asignarPaquete(Camion c, Paquete p ){
        if(!asignados.containsKey(c)){
            asignados.put(c, new ArrayList<>());
            
        }
        asignados.get(c).add(p);
        paquetesAsignados.add(p);
        
    }

    public void desasignarPaquete(Camion c, Paquete p){
        if(asignados.containsKey(c)){
            asignados.get(c).remove(p);
            paquetesAsignados.remove(p);
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

    public boolean estaAsignado(Paquete p) {
        return (paquetesAsignados.contains(p));

    }

    public double calcularPesoNoAsignado(List<Paquete>paquet){
        double pesoTotal=0;

        for(Paquete p: paquet){
            if(!estaAsignado(p)){
                pesoTotal+= p.getPesoKg();
            }
        }
        return pesoTotal;
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

    public Solucion copiarSolucion(){
        Solucion copia= new Solucion();
        copia.pesoTotalSinAsig= this.pesoTotalSinAsig;
        copia.metrica= this.metrica;
        copia.paquetesAsignados = new HashSet<>(this.paquetesAsignados);

        for(Camion c: this.asignados.keySet()){
            List<Paquete> listaNueva = new ArrayList<>();

            for (Paquete p : this.asignados.get(c)) {
               listaNueva.add(p);
            }

        copia.asignados.put(c, listaNueva);
       }

       return copia;
    }


    public void setPesoNoAsignado(double peso){
        this.pesoTotalSinAsig=peso;

    }

    public double getPesoNoAsig(){
        return this.pesoTotalSinAsig;
    }

    public void setMetrica(int met){
        this.metrica=met;
    }

    public int getMetrica(){
        return this.metrica;
    }

   @Override
    public String toString() {
        String texto = "SOLUCION:\n";
        for (Camion c : asignados.keySet()) {
            texto += "Camion " + c.getIdCamion() + ":\n";
            for (Paquete p : asignados.get(c)) {
                texto += "   Paquete " + p.getIdPaquete() + "\n";
            }
        }

        texto += "Peso no asignado: " + pesoTotalSinAsig + "\n";
        texto += "Metrica: " + metrica + "\n";

        return texto;
    }
}
