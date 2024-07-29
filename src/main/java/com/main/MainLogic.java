package com.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.util.ArrayIterator;
import com.main.entity.Employee;
import com.main.exception.EmployeeNotFoundException;
import com.main.logic.MyService;

@Controller
public class MainLogic{

	@Autowired
	MyService myService;
	
	
	

	
	public void run() throws Exception {    // run is an method created in Application.java by extending CommandLineRunner.class

		Scanner scanner = new Scanner(System.in);
		
//----------------SWITCH CASE-----------------------------------------------------------------
		while (true) {

			System.out.println("1. Add new Employee Info");
			System.out.println("2. Update ");
			System.out.println("3. Delete ");
			System.out.println("4. Show All Employee Info");
			System.out.println("5. Find Employee by Id");
			System.out.println("6. EXIT ");
			System.out.println();

			System.out.print("Enter choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {

			case 1:
				addEmp(scanner);
				break;

			case 2:
				updateEmp(scanner);
				break;

			case 3:
				deleteEmpById(scanner);
				break;
			case 4:
				showAllInfo();
				break;

			case 5:
				findById(scanner);
			case 6:
				exit();
				break;
			default:
				System.out.println("Enter a valid choice.");
				System.out.println();
				break;
			}

		}
	}
	//--------------------------INSERT DATA --------------------------------------------

	private void addEmp(Scanner scanner) {

		try {
			System.out.print("Enter Employee ID: ");
			int id = scanner.nextInt();
			scanner.nextLine();

			System.out.print("Enter Name: ");
			String name = scanner.nextLine();

			System.out.print("Enter email: ");
			String email = scanner.nextLine();

			System.out.print("Enter phoneNo: ");
			String phoneNo = scanner.nextLine();

			System.out.print("Enter Employee dept. : ");
			String dept = scanner.nextLine();

			Employee emp = new Employee();

			emp.setId(id);
			emp.setName(name);
			emp.setEmail(email);
			emp.setPhoneNo(phoneNo);
			emp.setPosition(dept);

			
			Employee a = myService.saveEmp(emp);

			System.out.println(" Data Entered Successfully");
			System.out.println(a);
			System.out.println();
		}
		catch (EmployeeNotFoundException e) {
			System.out.println("Error !!! ");
		}
		
		
		
	}
//--------------------------UPDATE DATA --------------------------------------------

	private void updateEmp(Scanner scanner){
		
		Employee emp = new Employee();
		
		System.out.print("Enter Id for Updation: ");
		int update = scanner.nextInt();
		scanner.nextLine();

		
		try {
			emp = myService.findById(update).orElseThrow(() -> new EmployeeNotFoundException(" Employee Not Found"));
			
			System.out.print("Enter Student Name: ");
			String name = scanner.nextLine();

			System.out.print("Enter Student email: ");
			String email = scanner.nextLine();

			System.out.print("Enter Student phoneNo: ");
			String phoneNo = scanner.nextLine();

			System.out.print("Enter Employee dept. : ");
			String dept = scanner.nextLine();

			
				emp.setName(name);
				emp.setEmail(email);
				emp.setPhoneNo(phoneNo);
				emp.setPosition(dept);

			Employee e = myService.saveEmp(emp);
			System.out.println(e + " ");
		
			System.out.println(" Info Updated Successfully");
		}
		catch(EmployeeNotFoundException e) {
			System.out.println("Employee Not Found");
			e.getMessage();
			System.out.println();
			
		}
		

			
		
		

	}
	
//--------------------------DELETE DATA --------------------------------------------
	
	private void deleteEmpById(Scanner scanner) {

		
		
		System.out.print("Enter ID for deletion : ");
		int idDelete = scanner.nextInt();
		
		
			myService.deleteEmpById(idDelete);
			System.out.println("Deleted successfully.");
			System.out.println();
		
		
	
			System.out.println("Invalid ID");
			System.out.println("Enter a Valid Id");
		
			System.out.println();
		
	}
	
//-------------------------SHOW ALL DATA --------------------------------------------
	private void showAllInfo() {
		
		List<Employee> list = myService.findAllEmployee();
		
		if(list.isEmpty()) {
			System.out.println("Data Not Found");
			System.out.println();
		}
		else {
			System.out.println("All Data ");
			for(Employee emp: list) {
				System.out.println(emp.getId());
				System.out.println(emp.getName());
				System.out.println(emp.getEmail());
				System.out.println(emp.getPhoneNo());
				System.out.println(emp.getPosition());
				System.out.println();
				System.out.println();
	
			}
		}
		
	}

//--------------------------FIND DATA -----------------------------------------------------------

	private void findById(Scanner scanner) {

		try {
		System.out.print("Find Employee Using ID: ");
		int findId = scanner.nextInt();

		Optional<Employee> opt = myService.findById(findId);

			System.out.println(opt);
			System.out.println();
		}
		catch(Exception e) {
			System.out.println("Invalid ID");
			System.out.println("Enter a valid ID");
		}
		
		
	}
	
//--------------------------EXIT --------------------------------------------

	private void exit() {
		System.exit(0);
		System.out.println("EXIT");
		System.out.println();

	}

}
