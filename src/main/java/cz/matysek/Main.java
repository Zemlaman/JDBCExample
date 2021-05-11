package cz.matysek;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cars","root", "");

        Scanner sc = new Scanner(System.in);
        System.out.println("Hello, do you want do add (1) or delete (2)?");
        int dec = sc.nextInt();

        if(dec == 1){
            Scanner sc2 = new Scanner(System.in);
            System.out.println("Write manufacturer of the car you want to add.");
            String manufacturer = sc2.nextLine();
            System.out.println("Write model of the car you want to add.");
            String model = sc2.nextLine();
            System.out.println("Write some informations of the car you want to add.");
            String info = sc2.nextLine();
            System.out.println("Write price of the car you want to add.");
            int price = sc2.nextInt();
            System.out.println("Do you want to list whole database? 1 for yes 2 for no");
            int dec2 = sc2.nextInt();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO car VALUES(NULL, ?, ?, ?, ?)");
            preparedStatement.setString(1, manufacturer);
            preparedStatement.setString(2, model);
            preparedStatement.setInt(3, price);
            preparedStatement.setString(4, info);

            preparedStatement.executeUpdate();

            if(dec2 == 1) {
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM car");

                while (result.next()) {
                    String id = result.getString("car_id");
                    String man = result.getString("manufacturer");
                    String mod = result.getString("model");
                    int pr = result.getInt("price");
                    String in = result.getString("info");
                    System.out.println(id + "\t " + man + "\t; " + mod + "\t; " + pr + " eur" + "\t; " + in);
                }

                connection.close();
            } else {
                connection.close();
            }

            connection.close();
    } else if(dec == 2){
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM car");

            while (result.next()) {
                String id = result.getString("car_id");
                String man = result.getString("manufacturer");
                String mod = result.getString("model");
                int pr = result.getInt("price");
                String in = result.getString("info");
                System.out.println(id + "\t " + man + "\t; " + mod + "\t; " + pr + " eur" + "\t; " + in);
            }

            System.out.println("Write id of the row you want to delete.");
            int del = sc.nextInt();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM car where car_id = ?");
            preparedStatement.setInt(1, del);
            preparedStatement.executeUpdate();
        }
        connection.close();
    }
}
