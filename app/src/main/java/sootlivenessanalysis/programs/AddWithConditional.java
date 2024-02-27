package sootlivenessanalysis.programs;

import java.util.Random;

public class AddWithConditional {
    public static void main(String[] args) {
        int a = 0;
        // {a}
        int x = 1;
        // {a}
        int y = 2;
        // {y, a}
        x = 3;
        // {x,y,a}
        if (rand()==1)
            // {a,x}
            y = 1;
        else
            // {a,y}
            x = 1;
        // {a,x,y}
        int z = a + x + y;
        // {z}
        System.out.println(z);
    }

    private static int rand() {
        return new Random().nextInt(2);
    }
}
