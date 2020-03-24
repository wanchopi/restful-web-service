package com.wanchopi.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.wanchopi.rest.model.Employee;
import com.wanchopi.rest.repository.EmployeeRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Test about EmployeeController class
 * @author Wanchopi
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
	
	private static final ObjectMapper om = new ObjectMapper();
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private EmployeeRepository employeeRepository;
	
	@Test
	public void find_allEmployees() throws Exception {
		
		Employee employee1 = new Employee("Zipi", "Zapatilla", "zipi@escobar.com");
		employee1.setId(1L);
		Employee employee2 = new Employee("Zape", "Zapatilla", "zape@escobar.com");
		employee2.setId(2L);
		
		List<Employee> employees = Arrays.asList(employee1, employee2);
		
		given(employeeRepository.findAll()).willReturn(employees);
		
		this.mockMvc.perform(get("/employees"))
			.andExpect(status().isOk())
			.andExpect(content().json("[{'id': 1, 'firstName': 'Zipi', 'lastName': 'Zapatilla', 'email': 'zipi@escobar.com'}, "
				+ "{'id': 2, 'firstName': 'Zape', 'lastName': 'Zapatilla', 'email': 'zape@escobar.com'}]"));
		
	}
	
	@Test
    public void find_employeeIdNotFound_404() throws Exception {
        mockMvc.perform(get("/employee/5")).andExpect(status().isNotFound());
    }
	
	@Test
	public void find_employeeId() throws Exception {
		
		Employee employee = new Employee("Zipi", "Zapatilla", "zipi@escobar.com");
		employee.setId(1L);
		
		when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
		
		this.mockMvc.perform(get("/employee/1"))
			.andExpect(status().isOk())
			.andExpect(content().json("{'id': 1, 'firstName': 'Zipi', 'lastName': 'Zapatilla', 'email': 'zipi@escobar.com'}"));
	}
	
	@Test
	public void create_employee() throws Exception {
		
		Employee newEmployee = new Employee("Zipi", "Zapatilla", "zipi@escobar.com");
		newEmployee.setId(1L);
		
		when(employeeRepository.save(any(Employee.class))).thenReturn(newEmployee);
		
		mockMvc.perform(post("/create")
			.content(om.writeValueAsString(newEmployee))
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
		
		verify(employeeRepository, times(1)).save(any(Employee.class));
		
	}
	
	@Test
	public void delete_employee() throws Exception {
		
		Employee employee = new Employee("Zipi", "Zapatilla", "zipi@escobar.com");
		employee.setId(1L);
		
		when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
		
		mockMvc.perform(delete("/delete/1"))
		.andExpect(status().isOk());
		
	}

}








