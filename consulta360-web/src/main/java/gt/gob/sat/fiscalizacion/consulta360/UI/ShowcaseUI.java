/**
 * Superintendencia de Administracion Tributaria (SAT) Gerencia de Informatica
 * Desarrollo de Sistemas
 */
package gt.gob.sat.fiscalizacion.consulta360.UI;


import java.util.ArrayList;
import java.util.List;
import org.primefaces.event.FileUploadEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("view")
public class ShowcaseUI extends AbstractUI {
    private String codigo;
    private List<String> table;
    private boolean activo;
    private String oneRadio;
    private String step="2";
  
    /**
     * @return the table
     */
    public List<String> getTable() {
        table = new ArrayList<>();
        for (int row = 0; row < 20; row++) {
            table.add(row, " Fila: " + (row + 1));
        }
        return this.table;
    }
    
    public void generarMensajes(){
        infoMsg("Mensaje informativo");
    }
    
    public void handleFileUpload(FileUploadEvent event){
        infoMsg(event.getFile().getFileName()+" fue cargado");
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getOneRadio() {
        return oneRadio;
    }

    public void setOneRadio(String oneRadio) {
        this.oneRadio = oneRadio;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }
}