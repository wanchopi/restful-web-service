package com.wanchopi.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wanchopi.rest.exception.ResourceNotFoundException;
import com.wanchopi.rest.model.Employee;
import com.wanchopi.rest.repository.EmployeeRepository;

/**
 * Employee controller
 * @author Wanchopi
 *
 */
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping(value = "/employees")
	public ResponseEntity<List<Employee>> findAll() {
	
		List<Employee> employees = employeeRepository.findAll();		
		return ResponseEntity.ok().body(employees);
	}
	
	@GetMapping(value = "/employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long id)
		throws ResourceNotFoundException {
		
		String message = "Empleado no encontrado para el ID ";
		Employee employee = employeeRepository.findById(id)
				.orElseThrow( () -> new ResourceNotFoundException(message + id));
		return ResponseEntity.ok().body(employee);
	}
	
	@PostMapping(value = "/create")
	public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) 
		throws ResourceNotFoundException {

		Employee createEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok().body(createEmployee);
	}
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		
		String message = "Empleado no encontrado para el ID ";
		Employee employee = employeeRepository.findById(id)
				.orElseThrow( () -> new ResourceNotFoundException(message + id));
		
		employee.setEmail(employeeDetails.getEmail());
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		
		final Employee updateEmployee = employeeRepository.save(employee);
		
		return ResponseEntity.ok().body(updateEmployee);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable(value = "id") Long id)
		throws ResourceNotFoundException {
		
		String message = "Empleado no encontrado para el ID ";
		Employee employee = employeeRepository.findById(id)
				.orElseThrow( () -> new ResourceNotFoundException(message + id));
		
		employeeRepository.delete(employee);
		
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		
		return ResponseEntity.ok().body(response);
	}

}





























