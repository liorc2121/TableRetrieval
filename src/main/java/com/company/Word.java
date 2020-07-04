package com.company;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

public class Word {
    String word;
    String stem;
    //    float vec;
    int wordCol;


    public Word(String word, String stem, int wordCol) {
        this.word = word;
        this.stem = stem;
        this.wordCol = wordCol;
    }
    public Word(String stem, int wordCol) {
        this.stem = stem;
        this.wordCol = wordCol;}
    }


