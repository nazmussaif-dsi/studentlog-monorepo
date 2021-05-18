package com.ideal.studentlog.database.repositories;

import com.ideal.studentlog.database.models.ClassDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassDetailsRepository extends JpaRepository<ClassDetails, Integer> {
}
