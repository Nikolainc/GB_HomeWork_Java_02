package Classes;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.*;


public class Calculator {

    private Logger _log;
    private String _HELP = "Что бы закрыть калькулятор введите в консоль 'Q' или 'q'\nДля обнуления результатов используйте 'c' или 'C' ";
    private String _ERRORINPUT = "Ошибка ввода";
    private String _ERRORNULL = "Деление на 0";
    private Scanner _input = new Scanner(System.in);
    private Double _firstnumber;
    private Double _secondnumber;
    private Double _result;
    private boolean _working = true;

    public void startCalc() throws IOException {

        initLogger();
        System.out.println("Калькулятор запущен");
        _log.log(Level.INFO, _HELP);
        System.out.println(_HELP);
        _result = inputNumber();

        while (_working) {

            _firstnumber = _result;

            switch (operatorChoice()) {
                
                case sum:

                    _secondnumber = inputNumber();
                    _result = sum(_firstnumber, _secondnumber);
                    _log.log(Level.INFO, _result.toString());
                    break;

                case sub:

                    _secondnumber = inputNumber();
                    _result = sub(_firstnumber, _secondnumber);
                    _log.log(Level.INFO, _result.toString());
                    break;

                case mult:

                    _secondnumber = inputNumber();
                    _result = mult(_firstnumber, _secondnumber);
                    _log.log(Level.INFO, _result.toString());
                    break;

                case div:

                    _secondnumber = inputNumber();

                    if (_secondnumber == 0) {

                        System.out.println(_ERRORNULL);
                        _log.log(Level.WARNING, _ERRORNULL);
                        break;

                    }

                    _result = div(_firstnumber, _secondnumber);
                    _log.log(Level.INFO, _result.toString());
                    break;

                case deg:

                    _secondnumber = inputNumber();
                    _result = deg(_firstnumber, _secondnumber);
                    _log.log(Level.INFO, _result.toString());
                    break;

                case clear:

                    _result = 0D;
                    _log.log(Level.INFO, "Очистка памяти");
                    break;
                    
                case exit:
                    _working = false;
                    _log.log(Level.INFO, "Выход");
                    break;

                case err:
                default:

                    System.out.println(_ERRORINPUT);
                    _log.log(Level.WARNING, _ERRORINPUT);

            }

            if (!_working){

                continue;

            }
                
            System.out.println("Результат: " + _result);

        }

        System.out.println("Калькулятор закрыт");

    }

    private Double inputNumber() {

        Double result;

        while (_working) {

            System.out.println("Ввод числа: ");

            if(_input.hasNext()){

                var flag = _input.hasNextDouble();

                if (!flag) {

                    typeOfOperation operation = operatorChoice(_input.next().charAt(0));

                    if (operation == typeOfOperation.exit) {
                        _working = false;
                    }

                    if (operation == typeOfOperation.clear) {
                        result = 0D;
                        return result;
                    }

                    if (_working)

                        System.out.println(_ERRORINPUT);
                        _log.log(Level.WARNING, _ERRORINPUT);

                    continue;

                }
            }
            
            result = _input.nextDouble();
            _log.log(Level.INFO, result.toString());
            return result;
            
        }

        return 0D;

    }

    private typeOfOperation operatorChoice() {

        char oper;

        while (_working) {

            System.out.println("Введите оператор: ");

            if (_input.hasNext()) {

                oper = _input.next().charAt(0);
                _log.log(Level.INFO, oper + " ");

                switch (oper) {

                    case '+':

                        return typeOfOperation.sum;

                    case '-':

                        return typeOfOperation.sub;

                    case '*':

                        return typeOfOperation.mult;

                    case '/':

                        return typeOfOperation.div;

                    case '^':

                        return typeOfOperation.deg;

                    case 'c':
                    case 'C':

                        return typeOfOperation.clear;

                    case 'q':
                    case 'Q':

                        return typeOfOperation.exit;

                    default:

                        _working = false;

                        continue;

                }

            } else {

                System.out.println(_ERRORINPUT);
                _log.log(Level.WARNING, _ERRORINPUT);

            }
        }

        return typeOfOperation.err;
    }

    private typeOfOperation operatorChoice(char oper) {

        _log.log(Level.INFO, oper + " ");

        while (_working) {

            switch (oper) {

                case '+':

                    return typeOfOperation.sum;

                case '-':

                    return typeOfOperation.sub;

                case '*':

                    return typeOfOperation.mult;

                case '/':

                    return typeOfOperation.div;

                case '^':

                    return typeOfOperation.deg;

                case 'c':
                case 'C':

                    return typeOfOperation.clear;

                case 'q':
                case 'Q':

                    return typeOfOperation.exit;

                default:

                    _working = false;

                    continue;

            }
            
        }

        return typeOfOperation.err;
    }

    private Double sum(double firstValue, double secondValue) {
        return firstValue + secondValue;
    }

    private Double sub(double firstValue, double secondValue) {
        return firstValue - secondValue;
    }

    private Double mult(double firstValue, double secondValue) {
        return firstValue * secondValue;
    }

    private Double div(double firstValue, double secondValue) {
        return firstValue / secondValue;
    }

    private Double deg(double firstValue, double secondValue) {
        if (secondValue == 0)
            return 1D;
        return firstValue * deg(firstValue, secondValue - 1);
    }

    private enum typeOfOperation{
        sum,
        sub,
        mult,
        div,
        deg,
        err,
        exit,
        clear
    }

    private void initLogger() throws IOException{

        _log = Logger.getLogger(Calculator.class.getName());

        FileHandler fh = new FileHandler("log/log.xml");
        fh.setEncoding("UTF-8");
        _log.addHandler(fh);

        XMLFormatter xml = new XMLFormatter();
        fh.setFormatter(xml);
    }
    
}
