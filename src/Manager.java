public class Manager extends Employee {
    private final int _teamSize;

    public Manager(String name, int salary, int teamSize) {
        super(name, salary);
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
}
