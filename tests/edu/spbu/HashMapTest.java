package edu.spbu;

import main.CuckooHashMap;
import org.junit.Test;

import java.util.Random;

public class HashMapTest {
    @Test
    public void Test(){
        Random random = new Random(1);
        final int length=100;
        double test[] = new double[length];
        CuckooHashMap map = new CuckooHashMap<Integer, Double>(201);

        for (int i = 0; i < length; i++) {
            test[i] = random.nextDouble();
            map.put(i, test[i]);
        }

        for (int i = 0; i < length; i++){
            assert((double)map.get(i) == test[i]);
        }

    }
}
