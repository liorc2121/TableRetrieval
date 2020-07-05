package com.company;

import org.json.JSONException;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        LocalDateTime start = LocalDateTime.now();

        try {
            TableReader.ReadTables("data\\tables.json.gz", 1);
           // Map<String, List<WordInTable>> something = TableReader.ReadIndexing("IndexWords.json");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {


            LocalDateTime end = LocalDateTime.now();
            Duration timeElapsed = Duration.between(start, end);


            System.out.println("Time taken: "+ timeElapsed.toMinutes() +" minutes " +
                                               timeElapsed.toSeconds() +" seconds");
        }
    }
}
