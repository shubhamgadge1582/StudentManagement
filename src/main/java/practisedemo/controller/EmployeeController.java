package practisedemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import practisedemo.model.Employee;
import practisedemo.service.EmployeeService;

@Controller
public class EmployeeController
{
	@Autowired
	private EmployeeService employeeservice;
	
	
   //Display the list of all employees
	
	@GetMapping("/")
	public String viewHomePage(Model m)
	{
		m.addAttribute("listEmployees",employeeservice.getAllEmployees());
		return "index";
	}
	
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model m) 
	{
		//create model attribute for bind the form data
		Employee e=new Employee();
		m.addAttribute("employee",e);
		return "newEmployee";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee e)
	{
		//save employee to database
		employeeservice.saveEmployee(e);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable (value="id") long id,Model m)
	{
		//get employee from service
		
		Employee e=employeeservice.getEmployeeById(id);
		
		//set employee as model attribute
		
		m.addAttribute("employee",e);
		return "update_employee";
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable (value="id") long id)
	{
		//delete employee by id
		
		this.employeeservice.deleteEmployeeById(id);
		return "redirect:/";
	}
}
