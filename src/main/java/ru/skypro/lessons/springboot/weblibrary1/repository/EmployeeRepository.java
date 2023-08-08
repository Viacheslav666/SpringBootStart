package ru.skypro.lessons.springboot.weblibrary1.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.weblibrary1.DTO.EmployeeFullInfo;
import ru.skypro.lessons.springboot.weblibrary1.DTO.EmployeeReport;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;

import java.util.List;
import java.util.Optional;
@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Integer>,
        PagingAndSortingRepository<Employee, Integer> {
    @Query("SELECT new ru.skypro.lessons.springboot.weblibrary1.DTO." +
            "EmployeeFullInfo(e.name , e.salary , p.name) " +
            "FROM Employee e join fetch Position p " +
            "WHERE e.position = p")
    List<EmployeeFullInfo> findAllEmployees();

    Optional<Employee> findFirstByOrderBySalaryDesc();


    @Query("SELECT new ru.skypro.lessons.springboot.weblibrary1.DTO." +
            "EmployeeFullInfo(e.name , e.salary , p.name) " +
            "FROM Employee e join fetch Position p " +
            "WHERE e.position = p AND p.id=?1")
    List<EmployeeFullInfo> findEmployeeByPosition(Integer position);


    @Query("SELECT new ru.skypro.lessons.springboot.weblibrary1.DTO." +
            "EmployeeFullInfo(e.name , e.salary , p.name) " +
            "FROM Employee e join fetch Position p " +
            "WHERE e.position = p AND e.id=?1")
    Optional<EmployeeFullInfo> findByIdFullInfo(Integer id);

    Optional<Employee> findById (Integer id);

    @Query(value = "SELECT e.name FROM Employee e")
    String getEmployeeByName();

    @Query("SELECT new ru.skypro.lessons.springboot.weblibrary1.DTO." +
            "EmployeeReport(e.department , COUNT(e.name) , MIN(e.salary), max(e.salary), avg(e.salary)) " +
            "FROM Employee e GROUP BY e.department")
    List<EmployeeReport> getReport();

}
