package com.company;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class WordInTable implements Serializable {

    String tableId;
    int colId;
    int count = 1;

    public WordInTable(String tableId, int wordCol) {
        this.tableId = tableId;
        this.colId = wordCol;
    }

    public WordInTable(JSONObject a) throws JSONException {
        this.tableId = a.getString("tableId");
        this.colId = a.getInt("colId");
        this.count = a.getInt("count");
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != WordInTable.class) return false;
        else
            return ((WordInTable) obj).colId == this.colId &&
                    ((WordInTable) obj).tableId.equals(this.tableId);
    }

    @Override
    public String toString() {
        return "{\"tableId\": " + tableId + ", \"colId\": " + colId + "}";
    }


}
