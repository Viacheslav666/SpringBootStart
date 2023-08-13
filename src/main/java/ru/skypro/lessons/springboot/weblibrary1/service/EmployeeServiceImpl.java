package ru.skypro.lessons.springboot.weblibrary1.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary1.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary1.DTO.EmployeeFullInfo;
import ru.skypro.lessons.springboot.weblibrary1.DTO.EmployeeReport;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary1.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.weblibrary1.repository.PagingAndSortingRepository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Service

public class EmployeeServiceImpl implements EmployeeService {

    private final  EmployeeRepository employeeRepository;

    private final PagingAndSortingRepository pagingAndSortingRepository;

    Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Value("${app.env}")
    private String test;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PagingAndSortingRepository pagingAndSortingRepository) {
        this.employeeRepository = employeeRepository;
        this.pagingAndSortingRepository = pagingAndSortingRepository;
    }


    @Override
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeFullInfo> getAllEmployees() {
        logger.info("Was invoked method for getAllEmployees");
        List<EmployeeFullInfo> employeeFullInfoList = employeeRepository.findAllEmployees();
        return employeeFullInfoList;
    }
    @Override
    public List<EmployeeReport> getReport() {
        logger.info("Was invoked method for creating report");
        List <EmployeeReport> employeeReportList= employeeRepository.getReport();
        return employeeReportList;
    }


    @Override
    public EmployeeDTO withHighestSalary() {
        logger.info("Was invoked method for getting employee with the highest salary");
        EmployeeDTO employeeDTO = EmployeeDTO.fromEmployee(employeeRepository
                .findFirstByOrderBySalaryDesc()
                .orElseThrow(() -> {
                    IllegalArgumentException e = new IllegalArgumentException("Данные в таблице отсутсвуют");
                    return e;
                }));
        return employeeDTO;
    }

    @Override
    public List<EmployeeFullInfo> employeesPosition(Integer position) {
        logger.info("Was invoked method for getting employees with position {}", position);

        List<EmployeeFullInfo> employeeFullInfoList = employeeRepository.findEmployeeByPosition(position);

        return employeeFullInfoList;
    }


    @Override
    public EmployeeFullInfo fullInfo(int id) {
        logger.info("Was invoked method for getting employee with short description with id={}", id);
        EmployeeFullInfo employeeFullInfo = employeeRepository.findByIdFullInfo(id)
                .orElseThrow(() -> {
                    IllegalArgumentException e = new IllegalArgumentException("Введен не корректный ID");
                    logger.error("Received the invalid id {} ", id);
                    return e;
                });
        logger.debug("Received the employee with short description {}", employeeFullInfo);
        return employeeFullInfo;
    }

    @Override
    public List<EmployeeDTO> getEmployeeWithPaging(int pageIndex) {
        logger.info("Was invoked method for getting employees on page {} with size=10", pageIndex);
        Page<Employee> employeePage = pagingAndSortingRepository.findAll(PageRequest.of(pageIndex, 10));
        List<Employee> employeeList = employeePage.stream().toList();
        List<EmployeeDTO> employeeDTOList = employeeList.stream().
                map(EmployeeDTO::fromEmployee).collect(Collectors.toList());

        return employeeDTOList;
    }

    @Override
    public EmployeeDTO upload(MultipartFile multipartFile) throws IOException {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        try (InputStream inputStream = multipartFile.getInputStream()) {
            logger.info("Was invoked method for getting employee from file {}", multipartFile);
            int streamSize = inputStream.available();
            byte[] bytes = new byte[streamSize];
            inputStream.read(bytes);
            String json = new String(bytes, StandardCharsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            employeeDTO = objectMapper.readValue(json, EmployeeDTO.class);
        } catch (IOException e) {
            logger.error("Employee wasn't getting", e);
            e.printStackTrace();
        }
        return employeeDTO;

    }
    @Override
    public EmployeeDTO getEmployeeById(Integer id) {
        logger.info("Was invoked method for getting employee with id={}", id);
        EmployeeDTO employeeDTO = EmployeeDTO.fromEmployee(employeeRepository
                .findById(id)
                .orElseThrow(() -> {
                    IllegalArgumentException e = new IllegalArgumentException("Введен не корректный ID");
                    logger.error("Received the invalid id {} ", id, e);
                    return e;
                }));
        logger.debug("Received the employee {}", employeeDTO);
        return employeeDTO;
    }

}