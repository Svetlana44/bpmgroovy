package errorparser;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
    private static final Logger LOG = LoggerFactory.getLogger(ErrorPage.class.getName());

    private final Connection connection;
    private final StatementsSQL statementsSQL;

    private final Properties properties = new Properties();
    public ErrorDB() {
        try (InputStream in = ErrorDB.class.getClassLoader().getResourceAsStream("errorpage.properties")) {
            properties.load(in);

            Class.forName(properties.getProperty("driverDB-class-name"));
            connection = DriverManager.getConnection(
                    properties.getProperty("urlDB"),
                    properties.getProperty("userDB"),
                    properties.getProperty("passwordDB"));
            LOG.info("Загружены errorpage.properties для БД.");
            statementsSQL = new StatementsSQL(this);

        } catch (IOException e) {
            System.out.println("Проперти не загрузились.");
            throw new RuntimeException(e);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Properties getProperties() {
        return properties;
    }

//    public static void main(String[] args) {
//        ErrorDB db = new ErrorDB();
//        List<ErrorModel> errors = db.findLastErrors();
//        if (errors.isEmpty()) {
//            System.out.println("Ошибок не требующих установки нет.");
//        } else {
//            Set<String> packagies = new HashSet<>();
//            errors.forEach(e -> packagies.add(e.packageName));
//            System.out.println(packagies);
//        }
//    }

    @SneakyThrows
    private ErrorModel newErrorModel(ResultSet resultSet) {

        return ErrorModel.builder()
                .packageName(resultSet.getString("PackName"))
                .lastError(resultSet.getString("LastError"))
                .needInstall(resultSet.getBoolean("NeedInstall"))
                .ObjectName(resultSet.getString("Name"))
                .build();
    }

    public List<ErrorModel> findLastErrors() {
        List<ErrorModel> errors = new ArrayList<>();
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(
                statementsSQL.ssmsSELECTerrors
        )) {
            LOG.info("Запрос к БД.");
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

    public List<ErrorModel> findLastErrorsNotNeedInstal() {
        List<ErrorModel> errors = new ArrayList<>();
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(
                statementsSQL.ssmsSELECTerrorsNotNeedInstall
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