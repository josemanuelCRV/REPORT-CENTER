package com.labs.josemanuel.reportcenter.Model;

import com.labs.josemanuel.reportcenter.R;

import java.util.Random;

/**
 * Created by Usuario on 03/06/2016.
 */
public class RandomHero {
    String name;
    int resourceId;
    public static String[] names = {"Chuck Norris", "Kim Jong Un II", "Arnold Schwarzenegger", "Mariano Rajoy", "Torrente"};

    public static int[] photoResourceId = {R.drawable.chuck_thumbnail, R.drawable.kim_thumbnail,
            R.drawable.arnold_thumbnail, R.drawable.rajoy_thumbnail, R.drawable.torrente_thumbnail};

    public static Random random = new Random();

    public RandomHero(String name, int resourceId) {
        this.name = name;
        this.resourceId = resourceId;
    }

    public static RandomHero getHero(){
        int numAleatorio = random.nextInt(names.length);
        return new RandomHero(names[numAleatorio],photoResourceId[numAleatorio]);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}

