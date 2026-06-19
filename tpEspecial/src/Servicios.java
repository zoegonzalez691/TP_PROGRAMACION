import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Servicios {

    private List<Camion> camiones;
    private Map<String, Paquete> grafoPaquetes; 
    private List<Paquete> paquetesConAlimentos;
    private List<Paquete> paquetesSinAlimentos;

    //O(n+m) donde n son la cantidad de camiones y m son la cantidad de paquetes
    public Servicios(String pathCamiones, String pathPaquetes) {
        this.camiones = new ArrayList<>();
        this.grafoPaquetes = new HashMap<>();
        this.paquetesConAlimentos= new ArrayList<>();
        this.paquetesSinAlimentos= new ArrayList<>();

        cargarCamiones(pathCamiones);
        cargarPaquetes(pathPaquetes);
    }

    //O(n) donde n son la cantidad de lineas(camiones) a procesar
    private void cargarCamiones(String path) {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(path), StandardCharsets.UTF_8)) {
            CsvToBean<Camion> csvToBean = new CsvToBeanBuilder<Camion>(br)
                    .withType(Camion.class)
                    .withSeparator(';')
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            this.camiones = csvToBean.parse();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //O(m) donde m son la cantidad de lineas(paquetes a procesar)
    private void cargarPaquetes(String path) {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(path), StandardCharsets.UTF_8)) {
            CsvToBean<Paquete> csvToBean = new CsvToBeanBuilder<Paquete>(br)
                    .withType(Paquete.class)
                    .withSeparator(';')
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            
            Iterator<Paquete> paqueteIterator = csvToBean.iterator();
            while (paqueteIterator.hasNext()) {
                Paquete p = paqueteIterator.next();
                this.grafoPaquetes.put(p.getCodigoPaquete(), p);
                if(p.contieneAlimentos()==true){
                    this.paquetesConAlimentos.add(p);
                }
                if(p.contieneAlimentos()==false){
                    this.paquetesSinAlimentos.add(p);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //O(1) ya que necesito un solo acceso con la clave que me pasan por parametro
    public Paquete servicio1(String codigoPaquete) { 
        Paquete paq= grafoPaquetes.get(codigoPaquete);
    
        return paq;
    }

    //O(1) ya que solo necesito retornar una de mis dos listas dependiendo que es lo que me piden
    public List<Paquete> servicio2(boolean contieneAlimentos){
        if(contieneAlimentos==true){
            return paquetesConAlimentos;
        }
        else{
            return paquetesSinAlimentos;
        }
    }
    
    //O(n) donde n son la cantidad de paquetes que tengo en el grafo
    public List<Paquete> servicio3(int urgenciaMinima, int urgenciaMaxima){
          List<Paquete> resultado= new ArrayList<Paquete>(); 
          for(Paquete p: grafoPaquetes.values()){
            if((p.getNivelUrgencia()<= urgenciaMaxima)&&
                (p.getNivelUrgencia()>=urgenciaMinima)){
                    resultado.add(p); 

            }
          }
          return resultado;
    }

    public List<Camion> getCamiones() {
       return camiones;
    }
     
    public List<Paquete> getPaquetes(){
        List<Paquete> paqLista= new ArrayList<>();
        paqLista.addAll(grafoPaquetes.values());

        return paqLista;
    }
    
}