package org.jason.spring.service.impl;
import java.util.List;

import org.jason.spring.dao.EmployeeDAO;
import org.jason.spring.entity.Employee;
import org.jason.spring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 
 * @author jason.zhang
 * @version 1.0
 */
@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	
    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    public List<Employee> getAllEmployees() {
        return this.employeeDAO.getAllEmployees();
    }
    
    @Override
    public Employee getEmployee(String ssn){
      return this.employeeDAO.findBySsn(ssn);
    }

}
