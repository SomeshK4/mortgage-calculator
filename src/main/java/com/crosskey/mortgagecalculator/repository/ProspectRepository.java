package com.crosskey.mortgagecalculator.repository;

import com.crosskey.mortgagecalculator.domain.ProspectsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProspectRepository extends JpaRepository<ProspectsEntity, Long> {

}
