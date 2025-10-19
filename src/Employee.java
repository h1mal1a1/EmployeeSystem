public abstract class Employee implements Payable {
    private final String _name;
    private static int _nextId = 1;
    private final int _employeeId;
    private final double _salary;

    public Employee(String name, double salary) {
        if(salary < 0){
            throw new InvalidSalaryException("Зарплата не может быть отрицательной");
        }
        _name = name;
        _salary = salary;
        _employeeId = _nextId++;
    }
    protected String getName() {
        return _name;
    }
    protected double getSalary() {
        return _salary;
    }
    protected int getEmployeeId() {
        return _employeeId;
    }

    public abstract double calculateTotalCompensation();

    protected abstract double calculateBonus();
    protected void displayInfo() {
        System.out.println("Имя: " + getName() + "\r\nЗп: " + getSalary() + "\r\nId: " + getEmployeeId());
    }
}
