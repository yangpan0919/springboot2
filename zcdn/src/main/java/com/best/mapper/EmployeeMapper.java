package com.best.mapper;

import com.best.bean.Employee;
import com.best.bean.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//@Mapper或者@MapperScan将接口扫描装配到容器中
//@Mapper
public interface EmployeeMapper {

     Employee getEmpById(Long id);

     int insertEmp(Employee employee);

     Integer insertEmps(List<Employee> list);

    List<Permission> getUserById(@Param(value = "userId") Integer userId);
    void createTable();
}
