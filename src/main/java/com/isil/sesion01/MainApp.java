package com.isil.sesion01;

import java.sql.*;

public class MainApp {

    public static void main(String[] args) throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Prueba",
                "root",
                "root"
        );

        Statement stmt = con.createStatement();

        int resultUpdate = stmt.executeUpdate("update Users set city='Rosario' where name='Messi'");

        /*ResultSet result = stmt.executeQuery("select * from Users");

        while (result.next()) {
            System.out.println(result.getString("idUser"));
            System.out.println(result.getString("name"));
            System.out.println(result.getString("phone"));
            System.out.println(result.getString("city"));
        }*/

        Statement stmt2 = con.createStatement();

        ResultSet resultSet = stmt2.executeQuery("select * from Users where name='Ronaldo'");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("name") + "\n" +
                    resultSet.getString("phone") + "\n" +
                    resultSet.getString("city"));

        }

        PreparedStatement preparedStatement =
                con.prepareStatement("Select * from Users where name=? and city=?");

        preparedStatement.setString(1, "Cueva");
        preparedStatement.setString(2, "Lima");

        ResultSet resultSet2 = preparedStatement.executeQuery();

        while (resultSet2.next()) {
            System.out.println(resultSet2.getString("name") + "\n" +
                    resultSet2.getString("phone") + "\n" +
                    resultSet2.getString("city"));
        }


        Statement stCreate = con.createStatement();

        int filasAfectadas = stCreate.executeUpdate("UPDATE USERS SET NAME='JUAN' WHERE IDUSER=1");
        System.out.println("Filas afectadas: " + filasAfectadas);

        // 3.2 Statement consulta
        PreparedStatement preparedStatement2 =
                con.prepareStatement("select * from Users where idUser=?");

        preparedStatement2.setInt(1, 1);

        // 4. Ejecutar query
        ResultSet resultSet3 = preparedStatement2.executeQuery();

        // 5. Recorrer resultados
        while (resultSet3.next()) {
            System.out.println(resultSet3.getString("name") + "\n" +
                    resultSet3.getString("phone") + "\n" +
                    resultSet3.getString("city"));
        }
        // 6. Cerrar conexion
        con.close();


    }
}
