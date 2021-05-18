package com.ideal.studentlog.database.repositories;

import com.ideal.studentlog.database.models.SubjectDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectDetailsRepository extends JpaRepository<SubjectDetails, Integer>{
    List<SubjectDetails> findAll();
}
