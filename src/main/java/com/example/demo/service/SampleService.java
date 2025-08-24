package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Address;
import com.example.demo.dto.Employee;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.repo.AddressRepository;
import com.example.demo.repo.EmployeeRepository;

@Service
public class SampleService {

	private AddressRepository addressRepo;
	private EmployeeRepository employeeRepository;

	@Autowired
	public SampleService(AddressRepository addressRepo, EmployeeRepository employeeRepository) {
		this.addressRepo = addressRepo;
		this.employeeRepository = employeeRepository;
	}

	public String updateEmployee(EmployeeDTO emp) {

		return employeeRepository.findById(emp.getId()).map(e -> {

			e.setName(emp.getName());

			employeeRepository.save(e);
			return "Success";
		}).orElse("Not found");

	}

	public String createEmployee(EmployeeDTO emp) {

		Employee e = new Employee();
		e.setId(emp.getId());
		e.setName(emp.getName());
		Address a1 = new Address();
		a1.setAddress(emp.getAddress());
		a1.setEmployee(e);
		e.setAddress(List.of(a1));

		Employee res = employeeRepository.save(e);

		if (res != null) {
			return res.getId() + res.getName();
		} else {
			return "unable";
		}
	}

	public List<EmployeeDTO> serachEmployee(Integer employeeId, String name) {

		List<Employee> response = null;
		if (employeeId == null || employeeId != 0 && name != null && !name.isEmpty()) {
			response = employeeRepository.findByNameContainingIgnoreCase(name);
		} else {
			response = employeeRepository.findByNativeQurey(employeeId);
		}

		return response.stream().map(e -> new EmployeeDTO(e.getId(), e.getName())).collect(Collectors.toList());
	}

}
