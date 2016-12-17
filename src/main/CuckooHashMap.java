package main;

import main.Bucket;

/**
 * Created by Evgueni on 17.12.2016.
 */
public class CuckooHashMap<K,V> {
    private int maxsize;
    Bucket<K,V>[] map1;
    Bucket<K,V>[] map2;

    public CuckooHashMap (int n){
        maxsize = n / 2 + 1;
        map1 = new Bucket[maxsize];
        map2 = new Bucket[maxsize];
    }

    public CuckooHashMap () {
        maxsize = 10000;
        map1 = new Bucket[maxsize];
        map2 = new Bucket[maxsize];
    }

    public int hash1 (K key){
        return(key.hashCode() % maxsize);
    }

    public int hash2 (K key){
        return((key.hashCode() / 2381 + 3541) % maxsize);
    }

    public V get (K key){
        int i = hash1(key);
        if (map1[i] != null)
            if (key.equals(map1[i].getKey()))
                return map1[i].getValue();
        else {
            int j = hash2(key);
            if (map2[j] != null)
                if (key.equals(map2[j].getKey()))
                    return map2[j].getValue();
            }
        return null;
    }

    public void put (K key, V value){
        int i = hash1(key);
        int j = hash2(key);
        Bucket t = new Bucket(key, value);
        Bucket temp = new Bucket(key, value);
        boolean queue = true;
        boolean f = true;

        while (f){
            if ((map1[i] != null) && (map2[j] != null)) {
                K k1 = map1[i].getKey();
                K k2 = map2[j].getKey();
                V v1 = map1[i].getValue();
                V v2 = map2[j].getValue();
                if (queue) {
                    map1[i] = temp;
                    temp = new Bucket(k1, v1);
                    j = hash2(k1);
                    queue = !queue;
                }
                else {
                    map2[j] = temp;
                    temp = new Bucket(k2, v2);
                    i = hash1(k2);
                    queue = !queue;
                }
            }
            else
                if (map1[i] == null){
                    map1[i] = temp;
                    f = false;
                }
                else {
                    map2[j] = temp;
                    f = false;
                }
        }
    }
}
