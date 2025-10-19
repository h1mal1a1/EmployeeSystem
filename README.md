В проекте показан пример использования наследования (extends), полиморфизма (массив Employee), инкапсуляции (private поля), абстрактных классов и методов.
// Базовый класс Employee
// - name, employeeId, salary
// - abstract calculateBonus()
// - displayInfo()

// Класс Developer (наследует Employee)
// - programmingLanguage
// - override calculateBonus() (20% от salary)

// Класс Manager (наследует Employee)
// - teamSize
// - override calculateBonus() (25% от salary + бонус за команду)

// Класс Intern (наследует Employee)
// - university
// - override calculateBonus() (фиксированный бонус 5000)
