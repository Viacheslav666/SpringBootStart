package ru.skypro.lessons.springboot.weblibrary1.repository;

import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;

@Repository
public interface PagingAndSortingRepository extends org.springframework.data.repository.PagingAndSortingRepository<Employee, Integer> {

}
