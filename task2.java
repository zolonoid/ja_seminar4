import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Реализовать очередь с помощью LinkedList со следующими методами:
 *  enqueue() - помещает элемент в конец очереди,
 *  dequeue() - возвращает первый элемент из очереди и удаляет его,
 *  first() - возвращает первый элемент из очереди, не удаляя.
 */
public class task2
{
    public static Scanner sc;

    public static void main(String[] args)
    {
        try
        {
            sc = new Scanner(System.in);
            var queue = new MyQueue();
            while(true)
            {
                System.out.println("Укажите цифру для выбора действия:\n"                                +
                                   " 1 - enqueue (поместить элемент в конец очереди)\n"                  +
                                   " 2 - dequeue (возвратить первый элемент из очереди и удалить его)\n" +
                                   " 3 - first (возвратить первый элемент из очереди не удаляя его)\n"   +
                                   " 0 - выход");
                String input = sc.nextLine();
                switch(Integer.parseInt(input))
                {
                    case 0:
                        return;
                    case 1:
                        queue.enqueue();
                        break;
                    case 2:
                        queue.dequeue();
                        break;
                    case 3:
                        queue.first();
                        break;
                    default:
                        System.out.println("Неизвестная команда");
                        continue;
                }
                System.out.printf("Элементы в очереди: %s\n", queue);
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
}


// Класс представляет очередь из строковых элементов.
class MyQueue
{
    private LinkedList<String> _list;
    
    MyQueue()
    {
        _list = new LinkedList<String>();
    }

    MyQueue(Collection<String> c)
    {
        _list = new LinkedList<String>(c);
    }

    // Поместить элемент в конец очереди.
    public void enqueue()
    {
        System.out.println("Введите строку, которую нужно поместить в конец очереди...");
        String elem = task2.sc.nextLine();
        _list.addLast(elem);
        System.out.printf("Элемент '%s' добавлен в конец очереди\n", elem);
    }
    
    // Возвратить первый элемент из очереди и удалить его.
    public void dequeue()
    {
        if(_list.size() > 0)
        {
            String elem = _list.removeFirst();
            System.out.printf("Элемент '%s', являвшийся первым элементом в очереди удален\n", elem);
        }
        else
            System.out.println("В очереди нет ни одного элемента");
    }

    // Возвратить первый элемент из очереди не удаляя его.
    public void first()
    {
        if(_list.size() > 0)
        {
            String elem = _list.getFirst();
            System.out.printf("Элемент '%s' является первым элементом в очереди\n", elem);
        }
        else
            System.out.println("В очереди нет ни одного элемента");
    }

    @Override
    public String toString()
    {
        return _list.toString();
    }
}