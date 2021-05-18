package com.ideal.studentlog.database.repositories;

import com.ideal.studentlog.database.models.LeaveApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Integer> {
}
