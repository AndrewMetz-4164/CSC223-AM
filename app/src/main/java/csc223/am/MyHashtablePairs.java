package csc223.am;
import java.util.ArrayList;


public class MyHashtablePairs {

    public class KeyVal{
        String key;
        int val;
        public KeyVal(String key, int val){
            this.key = key;
            this.val = val;
        }
    }

    int capacity;
    ArrayList<KeyVal>[] buckets;

    public MyHashtablePairs(int capacity){
        
        ArrayList<KeyVal>[] buckets = new ArrayList[capacity];
        for (int i=0; i<capacity;i++){
            this.buckets[i] = new ArrayList<KeyVal>();
        }

        this.capacity = capacity;
        this.buckets = buckets;
    }

    public boolean containsKey(String key){
        for (int i=0; i<this.buckets.length; i++){
            ArrayList<KeyVal> ourBucket = this.buckets[i];
            for (int j=0; j<ourBucket.size(); j++){
                if (ourBucket.get(j).key == key){
                    return true;
                }
            }
        }
        return false;
    }

    public int size(){
        int output = 0;
        for (int i=0; i<this.buckets.length; i++){
            ArrayList<KeyVal> thisBucket = this.buckets[i];
            for (int j=0; j<thisBucket.size(); j+=2){
                output++;
            }
        }
        return output;
    }

    public void put(String key, int value){
        int index = Math.abs(key.hashCode()) % this.capacity;
        KeyVal pair = new KeyVal(key, value);
        ArrayList<KeyVal> ourBucket = this.buckets[index];

        if (this.containsKey(key)){
            this.remove(key);
        }
        ourBucket.add(pair);
        this.buckets[index] = ourBucket;
    }

    public int get(String key){
        int index = Math.abs(key.hashCode()) % this.capacity;
        ArrayList<KeyVal> ourBucket = buckets[index];

        for (int i=0; i<ourBucket.size(); i+=2){
            if (ourBucket.get(i).key==key){
                return ourBucket.get(i).val;
            }
        }

        return -1;
    }

    public void remove(String key){
        int index = Math.abs(key.hashCode()) % this.capacity;
        ArrayList<KeyVal> ourBucket = buckets[index];

        for (int i=0; i<ourBucket.size(); i++){
            if (ourBucket.get(i).key == key){
                ourBucket.remove(i);
                break;
            }
        }
        this.buckets[index] = ourBucket;
    }

}
