package org.fasttrackit.persistence;

import org.fasttrackit.transfer.SaveCharitySavingRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CharitySavingUnitRepository {

    public void createCharitySavingUnit(SaveCharitySavingRequest request) throws SQLException {
        try(Connection connection = DatabaseConfiguration.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO charity_saving_units (description, started, done, deadline) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, request.getDescription());
            preparedStatement.setBoolean(2, request.isStarted());
            preparedStatement.setBoolean(3, request.isDone());
            preparedStatement.setDate(4, request.getDeadLine());

            preparedStatement.executeUpdate();
        }
    }
}
