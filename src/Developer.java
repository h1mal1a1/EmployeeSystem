@EmployeeInfo("Senior Developer") public class Developer extends Employee {
    private final String _programmingLanguage;

    public Developer(String name, int salary, String programmingLanguage) {
        super(name, salary, EmployeeType.DEVELOPER);
        _programmingLanguage = programmingLanguage;
    }

    public String getProgrammingLanguage() {
        return _programmingLanguage;
    }

    @Override
    public double calculateTotalCompensation() {
        return super.getSalary() + calculateBonus();
    }

    @Override
    public double calculateBonus() {
        return super.getSalary() * 0.2;
    }

    @Override
    @EmployeeInfo("Основной метод получения информации") public void displayInfo() {
        System.out.println("Developer:");
        super.displayInfo();
        System.out.println("Язык: " + getProgrammingLanguage() + "\r\nБонус: " + calculateBonus());
        System.out.println("Общая компенсация: " + calculateTotalCompensation());
    }
}
