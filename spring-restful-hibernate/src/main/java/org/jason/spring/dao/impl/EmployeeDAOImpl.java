/**
 * 
 */
package org.jason.spring.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jason.spring.dao.AbstractDao;
import org.jason.spring.dao.EmployeeDAO;
import org.jason.spring.entity.Employee;
import org.springframework.stereotype.Repository;

/**
 * @author jason.zhang
 * @version 1.0
 */

@Repository
public class EmployeeDAOImpl extends AbstractDao implements EmployeeDAO {

  @Override
  public List<Employee> getAllEmployees() {
    return getSession().createCriteria(Employee.class).list();
  }

  @Override
  public Employee findBySsn(String ssn) {
    Criteria criteria = getSession().createCriteria(Employee.class);
    criteria.add(Restrictions.eq("ssn", ssn));
    return (Employee) criteria.uniqueResult();
  }
}
