package com.company.app;

import com.company.manager.RollManager;
import com.company.manager.UserManager;
import com.company.model.Roll;
import com.company.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ApplicationJdbc {
    static Scanner scanner = new Scanner(System.in);
    static UserManager userManager = new UserManager();
    static RollManager rollManager = new RollManager();

    public static void main(String[] args) throws SQLException {
        while (true) {
            System.out.println("Press 1 to add user");
            System.out.println("Press 2 to add roll");
            System.out.println("Press 3 for assigning roll to user");
            System.out.println("Press 4 to find user");

            switch (scanner.nextInt()) {


                case 1:
                    System.out.println("Please enter user first name,ladst name and email");
                    User user = new User();
                    user.setFirstName(scanner.next());
                    user.setLastName(scanner.next());
                    user.setEmail(scanner.next());
                    System.out.println("User by id  " + userManager.addUser(user) + "  created");
                    break;
                case 2:
                    System.out.println("Please enter roll name");
                    Roll roll = new Roll();
                    roll.setRoll(scanner.next());
                    System.out.println("Roll by id  " + rollManager.addRoll(roll) + "  created");
                    break;
                case 3:
                    System.out.println("Please input user id and roll id");
                    userManager.assignRoll(scanner.nextInt(), scanner.nextInt());
                    break;
                case 4:
                    System.out.println("Please enter user's name");
                    List<User> users = userManager.findUsersByName(scanner.next());
                    for (User u : users) {
                        System.out.println(u);
                    }

            }

        }
    }
}
