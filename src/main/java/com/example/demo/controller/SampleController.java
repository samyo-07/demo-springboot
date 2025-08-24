package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CommonResponse;
import com.example.demo.dto.Employee;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.repo.AddressRepository;
import com.example.demo.repo.EmployeeRepository;
import com.example.demo.service.SampleService;

import jakarta.annotation.PostConstruct;

@RestController
public class SampleController{

	private AddressRepository addressRepository;
	private EmployeeRepository employeeRepo;
	private SampleService sampleService;

	@Autowired
	public SampleController(AddressRepository addressRepository, EmployeeRepository employeeRepo,
			SampleService sampleService) {
		this.addressRepository = addressRepository;
		this.employeeRepo = employeeRepo;
		this.sampleService = sampleService;
	}
	
	
//	@PostConstruct
//	public void test() {
//		File sourceDir = new File("D:\\003_Occasions\\2025\\Holiday\\Andaman And Nicobar\\202504__1");
//
//        if (!sourceDir.exists() || !sourceDir.isDirectory()) {
//            System.out.println("Invalid directory: " + "F:\\Occasions\\2025\\Holiday\\Andaman Nicoar\\Vd");
//            return;
//        }
//
//        // List all files (recursively = false here, can make true if needed)
//        Collection<File> files = FileUtils.listFiles(sourceDir, null, false);
//
//        for (File file : files) {
//            try {
//                // Get last modified date
//                long lastModified = file.lastModified();
//                String dateFolderName = new SimpleDateFormat("dd-MM-yyyy")
//                        .format(new Date(lastModified));
//
//                // Create date folder inside sourceDir
//                File dateFolder = new File(sourceDir, dateFolderName);
//                if (!dateFolder.exists()) {
//                    dateFolder.mkdirs();
//                }
//
//                // Move file
//                File targetFile = new File(dateFolder, file.getName());
//                FileUtils.moveFile(file, targetFile);
//
//                System.out.println("Moved: " + file.getName() + " -> " + dateFolderName);
//            } catch (IOException e) {
//                System.err.println("Error moving file: " + file.getName() + " - " + e.getMessage());
//            }
//        }
//	}

	@GetMapping(value = "/address/employee/{employeeId}", produces = "application/json")
	public ResponseEntity<List<String>> getAddressByEmployee(@PathVariable Integer employeeId) {
//		Address address = addressRepository.findAddressByEmployeeId(employeeId);
		Optional<Employee> address = employeeRepo.findById(employeeId);
		if (address != null) {

			List<String> add = new ArrayList<String>();
			add = address.get().getAddress().stream().map(e -> e.getAddress()).collect(Collectors.toList());

			return ResponseEntity.ok(add);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/updateEmployee")
	public ResponseEntity<String> updateEmployee(@RequestBody EmployeeDTO empReDto) {

		String msg = sampleService.updateEmployee(empReDto);
		return new ResponseEntity<String>(msg, HttpStatus.OK);

	}

	@PostMapping("/createEmployee")
	public ResponseEntity<String> createEmployee(@RequestBody EmployeeDTO empReDto) {
		

		String msg = sampleService.createEmployee(empReDto);
		return new ResponseEntity<String>(msg, HttpStatus.OK);

	}

	@GetMapping(value = "/findEmployee", produces = "application/json")
	public ResponseEntity<CommonResponse> findEmployee(@RequestParam(name = "id", required = false) Integer employeeId,
			@RequestParam(name = "name", required = false) String name) {

		List<EmployeeDTO> resp = sampleService.serachEmployee(employeeId, name);

		CommonResponse re = new CommonResponse(resp);

		return new ResponseEntity<CommonResponse>(re, HttpStatus.OK);
	}
}
