package com.library.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    // Mettre à jour l'URL pour la connexion à la base de données, selon la configuration exacte de votre serveur
    private static final String URL = "jdbc:mysql://mysql-1c441eed-hamdounechaymae-7b98.d.aivencloud.com:10809/library_db";
    private static final String USER = "avnadmin";
    private static final String PASSWORD = "AVNS_Ck7qkRKAw-ukIunRSgB";

    // Méthode pour obtenir la connexion
    public static Connection getConnection() throws SQLException {
        try {
            // Charger le driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Retourner la connexion avec les paramètres de connexion
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            // Si le driver est introuvable, cela lance une exception spécifique
            throw new SQLException("MySQL JDBC driver not found.", e);
        } catch (SQLException e) {
            // Si une erreur SQL se produit, l'exception est lancée
            throw new SQLException("Database connection error: " + e.getMessage(), e);
        }
    }
}
