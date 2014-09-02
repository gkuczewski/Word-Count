package wordcount;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.*;

public class WordCount {

    private static java.lang.String[] processInputFile(String fileName) 
            throws IOException{
        String clean ="";
        //feels like im in high schoool again... 
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        try{
        StringBuilder builds = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null){
            builds.append(line);
            builds.append('\n');
            //System.out.println(line);
           // line = reader.readLine();
        }
        clean = builds.toString();
        }
        finally{
            reader.close();
        }
        clean = clean.toLowerCase().replaceAll("\\d", "")
                .replaceAll("\\p{Punct}","").replaceAll("-", " ");
       // System.out.println(clean);
        String[] cleansed = clean.split("\\s+");
        return cleansed;
    }
    private static long displayMap(java.util.Map<String,Integer> map){
        long startTime = System.currentTimeMillis();
        
        // iterate that map 
        for( Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator(); 
                it.hasNext();){
            Entry<String, Integer> next = it.next();
            String key = next.getKey();
            System.out.println(key+ "   "+next.getValue());
        }
        
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
    private static long buildMap(String[] words, java.util.Map<String,Integer> map){
        long startTime = System.currentTimeMillis();
        
            for(String word : words){
             //   Integer freq = map.get(word);
                map.put(word, map.containsKey(word) ?  map.get(word) + 1 : 1);
            }
        
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
    
    public static void main(String[] args) throws IOException{
        Scanner input = new Scanner("/home/g/DoI.txt");
        String file = input.next();
        processInputFile(file);
        
        // hashmap!
        Map<String, Integer>hash = new HashMap<String, Integer>();
        //buildMap(processInputFile(file),hash);
        System.err.println("HashMap build time: "+buildMap(processInputFile(file),hash)+" ms.");
        System.out.println("HashMap based Map...");
        //displayMap(hash);
        System.err.println("HashMap display time: "+displayMap(hash)+" ms.");
        System.out.println("==========================");
        
	// treemap!
        Map<String, Integer>tree = new TreeMap<String, Integer>();
	//buildMap(processInputFile(file),tree);
        System.err.println("TreeMap build time: "+buildMap(processInputFile(file),tree)+" ms.");
        System.out.println("\nTreeMap based Map...");
        //displayMap(tree);
        System.err.println("TreeMap display time: "+displayMap(tree)+" ms.");
        System.out.println("==========================");
        
        //usage map!
        ArrayList<Map.Entry<String, Integer>> wordList = 
                new ArrayList<>(hash.entrySet());
        System.out.println("\nUsage based Map...");
        
    }
}
