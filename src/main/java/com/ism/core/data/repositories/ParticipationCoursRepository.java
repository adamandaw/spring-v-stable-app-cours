package com.ism.core.data.repositories;


import com.ism.core.data.entities.CoursEntity;
import com.ism.core.data.entities.ParticipationCoursEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipationCoursRepository extends JpaRepository<ParticipationCoursEntity,Long> {


}
