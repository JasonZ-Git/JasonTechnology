package org.jason.spring.controller;

import java.util.List;

import org.jason.spring.entity.Employee;
import org.jason.spring.service.EmployeeService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jason.zhang
 * @version 1.0
 */
@RestController
public class EmployeeController {

    private static final Logger logger = Logger.getLogger(EmployeeController.class);

    public EmployeeController() {
        System.out.println("EmployeeController()");
    }

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = {"getAllEmployees"})
    public List getAllEmployees() {
        logger.info("Getting all Employees.");
        List<Employee> employeeList = employeeService.getAllEmployees();
        return employeeList;
    }

    @RequestMapping(value = {"getEmployee"})
    public Employee getEmployee(String ssn) {
        logger.info("Getting aEmployee.");
        Employee employee = new Employee();
        employee.setName("Jason");
        return employee;
    }

    @RequestMapping(value = {"getEmployeeBySsn"})
    public Employee getEmployeeById(@RequestParam(value = "employeeSSN", defaultValue = "ssn00000001") String employeeSSN) {
        logger.info("Get employee by ssn");
        Employee employee = employeeService.getEmployee(employeeSSN);
        return employee;
    }
}
