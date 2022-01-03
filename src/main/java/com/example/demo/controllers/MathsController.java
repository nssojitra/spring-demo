package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dataaccess.CustomerStorageRepository;
import com.example.demo.models.ApiResponse;
import com.example.demo.models.ComplexNumber;
import com.example.demo.models.Customer;
import com.example.demo.models.PolarNumber;

@RestController
public class MathsController {

	@Autowired
	CustomerStorageRepository repository;
	
	@GetMapping("/square/{a}")
	public ApiResponse Add(@PathVariable Integer a)
	{
		ApiResponse ret = new ApiResponse();
		ret.setInput(a);
		ret.setResult(a*a);
		return ret;
	}
	
	
	@PostMapping("/Polar")
	public PolarNumber GetPolar(@RequestBody ComplexNumber number)
	{
		PolarNumber ret = new PolarNumber();
	
		System.out.println(number.getReal());
		
		double r = Math.sqrt(number.getReal()*number.getReal()+number.getImaginary()*number.getImaginary());
		ret.setRadius(r);
		double a = Math.atan(number.getImaginary()/number.getReal());
		ret.setAngle(a);
		
		return ret;
	}
	
	@PostMapping("/Customers")
	public ResponseEntity<Customer> CreateCustomer(@RequestBody Customer customer)
	{
		repository.Save(customer);
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
	}
	
	@GetMapping("/Customers")
	public ResponseEntity<List<Customer>> GetCustomers()
	{
		return new ResponseEntity<List<Customer>>(repository.GetAll(),HttpStatus.OK);
	}
	
	@DeleteMapping("/Customers")
	public ResponseEntity<Boolean> DeleteCustomer(@RequestBody Customer customer)
	{
		repository.Delete(customer.getFirstName(),customer.getLastName());
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	}
}