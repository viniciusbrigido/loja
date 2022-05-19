package personalizado;

import lombok.Getter;
import lombok.Setter;
import javax.swing.text.*;

public class UpperCaseDoc extends PlainDocument {
    @Getter @Setter
    private int maxLength;

    public UpperCaseDoc(int maxLength) {
        setMaxLength(maxLength);
    }

    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (str == null) {
            return;
        }
        char[] upper = str.toCharArray();
        int length = (getMaxLength() > 0) ? Math.min(str.length(), getMaxLength() - getLength()) : str.length();
        for (int i = 0; i < upper.length; i++) {
            upper[i] = Character.toUpperCase(upper[i]);
        }
        super.insertString(offs, new String(upper).substring(0, length), a);
    }
}
