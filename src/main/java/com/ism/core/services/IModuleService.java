package com.ism.core.services;

import com.ism.core.data.entities.*;
import com.ism.core.web.dto.request.SessionCoursDtoRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IModuleService {
    List<ModuleEntity> getAll();
ModuleEntity getById(Long id);
}
