package gt.gob.sat.fiscalizacion.consulta360.util;

import java.io.Serializable;
import org.springframework.stereotype.Service;

/**
 * @author jaarevah esta funcion verifica si el nit ingresado es un nit valido o
 * no
 */
@Service("nitValidador")
public class NitValidador implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * constructor *
     */
    public NitValidador() {
        //No es necesario hacer inicializaciones
    }

    public String nitDepurado(String pNit) {
        String nit = pNit.replaceAll(" ", "");
        nit = nit.replaceAll("_", "");
        nit = nit.replaceAll("-", "");
        nit = nit.toUpperCase();

        return nit;
    }

    public int validarNit(String pNit) {
        int correcto = Constante.NIT_CORRECTO;
        String nitStr = fnit(pNit);
        String nit = "";
        StringBuilder buf = new StringBuilder();
        for (int j = 0; j < nitStr.length(); j++) {
            if (nitStr.charAt(j) != '-') {
                buf.append(nitStr.charAt(j));
            }
        }
        nit = buf.toString();

        if (nit.length() == 13) {
            correcto = 0;
        }
        if (!nitValido(nit)) {
            correcto = Constante.NIT_INCORRECTO;
        }
        if (nit.equals("00")) {
            correcto = Constante.NIT_GENERICO;
        }
        return correcto;

    }
    
    protected String fnit(String pCadena) {
        String nit = "";
        boolean significativo = false;
        char caracter = ' ';
        StringBuilder buf = new StringBuilder();
        for (int j = 0; j < pCadena.length(); j++) {
            caracter = pCadena.charAt(j);
            if (caracter != ' ') {
                if (caracter != '0') {
                    significativo = true;
                    buf.append(pCadena.charAt(j));
                    //nit += pCadena.charAt(j);
                } else if (significativo) {
                    //nit += pCadena.charAt(j);
                    buf.append(pCadena.charAt(j));
                }
            }
        }
        nit = buf.toString();
        return nit;
    }

    protected boolean nitValido(String pNit) {
        StringBuilder buf = new StringBuilder();
        String pNitTemp;
        if (pNit == null || pNit.isEmpty()) {
            return true;
        }

        pNitTemp = pNit.toUpperCase();
        String vNit = "";
        for (int c = 0; c < pNitTemp.length() - 1; c++) {
            //vNit += pNit.charAt(c);
            buf.append(pNitTemp.charAt(c));
        }
        vNit = buf.toString();
        double suma = 0D;
        int pos = pNitTemp.length();
        String caracter = "";
        int num = 0;
        for (int c = 0; c < vNit.length(); c++) {

            caracter = vNit.substring(c, c + 1);

            //si hay caracteres no numericos antes
            //del ultimo digito el nit es invalido
            if (!isNumeric(caracter)) {
                return false;
            }
            num = Integer.parseInt(caracter);
            suma += num * pos;
            pos -= 1;
        }
        String sTmp = "";
        if (Double.isNaN(suma)) {
            return false;
        }
        int tmp = (int) (11 - (suma - Math.floor(suma / 11) * 11));
        if (tmp == 10) {
            sTmp = "K";
        } else {
            if (tmp == 11) {
                sTmp = "0";
            } else {
                sTmp = String.valueOf(tmp);
            }
        }
        vNit = vNit + sTmp;
        if (pNitTemp.compareToIgnoreCase(vNit) == 0) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isNumeric(String pCadena) {
        try {
            Integer.parseInt(pCadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

}
