package com.company.manager;

import com.company.db.DBConnectionProvider;
import com.company.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private static Connection connection = DBConnectionProvider.getInstance().getConnection();

    public int addUser(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into users(first_name,last_name,email) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.executeUpdate();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            int anInt = generatedKeys.getInt(1);
            user.setId(anInt);
        }
        return generatedKeys.getInt(1);
    }

    public List<User> findUsersByName(String firstName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where first_name=?");
        preparedStatement.setString(1, firstName);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt(1));
            user.setFirstName(resultSet.getString(2));
            user.setLastName(resultSet.getString(3));
            user.setEmail(resultSet.getString(4));
            users.add(user);
        }

        return users;
    }

    public void assignRoll(int userId, int rollId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE bdgdb.users\n" +
                "SET \n" +
                "   roll_id=?\n" +
                "WHERE\n" +
                "    id= ?;");
        preparedStatement.setInt(1, rollId);
        preparedStatement.setInt(2, userId);
        preparedStatement.executeUpdate();
    }
}
