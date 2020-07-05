package com.company;

import com.google.gson.Gson;
import org.bytedeco.javacv.FrameFilter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.zip.GZIPInputStream;



public class TableReader {

    public static void ReadTables(String tableFile,int batch_number) throws java.io.IOException, JSONException {
        GZIPInputStream in = new GZIPInputStream(new FileInputStream(tableFile));
        Reader decoder = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(decoder);

        int table_count = 0;
        Map<String, List<WordInTable>> wordsTable = ReadIndexing("IndexWords.json");
        //String allText = "";

        String line;

        while ((line = br.readLine()) != null) {
            try {
                if (table_count > 50 * batch_number) {
                    break;
                }else if (table_count < 50 * (batch_number - 1)) {
                } else {
                    JSONObject jsonData = new JSONObject(line);
                    Table t = new Table(jsonData);
                  //  allText = t.textData + System.lineSeparator();

                    for (Word word : t.words) {
                        List<WordInTable> temp = wordsTable.get(word.stem) == null ?
                                new ArrayList<WordInTable>() :
                                wordsTable.get(word.stem);
                        WordInTable wordToAdd = new WordInTable(t._id, word.wordCol);

                        if (temp.contains(wordToAdd))
                            temp.get(temp.indexOf(wordToAdd)).count++;
                        else {
                            temp.add(wordToAdd);
                            wordsTable.put(word.stem, temp);
                        }
                    }
                }

                table_count++;

                if (table_count % 5000 == 0)
                    System.out.println("worked on " + table_count + " tables - at: " + LocalDateTime.now().toLocalTime());
            }
            catch (Exception e)
            {
                e.printStackTrace();
                break;
            }
        }

        System.out.println("number of tables " + table_count);
        String json = new Gson().toJson(wordsTable);
        WriteToFile("IndexWords.json" , json);
      //  WriteToFile("AllText.txt" , allText);
    }

    public static void WriteToFile(String fileName, String json){
        try {
            boolean a = Files.deleteIfExists(Paths.get(fileName)) ;
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter file = new FileWriter(fileName)) {

            file.write(json);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, List<WordInTable>> ReadIndexing(String fileName) {
        try {
            Map<String, List<WordInTable>> wordsTable = new HashMap<String, List<WordInTable>>();
            JSONObject jsonData = new JSONObject(Files.readString(Paths.get(fileName)));
            return toMap(jsonData);
        }

        catch (JSONException | IOException e) {
            return new HashMap<String, List<WordInTable>>();
        }
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
