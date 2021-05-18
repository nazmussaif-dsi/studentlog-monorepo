package com.ideal.studentlog.database.repositories;

import com.ideal.studentlog.database.models.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Integer> {
    List<TestResult> findByStudentId(Integer id);
}
