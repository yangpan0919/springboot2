package com.best.service;

import com.best.bean.Employee;
import com.best.mapper.DepartmentMapper;
import com.best.mapper.EmployeeMapper;
import com.best.service.interfaces.WorkServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by EDZ on 2018/11/21.
 */
@Service
public class WorkServiceImpl implements WorkServiceI{
    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;



    @Override
    @Cacheable(value="emps", key="#id")
    public Employee getEmpByRedis(Long id) {
        System.out.println("-------------------------没有从redis中取值-------------------------------------------");
        return employeeMapper.getEmpById(id);
    }

}
