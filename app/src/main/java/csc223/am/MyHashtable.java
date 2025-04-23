package csc223.am;
import java.util.ArrayList;


public class MyHashtable {

    int capacity;
    ArrayList<Integer>[] buckets;

    // public class KeyValue {
    //     String key;
    //     int value;
    //     KeyValue next;
    //     public KeyValue(String key, int value, KeyValue next){
    //         this.key = key;
    //         this.value = value;
    //         this.next = next;
    //     }
    // }

    public MyHashtable(int capacity){
        
        //initializing

        ArrayList<Integer>[] buckets = new ArrayList[capacity];
        for (int i=0; i<capacity;i++){
            this.buckets[i] = new ArrayList<Integer>();
        }
       
        this.capacity = capacity;
        this.buckets = buckets;

        
    }

    public boolean containsKey(String key){
        int index = Math.abs(key.hashCode()) % this.capacity;
        ArrayList<Integer> ourBucket = buckets[index];

        for (int i=0; i<ourBucket.size();i=i+2){
            if (ourBucket.get(i) == key.hashCode()){
                return true;
            }
        }
        return false;
    }

    public int size(){
        int output = 0;
        for (int i=0; i<this.buckets.length; i++){
            ArrayList<Integer> thisBucket = this.buckets[i];
            for (int j=0; j<thisBucket.size(); j+=2){
                output++;
            }

        }

        return output;
    }

    public void put(String key, int value){
        int index = Math.abs(key.hashCode()) % this.capacity;
        int hashCode = key.hashCode();
        ArrayList<Integer> ourBucket = buckets[index];

        if (this.containsKey(key)){
            this.remove(key);
        }

        ourBucket.add(hashCode);
        ourBucket.add(value);

        this.buckets[index] = ourBucket;


    }

    public int get(String key){
        int index = Math.abs(key.hashCode()) % this.capacity;
        int hashCode = key.hashCode();
        ArrayList<Integer> ourBucket = buckets[index];

        for (int i=0; i<ourBucket.size(), i+=2){
            if (ourBucket.get(i)==hashCode){
                //twice for key, value
                return ourBucket.get(i+1);
            }
        }

        return -1;
    }
    public void remove(String key){
        int index = Math.abs(key.hashCode()) % this.capacity;
        int hashCode = key.hashCode();
        ArrayList<Integer> ourBucket = buckets[index];

        for (int i=0; i<ourBucket.size(), i+=2){
            if (ourBucket.get(i)==hashCode){
                //twice for key, value
                ourBucket.remove(i);
                ourBucket.remove(i);
                break;
            }
        }

        this.buckets[index] = ourBucket;
    }

}
