import java.io.IOException;
import Classes.Calculator;
import Classes.ArraySort;

class program{
    public static void main(String[] args) throws IOException {

        Calculator calc = new Calculator();
        calc.startCalc();
        ArraySort sorter = new ArraySort();
        int[] array = {5, 7, 0, 1, 4, 5, 2, 0, 10, 4};
        sorter.Sort(array);

    }
}