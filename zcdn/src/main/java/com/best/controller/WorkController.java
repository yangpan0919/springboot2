package com.best.controller;


import com.best.bean.Employee;
import com.best.mapper.EmployeeMapper;
import com.best.service.interfaces.WorkServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by EDZ on 2018/11/6.
 */
@RestController
public class WorkController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private WorkServiceI workService;

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String test(){


        return  "sucess";
    }
    @RequestMapping(value = "/getEmp", method = RequestMethod.GET)
    public Employee getEmp( Long id){
        return workService.getEmpByRedis(id);
    }

    @RequestMapping(value = "/insertEmps", method = RequestMethod.GET)
    public Employee insertEmps(){
        List<Employee> e = new ArrayList<>();

        Employee employee = new Employee();
        employee.setLastName("杨一");
        e.add(employee);
        employee = new Employee();
        employee.setLastName("杨二");
        e.add(employee);
        employee = new Employee();
        employee.setLastName("杨三");
        e.add(employee);
        employee = new Employee();
        employee.setLastName("杨四");
        e.add(employee);
        employee = new Employee();
        employee.setLastName("杨五");
        e.add(employee);
        employee = new Employee();
        employee.setLastName("杨六");
        e.add(employee);
        employee = new Employee();
        employee.setLastName("杨七");
        e.add(employee);
        int i = employeeMapper.insertEmps(e);
        System.out.println(i);
        return employee;
    }
}
