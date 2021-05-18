package com.ideal.studentlog.database.repositories;

import com.ideal.studentlog.database.models.ClassStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassStudentRepository extends JpaRepository<ClassStudent, Integer> {
}
