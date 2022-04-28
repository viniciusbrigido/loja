package personalizado;

import static util.Cores.*;

public class JButtonPersonalizado extends JButtonDefault {

    public JButtonPersonalizado() {
        super(BLUE, DARK_BLUE);
    }

    public JButtonPersonalizado(String text) {
        super(BLUE, DARK_BLUE);
        setText(text);
    }
}
