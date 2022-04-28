package util;

import javax.swing.*;
import java.awt.*;

public class FocusUtil {

    public static DefaultFocusManager getDefaultFocusManager() {
        return ((DefaultFocusManager) DefaultFocusManager.getCurrentManager());
    }

    public static Component getFocusNextComponent(final Component component) {
        focusNextComponent(component);

        final Container container = component.getFocusCycleRootAncestor();
        final FocusTraversalPolicy policy = container.getFocusTraversalPolicy();

        return policy.getComponentAfter(container, component);
    }

    public static void focusNextComponent(Component aComponent) {
        getDefaultFocusManager().focusNextComponent(aComponent);
    }

    public static Component getFocusPreviousComponent(final Component component) {
        focusPreviousComponent(component);

        final Container container = component.getFocusCycleRootAncestor();
        final FocusTraversalPolicy policy = container.getFocusTraversalPolicy();

        return policy.getComponentBefore(container, component);
    }

    public static void focusPreviousComponent(Component aComponent) {
        getDefaultFocusManager().focusPreviousComponent(aComponent);
    }

    public static Component getFocusOwner() {
        return getDefaultFocusManager().getFocusOwner();
    }
}