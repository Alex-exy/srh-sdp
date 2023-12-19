package de.srh.library.ui.borrowreturn;

import de.srh.library.entity.Borrow;

import javax.swing.*;
import java.awt.*;

public class BorrowListCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value instanceof Borrow) {
            Borrow borrow = (Borrow) value;
            String displayText = "<html><b>" + borrow.getBookName() + "</b>";
            if(borrow.getBorrowStatus() == 'D'){
                displayText += " <font color='red'>(Delayed)</font>";
            }
            displayText += "</html>";
            return super.getListCellRendererComponent(list, displayText, index, isSelected, cellHasFocus);
        } else {
            return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        }
    }
}
