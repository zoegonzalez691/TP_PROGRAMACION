import com.opencsv.bean.CsvBindByName;

public class Camion {
    @CsvBindByName(column = "id_camion")
    private String idCamion;

    @CsvBindByName(column = "patente")
    private String patente;

    @CsvBindByName(column = "esta_refrigerado")
    private boolean estaRefrigerado;

    @CsvBindByName(column = "capacidad_kg")
    private double capacidadKg;

    public Camion(String id, String patente, boolean estaRefrigerado,double capacidad){
        this.idCamion=id;
        this.patente=patente;
        this.estaRefrigerado= estaRefrigerado;
        this.capacidadKg= capacidad;
    }

    public Camion(){
        
    }

    
    public String getIdCamion(){
        return idCamion; 
    }
    public String getPatente(){
        return patente; 
    }
    public boolean esRefrigerado(){
        return estaRefrigerado; 
    }
    public double getCapacidadKg(){  
        return capacidadKg; 
    }
    @Override
    public boolean equals(Object obj) {
        Camion otroCamion = (Camion) obj;
        return this.idCamion.equals(otroCamion.idCamion);
    }

    @Override
    public int hashCode() {
        return idCamion.hashCode();
    }

    @Override
    public String toString(){
        return "Camion{" + "id= " +idCamion + '\'' +
                 ", patente=" + '\'' +
                ", esta refrigerado= " + estaRefrigerado+
                ", capacidad= " +capacidadKg+ "}";
    }
}
