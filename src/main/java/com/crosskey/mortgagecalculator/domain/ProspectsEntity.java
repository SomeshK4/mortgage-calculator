package com.crosskey.mortgagecalculator.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROSPECTS")
public class ProspectsEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "PROSPECT")
    private String prospect;

    public String getProspect() {
        return prospect;
    }

    public void setProspect(String prospect) {
        this.prospect = prospect;
    }



}
