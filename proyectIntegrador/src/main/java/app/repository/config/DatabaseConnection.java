package app.repository.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:sqlserver://127.0.0.1:1434;"
            + "databaseName=CineMaxCesde;"
            + "user=sa;"
            + "password=An@lyt!cs_S3cur3_Pr0j3ct#2026$;"
            + "trustServerCertificate=true;"
            + "encrypt=true;";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
