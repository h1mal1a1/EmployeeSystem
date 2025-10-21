import java.util.ArrayList;
import java.util.List;

public class Manager extends Employee {
    private final int _teamSize;

    public Manager(String name, int salary, int teamSize) {
        super(name, salary, EmployeeType.MANAGER);
        _teamSize = teamSize;
    }

    protected int getTeamSize() {
        return _teamSize;
    }

    @Override
    public double calculateTotalCompensation() {
        return super.getSalary() + calculateBonus();
    }

    @Override
    protected double calculateBonus() {
        return super.getSalary() * 0.25 + 5000;
    }

    @Override
    protected void displayInfo() {
        System.out.println("Manager: ");
        super.displayInfo();
        System.out.println("Команда: " + getTeamSize() + " человек\r\nБонус: " + calculateBonus());
        System.out.println("Общая компенсация: " + calculateTotalCompensation());
    }
    public AwardList createAwardList() {
        return new AwardList();
    }

    public class AwardList {
        private List<Employee> awardedEmployees = new ArrayList<>();

        public void addEmployee(Employee empl) {
            awardedEmployees.add(empl);
        }

        public void displayAwardees() {
            if (awardedEmployees.isEmpty()) {
                System.out.println("Награжденный список пуст");
                return;
            }
            System.out.println("Сотрудники подлежащие награждению: ");
            List<String> names = awardedEmployees
                    .stream()
                    .map(emp -> "Id сотрудника: " + emp.getEmployeeId() + ", имя сотрудника: " + emp.getName())
                    .toList();
            System.out.println(String.join(",", names));
        }
    }
}
