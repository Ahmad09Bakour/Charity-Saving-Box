package org.fasttrackit.service;

import org.fasttrackit.domain.CharityItem;
import org.fasttrackit.persistence.CharityItemRepository;
import org.fasttrackit.transfer.MarkItemDoneRequest;
import org.fasttrackit.transfer.SaveCharityRequest;

import java.sql.SQLException;
import java.util.List;

public class CharityItemService {

    CharityItemRepository charityItemRepository = new CharityItemRepository();
    public void createCharityItem(SaveCharityRequest request) throws SQLException {
        System.out.println("Creating Charity item..." + request);
        charityItemRepository.createCharityItem(request);
    }

    public List<CharityItem> getCharityItem() throws SQLException {
        System.out.println("Retrieving charity items.");
        return CharityItemRepository.getCharityItem();
    }

    public static void markCharityItemDone(long id, MarkItemDoneRequest request) throws SQLException {
        System.out.println("Updating charity item" + id + " with " + request);

        CharityItemRepository.markCharityItemDone(id, request.isDone());
    }

    public void deleteCharityItem(long id) throws SQLException {
        System.out.println("Deleting the item " + id);
        CharityItemRepository.deleteCharityItem(id);
    }
}
