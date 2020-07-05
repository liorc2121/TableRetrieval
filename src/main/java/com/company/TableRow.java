package com.company;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TableRow {
    private int rowId;
    List<TableCell> tableRowCell = new ArrayList<TableCell>();
   // String textRow = " ";
    List<Word> words = new ArrayList<Word>();

    public TableRow() {
    }

    public TableRow(JSONArray tableRowData, int tableRow) throws JSONException, IOException {
        this.rowId = tableRow;

        for (int i = 0; i < tableRowData.length(); i++) {
            JSONObject a = tableRowData.getJSONObject(i);
            this.tableRowCell.add(new TableCell(a, i));
        }
        for (TableCell cell : tableRowCell) {
            List<String> tokens = Util.fullAnalyze(cell.text);

            for (String token: tokens) {
                words.add(new Word(token, cell.cellLocation));
            }
        }
    }



    public int realDataLocation() {
        return 1;
    }

    public int getRowId() {
        return rowId;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
    }
}
