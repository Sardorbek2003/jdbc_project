package org.example.service;

import org.example.dao.CardDao;
import org.example.model.Card;

import java.sql.SQLException;
import java.util.List;

public class CardService {

    private final CardDao cardDao;

    public CardService(CardDao cardDao) {
        this.cardDao = cardDao;
    }

    public void createCard(String ownerName, String cardNumber, String password, String expireDate, int userId) throws SQLException {
        Card card = new Card();
        card.setOwnerName(ownerName);
        card.setCardNumber(cardNumber);
        card.setPassword(password);
        card.setExpireDate(expireDate);
        card.setUserId(userId);

        cardDao.createCard(card);
    }

    public Card getCard(int cardId) throws SQLException {
        return cardDao.readCard(cardId);
    }

    public List<Card> getMyCards(int userId) throws SQLException {
        return cardDao.getMyCards(userId);
    }

    public void deleteCard(int cardId, int userId) throws SQLException {
        cardDao.deleteCard(cardId,userId);
    }
}
