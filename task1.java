import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Пусть дан LinkedList с несколькими элементами.
 * Реализуйте метод(не void), который вернет “перевернутый” список.
 */
public class task1
{
    public static void main(String[] args)
    {
        var linkedList = new LinkedList<Integer>(CreateRandomList(1, 10, 5));
        System.out.printf("Задан LinkedList с целыми числами: %s\n", linkedList);
        List<Integer> revList = ReverseLinkedList(linkedList);
        System.out.printf("Получили перевернутый список: %s\n", revList);
    }

    // Создать список размером size, заполненный случайными числами в диапазоне от min до max.
    private static List<Integer> CreateRandomList(int min, int max, int size)
    {
        var randArrayList = new ArrayList<Integer>(size);
        var rand = new Random();
        for(int i = 0; i < size; i++)
            randArrayList.add(rand.nextInt(min,max));
        return randArrayList;
    }

    // Получить перевернутый список из linkedList.
    private static List<Integer> ReverseLinkedList(LinkedList<Integer> linkedList)
    {
        var list = new ArrayList<Integer>();
        while(linkedList.size() > 0)
            list.add(linkedList.removeLast());
        return list;
    }
}