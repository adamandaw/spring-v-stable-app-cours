package com.ism.core.services;

import com.ism.core.data.entities.CoursEntity;
import com.ism.core.data.entities.ModuleEntity;
import com.ism.core.data.entities.SemestreEntity;
import com.ism.core.web.dto.request.CoursDtoRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CoursService {
    CoursEntity getByModule(ModuleEntity module);
    int updateHeurePlanifiorByCour(Long id,int nbrHeurePlanifier);
    int updateHeureEffectuer(Long id,int value);
    List<SemestreEntity> getAllSemestres();
    List<ModuleEntity> getAllModules();

    void store(CoursDtoRequest cours);

    Page<CoursEntity> getAllCoursByDoneFalse(Pageable pageable);

    int setCoursToIsDone(Long id);

    CoursEntity getCourById(Long id);
}
