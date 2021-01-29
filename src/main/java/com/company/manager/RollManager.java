package com.company.manager;

import com.company.db.DBConnectionProvider;
import com.company.model.Roll;

import java.sql.*;

public class RollManager {

    private static Connection connection = DBConnectionProvider.getInstance().getConnection();

    public int addRoll(Roll roll) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into rolls(roll) values (?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, roll.getRoll());
        preparedStatement.executeUpdate();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            int anInt = generatedKeys.getInt(1);
            roll.setId(anInt);
        }
        return generatedKeys.getInt(1);
    }
}
