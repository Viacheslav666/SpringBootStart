package ru.skypro.lessons.springboot.weblibrary1.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Report;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends CrudRepository<Report, Integer> {

    List<Report> findAll();
    @Query("SELECT new com.example.weblibrary1.pojo.Report(" +
            "p.positionName, COUNT (e.id), MAX (e.salary), MIN (e.salary), AVG (e.salary)) " +
            "FROM Employee e join  fetch Position p " +
            "where e.position = p " +
            "Group by p.positionId " )
    List<Report> createReport();



    @Override
    Optional<Report> findById(Integer integer);
}
