package com.company;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TableCell {
    int cellLocation;
    String text;
    private boolean isNumeric;
    private boolean containsData;

    public TableCell(JSONObject jsonCell, int i) throws JSONException {
        this.cellLocation = i;
        this.text = jsonCell.getString("text");
        this.isNumeric = jsonCell.getBoolean("isNumeric");
        containsData = !(this.text == null || text.isEmpty());
    }


    // Getter Methods
/*
    public int getCellID() {
        return cellID;
    }

    public String getText() {
        return text;
    }

    public String getTdHtmlString() {
        return tdHtmlString;
    }

    public int getSubtableID() {
        return subtableID;
    }

    public boolean getIsNumeric() {
        return isNumeric;
    }

    // Setter Methods

    public void setCellID( int cellID ) {
        this.cellID = cellID;
    }

    public void setText( String text ) {
        this.text = text;
    }

    public void setTdHtmlString( String tdHtmlString ) {
        this.tdHtmlString = tdHtmlString;
    }

    public void setSubtableID( int subtableID ) {
        this.subtableID = subtableID;
    }

    public void setIsNumeric( boolean isNumeric ) {
        this.isNumeric = isNumeric;
    }*/
}
