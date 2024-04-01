package errorparser;

import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

/*  Добавить зависимость в проект и пропери БД
url=jdbc:postgresql://127.0.0.1:5432/memTracker
username=postgres
password=password
driver-class-name=org.postgresql.Driver

<dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.6.0</version>
        </dependency>
 */
public class ErrorDB {

    private final Connection connection;
    private final Properties properties = new Properties();

    public ErrorDB() {
        try (InputStream in = ErrorDB.class.getClassLoader().getResourceAsStream("errorpage.properties")) {
            properties.load(in);

            Class.forName(properties.getProperty("driverDB-class-name"));
            connection = DriverManager.getConnection(
                    properties.getProperty("urlDB"),
                    properties.getProperty("userDB"),
                    properties.getProperty("passwordDB"));

        } catch (IOException e) {
            System.out.println("Проперти не загрузились.");
            throw new RuntimeException(e);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ErrorDB db = new ErrorDB();
        List<ErrorModel> errors = db.findLastErrors();
        if (errors.isEmpty()) {
            System.out.println("Ошибок не требующих установки нет.");
        } else {
            Set<String> packagies = new HashSet<>();
            errors.forEach(e -> packagies.add(e.packageName));
            System.out.println(packagies);
        }
    }

    @SneakyThrows
    private ErrorModel newErrorModel(ResultSet resultSet) {

        return ErrorModel.builder()
                .packageName(resultSet.getString("PackName"))
                .lastError(resultSet.getString("LastError"))
                .NeedInstall(resultSet.getBoolean("NeedInstall"))
                .ObjectName(resultSet.getString("Name"))
                .build();
    }

    public List<ErrorModel> findLastErrors() {
        List<ErrorModel> errors = new ArrayList<>();
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(
                StatementsSQL.ssmsSELECTerrorsNotNeedInstall.getStatement()
        )) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    errors.add(newErrorModel(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return errors;
    }
}