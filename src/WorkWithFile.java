import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
public class WorkWithFile {
    public void saveEmployeeToFile(List<Employee> employeeList) {
        Path pth = Paths.get("", "test.csv").toAbsolutePath();
        EmployeeAdditionalInformation eai = employee -> switch (employee.getEmployeeType()) {
            case DEVELOPER -> {
                Developer dev = (Developer) employee;
                yield "ЯП: " + dev.getProgrammingLanguage();
            }
            case MANAGER -> {
                Manager mng = (Manager) employee;
                yield "Размер команды: " + mng.getTeamSize();
            }
            case INTERN -> {
                Intern intern = (Intern) employee;
                yield "Закончил ВУЗ: " + intern.getUniversity();
            }
        };

        try {
            if (Files.exists(pth))
                Files.delete(pth);
            Files.writeString(pth,
                    "Имя,Зарплата,Тип,Дополнительная_Информация" + System.lineSeparator(),
                    StandardOpenOption.CREATE_NEW);
            employeeList.forEach(f -> {
                List<String> data = new ArrayList<>(List.of(
                        f.getName(),
                        String.valueOf(f.getSalary()),
                        String.valueOf(f.getEmployeeType()),
                        eai.GetAdditionalInformation(f)
                ));
                try {
                    Files.writeString(pth,
                            String.join(", ", data) + System.lineSeparator(),
                            StandardOpenOption.APPEND);
                } catch (Exception ex) {
                    System.out.println("Возникла ошибка при записи строки(" + String.join(",", data) + ") в " +
                            "файл: " + ex);
                }
            });

        } catch (Exception ex) {
            System.out.println("Возникла ошибка при записи в файл информации об сотрудниках: " + ex);
        }

    }
    public void loadEmployeesFromFile() {
        Path pth = Paths.get("", "test.csv").toAbsolutePath();
        List<String> lines;
        try {
            lines = Files.readAllLines(pth);
            lines.removeFirst();
        } catch (Exception e) {
            System.out.println("Возникла ошибка при чтении данных из файла: " + e);
            return;
        }
        if (lines.isEmpty())
            return;
        List<Employee> employees = new ArrayList<>();
        try {
            lines.forEach(f -> {
                var dataInRow = f.split(", ");
                String name = dataInRow[0];
                int salary = (int) Double.parseDouble(dataInRow[1]);
                String type = dataInRow[2];
                Employee empl = switch (type) {
                    case "MANAGER" -> new Manager(name, salary, Integer.parseInt(dataInRow[3].split(" ")[2]));
                    case "DEVELOPER" -> new Developer(name, salary, dataInRow[3].split(" ")[1]);
                    case "INTERN" -> new Intern(name, salary, dataInRow[3].split(" ")[2]);
                    default -> throw new IllegalArgumentException("Данного типа не имеется");
                };
                employees.add(empl);
            });
        }
        catch (Exception e){
            System.out.println("Возникла ошибка при парсе строки: " + e);
        }
        employees.forEach(Employee::displayInfo);
    }
}