package com.coast.table;

import com.coast.util.DiscountUtil;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author Coast
 */
public class DiscountTableListener implements TableModelListener {

    @Override
    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel) e.getSource();
        String columnName = model.getColumnName(column);
        Object data = model.getValueAt(row, column);
        // Do something with the data...
        //debug
        System.out.printf("data[%d][%d] = %s\n",row,column,data);
        DiscountUtil discountUtil = new DiscountUtil();
        discountUtil.save(row, column, data);
        //save to xml
    }

}
