package com.project.mocha.Platform.Repository;

import com.project.mocha.Platform.Entity.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformRepository  extends JpaRepository<Platform, Integer> {
}
