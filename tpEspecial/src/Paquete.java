import com.opencsv.bean.CsvBindByName;

public class Paquete implements Comparable<Paquete>{
    @CsvBindByName(column = "id_paquete")
    private String idPaquete;

    @CsvBindByName(column = "codigo_paquete")
    private String codigoPaquete;

    @CsvBindByName(column = "peso_kg")
    private double pesoKg;

    @CsvBindByName(column = "contiene_alimentos")
    private boolean contieneAlimentos;

    @CsvBindByName(column = "nivel_urgencia")
    private int nivelUrgencia;

    public Paquete(String id, String codigo,boolean contieneAlimentos, double peso, int nvlUrg){
        this.idPaquete=id;
        this.codigoPaquete=codigo;
        this.pesoKg=peso;
        this.contieneAlimentos=contieneAlimentos;
        this.nivelUrgencia=nvlUrg;
    }

    public Paquete(){
        
    }

    
    public String getIdPaquete(){
        return idPaquete; 
    }
    public String getCodigoPaquete(){ 
        return codigoPaquete; 
    }
    public double getPesoKg(){ 
        return pesoKg; 
    }
    public boolean contieneAlimentos(){ 
        return contieneAlimentos; 
    }
    public int getNivelUrgencia(){ 
        return nivelUrgencia; 
    }

    @Override
    public int compareTo(Paquete p) {
        return Double.compare(p.getPesoKg(), this.getPesoKg());
        

    }
}
