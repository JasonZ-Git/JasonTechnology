
package org.jason.spring.service;

import java.util.List;

import org.jason.spring.entity.Employee;

/**
 * @author jason.zhang
 * @version 1.0
 */
public interface EmployeeService {
    //public long createEmployee(Employee employee);
    //public Employee updateEmployee(Employee employee);
    //public void deleteEmployee(int id);
    public List<Employee> getAllEmployees();

    public Employee getEmployee(String ssn);
    //public List<Employee> getAllEmployees(String employeeName);
}
