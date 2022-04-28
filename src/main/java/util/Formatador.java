package util;

import java.sql.Time;
import java.text.*;
import java.util.Date;
import static util.ValueUtil.*;

public class Formatador {

    public static String formataCep(String cep) {
        if (isEmpty(cep)) {
            return VAZIO;
        }
        if (cep.length() != 8) {
            return cep;
        }
        StringBuilder cepFormatado = new StringBuilder(cep);
        cepFormatado.insert(2, ".");
        cepFormatado.insert(6, "-");
        return cepFormatado.toString();
    }

    public static String formataTelefone(String fone) {
        if (isEmpty(fone)) {
            return VAZIO;
        }
        if (fone.length() != 11) {
            return fone;
        }
        StringBuilder foneFormatado = new StringBuilder(fone);
        foneFormatado.insert(0, "(");
        foneFormatado.insert(3, ")");
        foneFormatado.insert(8, "-");
        return foneFormatado.toString();
    }

    public static String formataCpf(String cpf) {
        if (isEmpty(cpf)) {
            return VAZIO;
        }
        if (cpf.length() != 11) {
            return cpf;
        }
        StringBuilder cpfFormatado = new StringBuilder(cpf);
        cpfFormatado.insert(3, ".");
        cpfFormatado.insert(7, ".");
        cpfFormatado.insert(11, "-");
        return cpfFormatado.toString();
    }

    public static String formataDataPadrao(Date data) {
        return formataDataPadrao(data, false);
    }

    public static String formataDataPadrao(Date data, boolean isSugerirDataAtual) {
        if (isNull(data)) {
            if (isSugerirDataAtual) {
                data = new Date();
            } else {
                return VAZIO;
            }
        }
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(data);
    }

    public static String formatHora(Time time) {
        return formatHora(time, false);
    }

    public static String formatHora(Time time, boolean isSugerirDataAtual) {
        if (isNull(time)) {
            if (isSugerirDataAtual) {
                time = new Time(new Date().getTime());
            } else {
                return VAZIO;
            }
        }
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(time);
    }

    public static Date transformaDataFormatadaEmObjeto(String data) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return format.parse(data);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String formataDouble(Double valor) {
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(valor);
    }
}
