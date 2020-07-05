package com.company;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Table {
    String _id;
    int numCols;
    int numDataRows;
    int numHeaderRows;
    List<Integer> numericColumns = new ArrayList<Integer>();
    float order;
    int pgId;
    String pgTitle;
    String sectionTitle;
    String tableCaption;
    List<TableRow> tableData = new ArrayList<TableRow>();
    List<TableRow> tableHeaders = new ArrayList<TableRow>();
    int tableId;
    //String textData = " ";
    List<Word> words = new ArrayList<Word>();

    public Table(JSONObject jsonTable) throws JSONException, IOException {

        this._id = jsonTable.getString("_id");
        this.numCols =  jsonTable.getInt("numCols");
        this.numDataRows = jsonTable.getInt("numDataRows");
        this.numHeaderRows = jsonTable.getInt("numHeaderRows");


        JSONArray jArray = jsonTable.getJSONArray("numericColumns");
        if (jArray != null) {
            for (int i=0; i<jArray.length(); i++){
                numericColumns.add(jArray.getInt(i));
            }
        }

        this.order = jsonTable.getLong("order");
        this.pgId = jsonTable.getInt("pgId");
        this.pgTitle = jsonTable.getString("pgTitle");
        this.sectionTitle = jsonTable.getString("sectionTitle");
        this.tableId = jsonTable.getInt("tableId");

        try {
            this.tableCaption = jsonTable.getString("tableCaption");
        }
        catch (Exception e){
            this.tableCaption = null;
        }



        jArray = jsonTable.getJSONArray("tableData");

        if (jArray != null) {
            for (int tableRow=0; tableRow<jArray.length(); tableRow++){
                JSONArray tableRowData = jArray.getJSONArray(tableRow);
                this.tableData.add(new TableRow(tableRowData,tableRow));
            }
        }

        jArray = jsonTable.getJSONArray("tableHeaders");

        if (jArray != null) {
            for (int tableRow=0; tableRow<jArray.length(); tableRow++){
                JSONArray tableRowData = jArray.getJSONArray(tableRow);
                this.tableHeaders.add(new TableRow(tableRowData, tableRow));
            }
        }

        for(TableRow row : tableData) {
          //  textData += row.textRow + " ";
            words.addAll(row.words);
        }
    }


    // Getter Methods

    public String get_id() {
        return _id;
    }

    public int getNumCols() {
        return numCols;
    }

    public int getNumDataRows() {
        return numDataRows;
    }

    public int getNumHeaderRows() {
        return numHeaderRows;
    }

    public float getOrder() {
        return order;
    }

    public int getPgId() {
        return pgId;
    }

    public String getPgTitle() {
        return pgTitle;
    }

    public String getSectionTitle() {
        return sectionTitle;
    }

    public String getTableCaption() {
        return tableCaption;
    }

    public int getTableId() {
        return tableId;
    }

    // Setter Methods

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setNumCols(int numCols) {
        this.numCols = numCols;
    }

    public void setNumDataRows(int numDataRows) {
        this.numDataRows = numDataRows;
    }

    public void setNumHeaderRows(int numHeaderRows) {
        this.numHeaderRows = numHeaderRows;
    }

    public void setOrder(float order) {
        this.order = order;
    }

    public void setPgId(int pgId) {
        this.pgId = pgId;
    }

    public void setPgTitle(String pgTitle) {
        this.pgTitle = pgTitle;
    }

    public void setSectionTitle(String sectionTitle) {
        this.sectionTitle = sectionTitle;
    }

    public void setTableCaption(String tableCaption) {
        this.tableCaption = tableCaption;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }
}


