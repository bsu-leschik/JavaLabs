package kontr;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Consumer;

public class EmployeesList {
    public static ArrayList<Employee> getEmployeesBySalary(ArrayList<Employee> list) throws IllegalArgumentException{
        checkArguments(list);
        return new ArrayList<>(list.stream().sorted(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getSalary() - o2.getSalary();
            }
        }).toList());

    }

    public static ArrayList<Employee> getEmployeesBySalaryAndCoefficient(ArrayList<Employee> list) throws IllegalArgumentException{
        checkArguments(list);
        return new ArrayList<>(list.stream().sorted(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return (int) (o1.getSalary() / o1.getCoefficient() - o2.getSalary() / o2.getCoefficient());
            }
        }).toList());

    }

    public static ArrayList<String> getCompaniesStartingWithB(ArrayList<Employee> list) throws IllegalArgumentException{
        checkArguments(list);
        ArrayList<String> bCompanies = new ArrayList<>();
        list.stream().forEach(new Consumer<Employee>() {
            @Override
            public void accept(Employee employee) {
                if (employee.organization.charAt(0) == 'b' || employee.organization.charAt(0) == 'B'){
                    if (!bCompanies.contains(employee.organization)){
                        bCompanies.add(employee.organization);
                    }
                }
            }
        });
        return bCompanies;
    }

    public static double countAverageSalary(ArrayList<Employee> list, String company) throws IllegalArgumentException{
        checkArguments(list);
        final double[] averageSalary = {0};
        final double[] amountOfEmployees = {0};
        list.stream().forEach(new Consumer<Employee>() {
            @Override
            public void accept(Employee employee) {
                if (employee.organization.equals(company)){
                    averageSalary[0] += employee.getSalary();
                    amountOfEmployees[0]++;
                }
            }
        });
        if (amountOfEmployees[0] == 0){
            throw new IllegalArgumentException("There is no such company");
        }
        return averageSalary[0] / amountOfEmployees[0];
    }

    private static void checkArguments(ArrayList<Employee> list) throws IllegalArgumentException{
        if (list == null || list.size() == 0){
            throw new IllegalArgumentException("Employees list is empty or null");
        }
    }
}
