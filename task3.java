import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.*;

/**
 * В калькулятор добавьте возможность отменить последнюю операцию.
 */
public class task3
{
    private static Scanner sc;
    
    public static void main(String[] args)
    {
        System.out.println("Шаг 1: Введите целое число.");
        System.out.println("Шаг 2: Укажите арифметическое действие: + - * /");
        System.out.println("Шаг 3: Введите еще одно число.");
        System.out.println("Шаг 4: После получения результата:");
        System.out.println("       - продолжите вычисления вернувшись к шагу 2");
        System.out.println("       - введите < для отмены последнего действия");
        System.out.println("       - введите X для завершения.");
        try
        {
            sc = new Scanner(System.in);
            var operation = ArithmeticOperation.NextOperation('+', GetOperand());
            while(true)
            {
                char operator = GetOperator();
                if(operator == 'X' || operator == 'x')
                    break;
                else if(operator == '<')
                    operation = ArithmeticOperation.CancelOperation();
                else
                {
                    int operand = GetOperand();
                    operation = ArithmeticOperation.NextOperation(operator, operand);
                }
                System.out.printf("=\n%d\n", operation.Calculate());
                WriteLog(operation.toString());
            }
        }
        catch(Exception e)
        {
            System.out.printf("Ошибка: %s", e.getMessage());
        }
        finally
        {
            if(sc != null)
                sc.close();
        }
    }

    // Получить число из командной строки.
    private static int GetOperand()
    {
        int operand = sc.nextInt();
        return operand;
    }

    // Получить арифметический оператор из командной строки.
    private static char GetOperator() throws Exception
    {
        char operator = sc.next().charAt(0);
        if("+-*/Xx<".indexOf(operator) < 0)
            throw new Exception("Указано недопустимое действие.");
        return operator;
    }

    // Записать в лог файл строку log.
    private static void WriteLog(String log) throws SecurityException, IOException
    {
        Logger logger = Logger.getLogger(task3.class.getName());
        logger.setLevel(Level.INFO);
        FileHandler fh = new FileHandler("task3.log", true);
        logger.addHandler(fh);
        fh.setFormatter(new SimpleFormatter());
        logger.setUseParentHandlers(false);
        logger.log(Level.INFO, log);
        fh.close();
    }
}

// Базовый класс для всех арифметических операций.
class ArithmeticOperation
{
    private static LinkedList<ArithmeticOperation> operationHistory; // история операций
    
    protected ArithmeticOperation _operand1; // левый операнд
    protected int _operand2; // правый операнд

    static
    {
        operationHistory = new LinkedList<ArithmeticOperation>();
        operationHistory.addLast(new ArithmeticOperation(null, 0));
    }
    
    ArithmeticOperation(ArithmeticOperation operand1, int operand2)
    {
        _operand1 = operand1;
        _operand2 = operand2;
    }

    // Реализовать следующую арифметическую операцию.
    public static ArithmeticOperation NextOperation(char operator, int operand)
    {
        ArithmeticOperation next;
        switch (operator)
        {
            case '+':
                next = new Addition(operationHistory.getLast(), operand);
                break;
            case '-':
                next = new Subtraction(operationHistory.getLast(), operand);
                break;
            case '*':
                next = new Multiplication(operationHistory.getLast(), operand);
                break;
            case '/':
                next = new Division(operationHistory.getLast(), operand);
                break;
            default:
                next = new ArithmeticOperation(null, 0);
                break;
        }
        operationHistory.addLast(next);
        return next;
    }

    // Отменить предыдущую операцию.
    public static ArithmeticOperation CancelOperation()
    {
        if(operationHistory.size() > 1)
            operationHistory.removeLast();
        return operationHistory.getLast();
    }
    
    // Получить результат данной арифметической операции.
    public int Calculate() { return 0; }
}

// Операция сложения.
class Addition extends ArithmeticOperation
{
    Addition(ArithmeticOperation operand1, int operand2) { super(operand1, operand2); }

    @Override
    public int Calculate() { return _operand1.Calculate() + _operand2; }

    @Override
    public String toString() { return String.format("%d + %d = %d", _operand1.Calculate(), _operand2, Calculate()); }
}

// Операция вычитания.
class Subtraction extends ArithmeticOperation
{
    Subtraction(ArithmeticOperation operand1, int operand2) { super(operand1, operand2); }

    @Override
    public int Calculate() { return _operand1.Calculate() - _operand2; }

    @Override
    public String toString() { return String.format("%d - %d = %d", _operand1.Calculate(), _operand2, Calculate()); }
}

// Операция умножения.
class Multiplication extends ArithmeticOperation
{
    Multiplication(ArithmeticOperation operand1, int operand2) { super(operand1, operand2); }

    @Override
    public int Calculate() { return _operand1.Calculate() * _operand2; }

    @Override
    public String toString() { return String.format("%d * %d = %d", _operand1.Calculate(), _operand2, Calculate()); }
}

// Операция деления.
class Division extends ArithmeticOperation
{
    Division(ArithmeticOperation operand1, int operand2) { super(operand1, operand2); }

    @Override
    public int Calculate() { return _operand1.Calculate() / _operand2; }

    @Override
    public String toString() { return String.format("%d / %d = %d", _operand1.Calculate(), _operand2, Calculate()); }
}