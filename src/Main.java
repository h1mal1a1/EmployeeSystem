import java.util.*;

public class Main {
    public static void WorkWithCollections() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Developer("Антон", 1000, "Java"));
        employees.add(new Manager("Гена", 2000, 4));
        employees.add(new Intern("Ваня", 500, "НТГУ"));
        employees.add(new Developer("Витя", 3000, "C#"));
        employees.add(new Manager("Миша", 4000, 5));
        employees.add(new Intern("Антон", 500, "МГУ"));

        Set<String> uniqueNames = new HashSet<>();
        for (var empl : employees)
            uniqueNames.add(empl.getName());
        System.out.println("Уникальные имена: " + String.join(",",uniqueNames));

        List<Employee> lstDev = employees.stream().filter(employee ->
                employee.getEmployeeType() == EmployeeType.DEVELOPER).toList();
        System.out.println();
        System.out.println("Devs: ");
        for(var dev: lstDev) {
            dev.displayInfo();
        }

        Map<Integer,Employee> employeeMap = new HashMap<>();
        for(var empl:employees)
            employeeMap.put(empl.getEmployeeId(),empl);
        System.out.println();

        var id = 10;
        var emplId = employeeMap.get(id);
        if(emplId == null)
            System.out.println("Empl with Id = " + id + " not found");
        else
            emplId.displayInfo();
        System.out.println("Size employees: " + employees.size());
        employees.add(new Intern("Петя",100,"РанХИГС"));
        System.out.println("Size employees after add: " + employees.size());
    }
    public static void WorkWithExceptions(){
        try {
            Employee[] employees = new Employee[] {
                    new Developer("Антон",1000,"Java"),
                    new Manager("Гена",2000,4),
                    new Intern("Ваня", 500, "НТГУ"),
                    new Developer("Витя",3000,"C#"),
                    new Manager("Миша",4000,5),
                    new Intern("Петя",500,"МГУ")
            };
            for(var empl:employees){
                empl.displayInfo();
                System.out.println();
            }
            Employee emp = new Developer("Вася",-100,"Java");
            emp.displayInfo();
        }
        catch (InvalidSalaryException ex){
            System.out.println("Ошибка при создании сотрудника: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {

    }
}

//Generic - позволяют создавать типобезопасные классы, интерфейсы и методы. Таким образом они решают проблемы:
// проверки типов до этапа компиляции, необходимости приведения типов