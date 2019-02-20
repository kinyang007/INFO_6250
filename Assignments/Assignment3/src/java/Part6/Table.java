/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part6;

import java.util.*;

/**
 *
 * @author kinyang
 */
public class Table {
    private List<Row> table;
    
    public Table() {
        table = new ArrayList<>();
    }

    public List<Row> getTable() {
        return table;
    }

    public void setTable(List<Row> table) {
        this.table = table;
    }
    
    public void addRow(Row row) {
        table.add(row);
    }
}
