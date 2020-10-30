package Homework.Lesson15;
// Создать список объектов List<Employee> (использовать метод employeeGenerator)
// и сортировать по:
// имени
// имени и зарплате
// имени, зарплате, возрасту и компании

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Employee implements Comparable<Employee> {
    private String name;
    private String company;
    private int salary;
    private int age;

    // TODO: конструктор, геттеры и сеттеры

    public Employee(String name, String company, int salary, int age) {
        Objects.requireNonNull(name, "name is null");
        Objects.requireNonNull(company, "company is null");
        if (salary < 1000 || salary > 5000) throw new IllegalArgumentException("salary < 1000 or salary > 5000");
        if (age < 21 || age > 60) throw new IllegalArgumentException("age < 21 or age > 60");
        this.name = name;
        this.company = company;
        this.salary = salary;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public String getCompany() {
        return company;
    }
    public int getSalary() {
        return salary;
    }
    public int getAge() {
        return age;
    }

    public static List<Employee> employeeGenerator(int num) {
        // метод для создания списка объектов класса Employee
        String[] names = {"Mike", "Tom", "Alex", "John", "Peter", "Jack", "Charlie", "Max", "Jenifer", "Linda", "Elizabeth"}; // массив с именами
        String[] companies = {"Microsoft", "IBM", "Google", "General Electric", "Siemens", "Samsung", "Apple"}; // массив с названиями компаний

        List<Employee> employees = new ArrayList<>(num);

        // добавление num объектов Employee в список (employees)
        for (int i = 0; i < num; i++) {
            // TODO: объекты создавать со случайными значениями. Возраст от 21 до 60 и не забудьте про зп
            Random r = new Random();
            employees.add(new Employee(names[r.nextInt(names.length)],
                    companies[r.nextInt(companies.length)],
                    r.nextInt(4001) + 1000,
                    r.nextInt(40) + 21));
        }
        return employees;
    }


    public static List<Employee> sortingByName(List<Employee> employees) {
        Comparator<Employee> employeeComparator = new NameComparator();
        employees.sort(employeeComparator);
        return employees;
    }

    public static List<Employee> sortingByNameAndSalary(List<Employee> employees) {
        Comparator<Employee> employeeComparator = new NameComparator().thenComparing(new SalaryComparator());
        employees.sort(employeeComparator);
        return employees;
    }

    public static List<Employee> sortingByAllParameters(List<Employee> employees) {
        Comparator<Employee> employeeComparator = new NameComparator()
                .thenComparing(new SalaryComparator())
                .thenComparing(new AgeComparator())
                .thenComparing(new CompanyComparator());
        employees.sort(employeeComparator);
        return employees;
    }

    @Override
    public int compareTo(@NotNull Employee o) {
        return Integer.compare(this.getAge(), o.getAge());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        List<Employee> employees = employeeGenerator(5);
        System.out.println("new list created: ");
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }

        System.out.println("\n" + "sorting by name: ");
        sortingByName(employees);
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }

        System.out.println("\n" + "sorting by name and salary: ");
        sortingByNameAndSalary(employees);
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }

        System.out.println("\n" + "sorting by all parameters: ");
        sortingByAllParameters(employees);
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
    }
}


class NameComparator implements Comparator<Employee> {
    @Override
    public int compare(@NotNull Employee o1, Employee o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

class CompanyComparator implements Comparator<Employee> {
    @Override
    public int compare(@NotNull Employee o1, Employee o2) {
        return o1.getCompany().compareTo(o2.getCompany());
    }
}

class SalaryComparator implements Comparator<Employee> {
    @Override
    public int compare(@NotNull Employee o1, Employee o2) {
        return Integer.compare(o1.getSalary(), o2.getSalary());
    }
}

class AgeComparator implements Comparator<Employee> {
    @Override
    public int compare(@NotNull Employee o1, Employee o2) {
        return Integer.compare(o1.getAge(), o2.getAge());
    }
}
