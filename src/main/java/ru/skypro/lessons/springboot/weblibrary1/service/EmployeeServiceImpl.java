package ru.skypro.lessons.springboot.weblibrary1.service;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary1.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary1.DTO.FullInfo;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary1.repository.EmployeeRepository;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@EqualsAndHashCode
@ToString
@Service
public class EmployeeServiceImpl implements EmployeeService{
    public EmployeeRepository employeeRepository = null;
    ObjectMapper objectMapper = new ObjectMapper();

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }


    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAllEmployees().stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> withHighestSalary() {
       return employeeRepository.withHighestSalary().stream()
               .map(EmployeeDTO::fromEmployee)
               .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> employeesPosition(Optional position) {
         return employeeRepository.getEmployeesByName(position).stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());

    }

    @Override
    public List<FullInfo> fullInfo(int id) {
        return employeeRepository.getEmployeesFullInfo(id).stream()
                .map(FullInfo::fromEmployeeFullInfo)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> getEmployeeWithPaging(int pageIndex) {
        PageRequest employeePage = PageRequest.of(pageIndex,10);
Page<Employee> employeeDTOS =  employeeRepository.findAll(employeePage);
 return employeeDTOS.stream()
        .map(EmployeeDTO::fromEmployee).
        collect(Collectors.toList());
    }

    @Override
    public void upload(MultipartFile file) throws IOException {
        Employee employee = objectMapper.readValue((JsonParser) file, Employee.class);
        employeeRepository.save(employee);

        ;
    }

}



