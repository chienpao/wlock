package com.wlock.pao.wlock;
import java.util.Random;
/**
 * Created by pao on 12/23/14.
 */
public class RandomClass {

    static int getRandom(int count, int randomNumber){
        int ret = 0;
        Random random = new Random();
        return 1 + random.nextInt(randomNumber);
    }

}
