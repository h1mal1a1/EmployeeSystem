import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void WorkWithCollections() {
        List<Employee> employees = new ArrayList<>(List.of(
                new Developer("Антон", 1000, "Java"),
                new Manager("Гена", 2000, 4),
                new Intern("Ваня", 500, "НТГУ"),
                new Developer("Витя", 3000, "C#"),
                new Manager("Миша", 4000, 5),
                new Intern("Антон", 500, "МГУ")));

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
    public static void WorkWithGeneric(){
        Container<Employee> cont = new Container<>();
        cont.setItem(new Manager("Вася",1000,5));
        if(cont.isEmpty()) {
            System.out.println("Container is empty");
            return;
        }
        cont.getItem().displayInfo();
    }
    public static void WorkWithNestedClasses(){
        Manager mg = new Manager("Петя",10000,5);
        Intern int1 = new Intern("Женя",500,"МГУ");
        Intern int2 = new Intern("Женя",500,"МГУ");
        var al = mg.createAwardList();
        al.addEmployee(int2);
        al.displayAwardees();

    }
    public static void WorkWithLambdaExpression(){
        List<Employee> employees = List.of(
                new Developer("Антон", 1000, "Java"),
                new Manager("Гена", 2000, 4),
                new Intern("Ваня", 500, "НТГУ"),
                new Developer("Витя", 3000, "C#"),
                new Manager("Миша", 4000, 5),
                new Intern("Антон", 500, "МГУ"));
        System.out.println(employees.stream().filter(e->e.getSalary() > 1500).count());
        System.out.println(String.join(",",employees.stream().map(Employee::getName).toList()));

        var lstManagers = employees
                .stream()
                .filter(f->f.getEmployeeType()==EmployeeType.MANAGER)
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .limit(2);
        lstManagers.forEach(Employee::displayInfo);
        Map<Integer,Employee> mapIdAndEmpl = employees.stream().collect(Collectors.toMap(Employee::getEmployeeId,f->f));
        mapIdAndEmpl.forEach((f,x)->System.out.println("Id: "+ f + ", name: " + x.getName()));

        System.out.println("Интерн с ЗП меньше 600 " +
                (employees
                        .stream()
                        .anyMatch(f->f.getEmployeeType()==EmployeeType.INTERN && f.getSalary() < 600) ?
                "есть" : " не имеется"));
    }
    public static void WorkWithFunctionalInterface() {
        List<Employee> employees = new ArrayList<>(List.of(
                new Developer("Антон", 1000, "Java"),
                new Manager("Гена", 2000, 4),
                new Intern("Ваня", 500, "НТГУ"),
                new Developer("Витя", 3000, "C#"),
                new Manager("Миша", 4000, 5),
                new Intern("Антон", 500, "МГУ")));
        EmployeeBonusCalculator calculator = employee -> switch (employee.getEmployeeType()) {
            case MANAGER -> (employee.getSalary() * 0.25 + 5000);
            case INTERN -> 5000;
            case DEVELOPER -> employee.getSalary() * 0.2;
            default -> throw new IllegalArgumentException("Unknow employee type: " + employee.getEmployeeType());
        };
        employees.forEach(e->System.out.println("Id сотрудника: " + e.getEmployeeId() +
                ". Bonus: " + calculator.calculateBonus(e)));
    }
    public static void main(String[] args) {
        WorkWithFunctionalInterface();
    }
}