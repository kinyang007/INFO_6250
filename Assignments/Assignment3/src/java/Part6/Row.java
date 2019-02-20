/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part6;

/**
 *
 * @author kinyang
 */
public class Row {
    private String[] row;
    private int rowSize;
    
    public Row(int size) {
        row = new String[size];
        rowSize = size;
    }

    public String[] getRow() {
        return row;
    }

    public void setRow(String[] row) {
        this.row = row;
    }

    public int getRowSize() {
        return rowSize;
    }

    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }
}
