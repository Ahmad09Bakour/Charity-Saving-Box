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
                    "INSERT INTO charity_budget_items (description, started, location, done, deadline, note) VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, request.getDescription());
            preparedStatement.setBoolean(2, request.isStarted());
            preparedStatement.setString(3, request.getLocation());
            preparedStatement.setBoolean(4, request.isDone());
            preparedStatement.setDate(5, request.getDeadLine());
            preparedStatement.setString(6, request.getNote());

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
                    "SELECT id, description, started, location, done, deadline, note FROM charity_budget_items");

            ResultSet resultSet = preparedStatement.executeQuery();

            List<CharityItem> response = new ArrayList<>();
            while (resultSet.next()){
                CharityItem charityItem = new CharityItem();
                charityItem.setId(resultSet.getLong("id"));
                charityItem.setDescription(resultSet.getString("description"));
                charityItem.setStarted(resultSet.getBoolean("started"));
                charityItem.setLocation(resultSet.getString("location"));
                charityItem.setDone(resultSet.getBoolean("done"));
                charityItem.setDeadline(resultSet.getDate("deadline"));
                charityItem.setNote(resultSet.getString("note"));

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
