package com.henry.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.henry.dao.DepartmentDao;
import com.henry.dao.EmployeeDao;
import com.henry.entity.Department;
import com.henry.entity.Employee;

@Controller
public class EmployeeHandler {
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private DepartmentDao departmentDao;
	
	@RequestMapping("/emps")
	public String list(Map<String, Object> map) {
		map.put("employees", employeeDao.getAll());
		return "list";
	}
	
	@RequestMapping("/emp")
	public String input(Map<String, Object> map) {
		map.put("departments", departmentDao.getDepartments());
		map.put("employee", new Employee()); //这里的Employee的属性有值的话，会自动回显
		return "input";
	}
	
	@RequestMapping(value = "/emp", method = RequestMethod.POST) 
	public String save(Employee employee) {
		employeeDao.save(employee);
		return "redirect:/emps";
	}
	
	@RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable(value="id") int id) {
		employeeDao.delete(id);
		return "redirect:/emps";
	}
	
	@RequestMapping(value = "/emp/{id}")
	public String input(@PathVariable(value="id") int id,Map<String,Object> map) {  //数据回显
		map.put("employee", employeeDao.get(id));
		map.put("departments", departmentDao.getDepartments());
		return "input";
	}
	
	@ModelAttribute
	public void getEmployee(@RequestParam(value="id", required=false) Integer id, Map<String, Object> map) {
		if(id!=null) {
			map.put("employee", employeeDao.get(id));
		}
	}
	
	@RequestMapping(value = "/emp", method = RequestMethod.PUT)
	public String update(Employee employee) {
		employeeDao.save(employee);
		return "redirect:/emps";
	}
}
