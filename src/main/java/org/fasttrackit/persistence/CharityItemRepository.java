package org.fasttrackit.persistence;

import org.fasttrackit.domain.CharityItem;
import org.fasttrackit.transfer.SaveCharityRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CharityItemRepository {

    public void createCharityItem(SaveCharityRequest request) throws SQLException {
        try(Connection connection = DatabaseConfiguration.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO charity_budget_items (description, started, done, deadline) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, request.getDescription());
            preparedStatement.setBoolean(2, request.isStarted());
            preparedStatement.setBoolean(3, request.isDone());
            preparedStatement.setDate(4, request.getDeadLine());

            preparedStatement.executeUpdate();
        }
    }

    public static void markCharityItemDone(long id, boolean done) throws SQLException {
        try(Connection connection = DatabaseConfiguration.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE charity_budget_items SET done=? WHERE id=?");
            preparedStatement.setBoolean(1, done);
            preparedStatement.setLong(2, id);

            preparedStatement.executeUpdate();
        }
    }

    public static List<CharityItem> getCharityItem() throws SQLException {
        try(Connection connection = DatabaseConfiguration.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, description, started, done, deadline FROM charity_budget_items");

            ResultSet resultSet = preparedStatement.executeQuery();

            List<CharityItem> response = new ArrayList<>();
            while (resultSet.next()){
                CharityItem charityItem = new CharityItem();
                charityItem.setId(resultSet.getLong("id"));
                charityItem.setDescription(resultSet.getString("description"));
                charityItem.setStarted(resultSet.getBoolean("started"));
                charityItem.setDone(resultSet.getBoolean("done"));
                charityItem.setDeadline(resultSet.getDate("deadline"));

                response.add(charityItem);
            }
            return response;
        }
    }

    public static void deleteCharityItem(long id) throws SQLException {
        try (Connection connection = DatabaseConfiguration.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM charity_budget_items WHERE id=?");

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        }

    }
}
