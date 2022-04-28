package util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class ValueUtil {

    public static final Integer ZERO = Integer.valueOf(0);
    public static final Double ZEROD = Double.valueOf(0.0);
    public static final String VAZIO = "";

    public static boolean isNull(Object value) {
        return value == null;
    }

    public static boolean isNotNull(Object value) {
        return !isNull(value);
    }

    public static boolean isEmpty(final String value) {
        return isNull(value) || value.trim().isEmpty();
    }

    public static boolean isNotEmpty(final String value) {
        return !isEmpty(value);
    }

    public static boolean isEmpty(final StringBuilder value) {
        return isNull(value) || isEmpty(value.toString());
    }

    public static boolean isEmpty(final Double value) {
        return isNull(value) || value.equals(Double.valueOf(ZERO));
    }

    public static boolean isEmpty(final Integer value) {
        return isNull(value) || value.equals(Integer.valueOf(ZERO));
    }

    public static boolean isNotEmpty(final Integer value) {
        return !isEmpty(value);
    }

    public static boolean isEmpty(final BigDecimal value) {
        return isNull(value) || value.equals(BigDecimal.valueOf(ZEROD)) || value.equals(BigDecimal.valueOf(ZERO));
    }

    public static boolean isEmpty(final Float value) {
        return isNull(value) || value.equals(Float.valueOf(ZERO));
    }

    public static String format(float val) {
        DecimalFormat df = new DecimalFormat("#0.00");
        if (isNull(val)) {
            val = 0F;
        }
        return df.format(val);
    }

    public static double paramToDouble(Object parametro) {
        if (parametro == null) {
            return 0;
        }
        if (parametro instanceof String) {
            final String parametroString = ((String) parametro).trim();
            if (parametroString.isEmpty()) {
                return 0;
            }
            return Double.parseDouble(parametroString.replaceAll(",", "."));
        }
        if (parametro instanceof Double) {
            return ((Double) parametro).doubleValue();
        }
        if (parametro instanceof Integer) {
            return ((Integer) parametro).doubleValue();
        }
        if (parametro instanceof Float) {
            return ((Float) parametro).doubleValue();
        }
        if (parametro instanceof Long) {
            return ((Long) parametro).doubleValue();
        }
        throw new IllegalArgumentException(
                "Tipo: [" + parametro.getClass().getSimpleName() + "] parâmetro inválido para conversão!");
    }

    public static Integer asInteger(String value) {
        return isEmpty(value) ? ZERO : Integer.parseInt(value);
    }

    public static boolean isNumerico(String conteudo) {
        String numeros = "0123456789";
        if (conteudo != null && !VAZIO.equals(conteudo)) {
            char[] var2 = conteudo.toCharArray();
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                char posicao = var2[var4];
                if (numeros.indexOf(String.valueOf(posicao)) == -1) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }
}
