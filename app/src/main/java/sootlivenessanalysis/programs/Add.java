package sootlivenessanalysis.programs;

public class Add {
    public static void main(String[] args) {
        int a = 0;
        // {a}
        int x = 1;
        // {a}
        int y = 2;
        // {y,a}
        x = 3;
        // {x,y,a}
        int z = a + x + y;
        // {z}
        System.out.println(z);
    }
}
