package org.fasttrackit.service;

import org.fasttrackit.persistence.CharitySavingUnitRepository;
import org.fasttrackit.transfer.SaveCharitySavingRequest;

import java.sql.SQLException;

public class CharitySavingUnitService {

    CharitySavingUnitRepository charitySavingUnitRepository = new CharitySavingUnitRepository();
    public void createCharitySavingUnit(SaveCharitySavingRequest request) throws SQLException {
        System.out.println("Creating the first Charity Saving Unit" + request);
        charitySavingUnitRepository.createCharitySavingUnit(request);
    }
}
