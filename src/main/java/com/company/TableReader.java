package com.company;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.GZIPInputStream;


public class TableReader {

    public static void ReadTables(String tableFile) throws java.io.IOException {
        GZIPInputStream in = new GZIPInputStream(new FileInputStream(tableFile));
        Reader decoder = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(decoder);

        int countTable = 0;
        Map<String, List<WordInTable>> wordsTable = new HashMap<String, List<WordInTable>>();
        String allText = "";

        String line;

        //read json convert to Table
        while ((line = br.readLine()) != null && countTable < 50) {
            //System.out.println(line);
            try {
                JSONObject jsonData = new JSONObject(line);
                Table t = new Table(jsonData);
                allText = t.textData + System.lineSeparator();

                for(Word word : t.words) {
                    List<WordInTable> temp = wordsTable.get(word.stem) == null ?
                                                                            new ArrayList<WordInTable>() :
                                                                            wordsTable.get(word.stem);
                    temp.add(new WordInTable(t._id, word.wordCol));
                    wordsTable.put(word.stem, temp);
                }

                countTable++;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                break;
            }
        }

        WriteJsonToFile("IndexWords.json" ,new Gson().toJson(wordsTable));
    }

    public static void WriteJsonToFile(String fileName, String json){

        try (FileWriter file = new FileWriter(fileName)) {

            file.write(json);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, List<WordInTable>> ReadIndexing(String fileName) throws IOException, JSONException {
        Map<String, List<WordInTable>> wordsTable = new HashMap<String, List<WordInTable>>();
        JSONObject jsonData = new JSONObject(Files.readString(Paths.get(fileName)));

        return toMap(jsonData);
    }

    public static Map<String, List<WordInTable>> toMap(JSONObject object) throws JSONException {
        Map<String, List<WordInTable>> map = new HashMap<String, List<WordInTable>>();

        Iterator<String> keysItr = object.keys();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if(value instanceof JSONArray) {
                List<WordInTable> value1 = toListWordInTable((JSONArray) value);
                map.put(key, value1);
            }

        }
        return map;
    }

    public static List<WordInTable> toListWordInTable(JSONArray array) throws JSONException {
        List<WordInTable> list = new ArrayList<WordInTable>();
        for(int i = 0; i < array.length(); i++) {
            WordInTable value = new WordInTable(array.getJSONObject(i));
            list.add(value);
        }

        return list;
    }
}
