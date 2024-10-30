package org.example.model;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Card {
    private int id;
    private String ownerName;
    private String cardNumber;
    private String password;
    private String expireDate;
    private int userId;

    public Card(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.ownerName = resultSet.getString("owner_name");
        this.cardNumber = resultSet.getString("card_number");
        this.password = resultSet.getString("password");
        this.expireDate = resultSet.getString("expire_date");
        this.userId = resultSet.getInt("user_id"); // Added to match the database schema
    }
}
