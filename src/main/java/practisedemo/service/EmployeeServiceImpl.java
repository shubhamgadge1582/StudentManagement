package practisedemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import practisedemo.model.Employee;
import practisedemo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository emprepo;
	@Override
	public List<Employee> getAllEmployees() 
	{
		
		return emprepo.findAll();
	}
	@Override
	public void saveEmployee(Employee employee)
	{
		this.emprepo.save(employee);
		
	}
	@Override
	public Employee getEmployeeById(long id)
	{
		Optional<Employee> optional=emprepo.findById(id);
		Employee e=null;
		if(optional.isPresent())
		{
			e=optional.get();
		}else 
		{
			throw new RuntimeException("Employee not found for id :: "+id);
		}
		return e;
	}
	@Override
	public void deleteEmployeeById(long id) 
	{
	   this.emprepo.deleteById(id);
	   
	}

}
