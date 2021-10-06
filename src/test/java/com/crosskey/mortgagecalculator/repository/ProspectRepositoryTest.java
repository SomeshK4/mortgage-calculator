package com.crosskey.mortgagecalculator.repository;

import com.crosskey.mortgagecalculator.domain.ProspectsEntity;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProspectRepositoryTest {

    @Autowired
    private ProspectRepository prospectRepository;

    @BeforeAll
    public void cleanUp() {
        prospectRepository.deleteAll();
    }

    @Test
    void testSaveProspects() {
        String prospect = "Test Customer want to borrow";
        ProspectsEntity prospectsEntity = new ProspectsEntity();
        prospectsEntity.setProspect(prospect);

        prospectRepository.saveAll(List.of(prospectsEntity));

        List<ProspectsEntity> prospectsEntities = prospectRepository.findAll();
        assertEquals(1, prospectsEntities.size());
        assertEquals(prospect ,prospectsEntities.get(0).getProspect());
    }
}
