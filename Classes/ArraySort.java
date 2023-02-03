package Classes;

import java.io.IOException;
import java.util.logging.*;

public class ArraySort {

    Logger _log;
    
    public int[] Sort(int[] array) throws IOException{

        initLogger();

        for (int i = array.length - 1; i >= 1; i--) {

            for (int j = 0; j < i; j++) {

                if(array[j] > array[j + 1]){

                    _log.log(Level.INFO, array[j] + " >>> " + array[j + 1]);

                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    
                }
            }
        }

        printer(array);
        return array;

    }

    private void printer(int[] array) {

        for (int i = 0; i < array.length; i++) {

            System.out.print(array[i] + " ");

        }

        System.out.println("----Окончание вывода массива----");
    }

    private void initLogger() throws IOException {

        _log = Logger.getLogger(Calculator.class.getName());

        FileHandler fh = new FileHandler("log/logArray.xml");
        fh.setEncoding("UTF-8");
        _log.addHandler(fh);

        XMLFormatter xml = new XMLFormatter();
        fh.setFormatter(xml);
    }
}
