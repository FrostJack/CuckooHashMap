package main;

/**
 * Created by Evgueni on 17.12.2016.
 */
public class Bucket <K, V> {
    private K key;
    private V value;

    public Bucket(K key, V value){
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
