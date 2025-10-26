import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void WorkWithReflection(Object obj) {
        try {
            Class<?> personClass = obj.getClass();
            System.out.println("Класс: " + personClass.getName());
            var fields = personClass.getDeclaredFields();
            for(var field: fields){
                field.setAccessible(true);
                Object value = field.get(obj);
                System.out.println("Поле: " + field.getName() + ", Значение: " + value);
            }
            var methods = personClass.getDeclaredMethods();
            for(var method: methods){
                System.out.println("Метод: " + method.getName());
            }

        } catch (Exception ex) {
            System.out.println("Возникла ошибка: " + ex);
        }
        System.out.println();
    }
    public static void main(String[] args) {

        WorkWithReflection(new Developer("Антон", 1000, "Java"));
        WorkWithReflection(new Manager("Гена", 2000, 4));
        WorkWithReflection(new Intern("Ваня", 500, "НТГУ"));

    }
}