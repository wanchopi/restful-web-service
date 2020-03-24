package com.wanchopi.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wanchopi.rest.model.Employee;

/**
 * Employee repository
 * @author Wanchopi
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
