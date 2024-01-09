package com.artpro.artpro.heart.repository;

import com.artpro.artpro.heart.entity.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartRepository extends JpaRepository<Heart, Long> {
}
