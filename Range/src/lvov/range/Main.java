package lvov.range;

public class Main {
    public static void main(String[] args) {

        Range range1 = new Range(1.1, 5.6);
        Range range2 = new Range(3.4, 7);

        double number = 2.2;

        System.out.println("Длина диапазона = " + range1.getLength());

        if(range1.isInside(number)){
            System.out.println("Число принадлежит диапазону");
        }else {
            System.out.println("Число не принадлежит диапазону");
        }


    }
}
