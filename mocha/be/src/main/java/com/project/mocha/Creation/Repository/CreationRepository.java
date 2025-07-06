package com.project.mocha.Creation.Repository;

import com.project.mocha.Creation.Entity.Creation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreationRepository extends JpaRepository<Creation, Integer>, CreationRepositoryCustom {

}