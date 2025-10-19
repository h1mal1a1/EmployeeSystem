public class Intern extends Employee {
    private final String _university;
    public Intern(String name, int salary, String university){
        super(name, salary);
        _university = university;
    }
    public String getUniversity(){
        return _university;
    }

    @Override
    protected double calculateBonus(){
        return 5000;
    }
    @Override
    public double calculateTotalCompensation() {
        return super.getSalary() + calculateBonus();
    }
    @Override
    protected void displayInfo() {
        System.out.println("Intern: ");
        super.displayInfo();
        System.out.println("Университет: " + getUniversity() + "\r\nБонус: " + calculateBonus());
        System.out.println("Общая компенсация: " + calculateTotalCompensation());
    }
}
