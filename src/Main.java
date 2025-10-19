public class Main {
    public static void main(String[] args) {
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
        }
        catch (InvalidSalaryException ex){
            System.out.println("Ошибка при создании сотрудника: " + ex.getMessage());
        }
    }
}