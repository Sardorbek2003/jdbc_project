package org.example.model;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private int id;
    private String username;
    private String phone_number;
    private String password;
    private String email;
    public User(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.username = resultSet.getString("name");
        this.email = resultSet.getString("email");
        this.phone_number = resultSet.getString("phone_number");
        this.password = resultSet.getString("password");
    }
}
