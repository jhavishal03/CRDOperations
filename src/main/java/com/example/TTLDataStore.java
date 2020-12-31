package com.example;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TTLDataStore {
    private final HashMap<String,String> store=new HashMap<>();

    private final HashMap<String,Long> timestamps=new HashMap<>();
    private Long ttl;
    public TTLDataStore( long ttlValue) {
        this.ttl = (ttlValue);
    }



    public String get(String key) {
        JSONObject jsonObject=new JSONObject();

        String value = this.store.get(key);
        if(value==null)throw new UserNotFoundException("key Not present with Id"+key);

        if ( expired(key,value)) {
            System.out.println("Time Limit Exceeded :- Cannot Retrieve data");
            store.remove(key);
            timestamps.remove(key);
            return null;
        } else {
            jsonObject.put(key,value);
            return jsonObject.toString();
        }
    }

    private boolean expired(String key, String value) {
        return (System.currentTimeMillis() - timestamps.get(key)) > this.ttl;
    }
    // synchronized to avoid
    public synchronized void put(String key, String value) throws Exception {
        // try Catch is to check to control multiple access thread.
        try {
            Thread.sleep(400);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        if(key.length()>32)
            throw new Exception("Id character cannot exceed 32 size");
        if(store.containsKey(key)){
            throw new Exception("Value Stored with given Key");
        }
//        System.out.println("object insereted in storage with "+ key);
        timestamps.put(key, System.currentTimeMillis());
        store.put(key, value);
    }
    public void remove(String key) {
        timestamps.remove(key);
        store.remove(key);
    }
    public void getAll(){
        if(store.size()==0)System.out.println("No element in data storage ");
        JSONArray jsonArray=new JSONArray();
        for(Map.Entry it:store.entrySet()){
//        System.out.println(it.getKey()+" -> "+it.getValue());
            JSONObject jsonObject = new JSONObject(store);
            System.out.printf(jsonObject.toString());
        }
    }
}