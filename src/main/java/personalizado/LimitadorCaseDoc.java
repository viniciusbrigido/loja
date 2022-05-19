package personalizado;

import lombok.Getter;
import lombok.Setter;
import javax.swing.text.*;

public class LimitadorCaseDoc extends PlainDocument {

    @Getter @Setter
    private int maxLength;

    public LimitadorCaseDoc(int maxLength) {
        setMaxLength(maxLength);
    }

    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (str == null) {
            return;
        }
        int length = (getMaxLength() > 0) ? Math.min(str.length(), getMaxLength() - getLength()) : str.length();
        super.insertString(offs, str.substring(0, length), a);
    }
}
