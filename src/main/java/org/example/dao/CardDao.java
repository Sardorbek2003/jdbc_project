package org.example.dao;

import org.example.model.Card;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardDao {

    public void createCard(Card card) throws SQLException {
        String sql = "INSERT INTO cards(owner_name, card_number, password, expire_date, user_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, card.getOwnerName());
            statement.setString(2, card.getCardNumber());
            statement.setString(3, card.getPassword());
            statement.setString(4, card.getExpireDate());
            statement.setInt(5, card.getUserId());
            statement.executeUpdate();
        }
    }

    public Card readCard(int cardId) throws SQLException {
        String sql = "SELECT * FROM cards WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cardId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Card(resultSet);
            }
            return null;
        }
    }

    public List<Card> getMyCards(int userId) throws SQLException {
        String sql = "SELECT * FROM cards WHERE user_id = ?";
        List<Card> cards = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Card card = new Card();
                card.setId(resultSet.getInt("id"));
                card.setOwnerName(resultSet.getString("owner_name"));
                card.setCardNumber(resultSet.getString("card_number"));
                card.setPassword(resultSet.getString("password"));
                card.setExpireDate(resultSet.getString("expire_date"));
                card.setUserId(resultSet.getInt("user_id"));
                cards.add(card);
            }
        }
        return cards;
    }

    public boolean isCardOwnedByUser(int cardId, int userId) throws SQLException {
        String query = "SELECT COUNT(*) FROM cards WHERE id = ? AND user_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, cardId);
            statement.setInt(2, userId);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next() && resultSet.getInt(1) > 0;
        }
    }

    public void deleteCard(int cardId, int userId) throws SQLException {
        if (isCardOwnedByUser(cardId, userId)) {
            String sql = "DELETE FROM cards WHERE id = ?";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, cardId);
                statement.executeUpdate();
            }
        } else {
            System.out.println("You don’t have a card with this ID.");
        }
    }
}
