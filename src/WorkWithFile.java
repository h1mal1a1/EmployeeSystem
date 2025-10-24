import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class WorkWithFile {
    public void saveEmployeeToFIle(List<Employee> employeeList){
        Path pth = Paths.get("","test.csv").toAbsolutePath();
        EmployeeAdditionalInformation eai = employee -> switch (employee.getEmployeeType()) {
            case DEVELOPER -> {
                Developer dev =  (Developer) employee;
                yield "ЯП: " + dev.getProgrammingLanguage();
            }
            case MANAGER -> {
                Manager mng = (Manager)employee;
                yield "Размер команды: " + mng.getTeamSize();
            }
            case INTERN -> {
                Intern intern = (Intern) employee;
                yield "Закончил ВУЗ: " + intern.getUniversity();
            }
        };

        try{
            if(Files.exists(pth))
                Files.delete(pth);
            Files.writeString(pth,
                    "Имя,Зарплата,Тип,Дополнительная_Информация" + System.lineSeparator(),
                    StandardOpenOption.CREATE_NEW);
            employeeList.forEach(f-> {
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
                }
                catch (Exception ex){
                    System.out.println("Возникла ошибка при записи строки("+String.join(",",data)+") в " +
                            "файл: " + ex);
                }
            });

        }
        catch (Exception ex){
            System.out.println("Возникла ошибка при записи в файл информации об сотрудниках: " + ex);
        }

    }
}
