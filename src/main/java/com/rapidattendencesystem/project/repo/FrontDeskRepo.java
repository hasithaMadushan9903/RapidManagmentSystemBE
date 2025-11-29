package com.rapidattendencesystem.project.repo;

import com.rapidattendencesystem.project.entity.FrontDesk;
import com.rapidattendencesystem.project.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FrontDeskRepo extends JpaRepository<FrontDesk, Integer> {
    FrontDesk findFrontDeskByFcode(String mcode);
    List<FrontDesk> findByIsActiveOrderByIdDesc(Boolean isActive);
}
