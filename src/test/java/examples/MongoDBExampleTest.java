package examples;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Пример работы с MongoDB из Java тестов
 * 
 * MongoDB - документо-ориентированная NoSQL база данных.
 * 
 * Основные операции:
 * - Create (Insert)
 * - Read (Find)
 * - Update
 * - Delete
 * 
 * Для запуска тестов нужен запущенный MongoDB (локально или через Docker)
 */
public class MongoDBExampleTest {

    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private MongoCollection<Document> collection;

    @BeforeAll
    static void setUpConnection() {
        // Подключение к MongoDB
        // Замените connectionString на ваш URL MongoDB
        String connectionString = "mongodb://localhost:27017";
        mongoClient = MongoClients.create(connectionString);
        
        // Выбираем базу данных (создается автоматически, если не существует)
        database = mongoClient.getDatabase("testdb");
    }

    @AfterAll
    static void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }

    @BeforeEach
    void setUpCollection() {
        // Получаем коллекцию (аналог таблицы в SQL)
        collection = database.getCollection("users");
        
        // Очищаем коллекцию перед каждым тестом
        collection.deleteMany(new Document());
    }

    /**
     * Пример 1: CREATE - Вставка одного документа
     */
    @Test
    void testInsertOneDocument() {
        // Создаем документ (аналог строки в SQL)
        Document user = new Document("name", "John Doe")
                .append("email", "john@example.com")
                .append("age", 30);
        
        // Вставляем документ
        collection.insertOne(user);
        
        // Проверяем, что документ был вставлен
        long count = collection.countDocuments();
        assertEquals(1, count);
    }

    /**
     * Пример 2: CREATE - Вставка нескольких документов
     */
    @Test
    void testInsertManyDocuments() {
        List<Document> users = new ArrayList<>();
        users.add(new Document("name", "John Doe").append("email", "john@example.com"));
        users.add(new Document("name", "Jane Doe").append("email", "jane@example.com"));
        users.add(new Document("name", "Bob Smith").append("email", "bob@example.com"));
        
        // Вставляем несколько документов
        collection.insertMany(users);
        
        // Проверяем количество
        long count = collection.countDocuments();
        assertEquals(3, count);
    }

    /**
     * Пример 3: READ - Поиск всех документов
     */
    @Test
    void testFindAllDocuments() {
        // Вставляем тестовые данные
        collection.insertOne(new Document("name", "John Doe").append("email", "john@example.com"));
        collection.insertOne(new Document("name", "Jane Doe").append("email", "jane@example.com"));
        
        // Находим все документы
        List<Document> users = collection.find().into(new ArrayList<>());
        
        assertEquals(2, users.size());
        assertEquals("John Doe", users.get(0).getString("name"));
    }

    /**
     * Пример 4: READ - Поиск по условию (WHERE в SQL)
     */
    @Test
    void testFindWithFilter() {
        // Вставляем тестовые данные
        collection.insertOne(new Document("name", "John Doe").append("age", 30));
        collection.insertOne(new Document("name", "Jane Doe").append("age", 25));
        collection.insertOne(new Document("name", "Bob Smith").append("age", 35));
        
        // Находим документы по условию (age > 30)
        List<Document> users = collection.find(Filters.gt("age", 30))
                .into(new ArrayList<>());
        
        assertEquals(1, users.size());
        assertEquals("Bob Smith", users.get(0).getString("name"));
    }

    /**
     * Пример 5: READ - Поиск одного документа
     */
    @Test
    void testFindOneDocument() {
        collection.insertOne(new Document("name", "John Doe").append("email", "john@example.com"));
        
        // Находим первый документ, соответствующий условию
        Document user = collection.find(Filters.eq("email", "john@example.com")).first();
        
        assertNotNull(user);
        assertEquals("John Doe", user.getString("name"));
    }

    /**
     * Пример 6: UPDATE - Обновление одного документа
     */
    @Test
    void testUpdateOneDocument() {
        // Вставляем документ
        collection.insertOne(new Document("name", "John Doe").append("email", "john@example.com"));
        
        // Обновляем документ
        collection.updateOne(
                Filters.eq("email", "john@example.com"),
                Updates.set("age", 31)
        );
        
        // Проверяем обновление
        Document user = collection.find(Filters.eq("email", "john@example.com")).first();
        assertEquals(31, user.getInteger("age"));
    }

    /**
     * Пример 7: UPDATE - Обновление нескольких документов
     */
    @Test
    void testUpdateManyDocuments() {
        // Вставляем тестовые данные
        collection.insertOne(new Document("name", "John Doe").append("status", "active"));
        collection.insertOne(new Document("name", "Jane Doe").append("status", "active"));
        collection.insertOne(new Document("name", "Bob Smith").append("status", "inactive"));
        
        // Обновляем все документы со status = "active"
        collection.updateMany(
                Filters.eq("status", "active"),
                Updates.set("status", "verified")
        );
        
        // Проверяем обновление
        long verifiedCount = collection.countDocuments(Filters.eq("status", "verified"));
        assertEquals(2, verifiedCount);
    }

    /**
     * Пример 8: DELETE - Удаление одного документа
     */
    @Test
    void testDeleteOneDocument() {
        // Вставляем тестовые данные
        collection.insertOne(new Document("name", "John Doe").append("email", "john@example.com"));
        collection.insertOne(new Document("name", "Jane Doe").append("email", "jane@example.com"));
        
        // Удаляем один документ
        collection.deleteOne(Filters.eq("email", "john@example.com"));
        
        // Проверяем удаление
        long count = collection.countDocuments();
        assertEquals(1, count);
        
        Document remaining = collection.find().first();
        assertEquals("Jane Doe", remaining.getString("name"));
    }

    /**
     * Пример 9: DELETE - Удаление нескольких документов
     */
    @Test
    void testDeleteManyDocuments() {
        // Вставляем тестовые данные
        collection.insertOne(new Document("name", "John Doe").append("status", "inactive"));
        collection.insertOne(new Document("name", "Jane Doe").append("status", "inactive"));
        collection.insertOne(new Document("name", "Bob Smith").append("status", "active"));
        
        // Удаляем все документы со status = "inactive"
        collection.deleteMany(Filters.eq("status", "inactive"));
        
        // Проверяем удаление
        long count = collection.countDocuments();
        assertEquals(1, count);
    }

    /**
     * Пример 10: Сложные запросы (AND, OR, IN)
     */
    @Test
    void testComplexQueries() {
        // Вставляем тестовые данные
        collection.insertOne(new Document("name", "John Doe").append("age", 30).append("city", "Moscow"));
        collection.insertOne(new Document("name", "Jane Doe").append("age", 25).append("city", "SPB"));
        collection.insertOne(new Document("name", "Bob Smith").append("age", 35).append("city", "Moscow"));
        
        // AND условие: age > 25 AND city = "Moscow"
        List<Document> users = collection.find(
                Filters.and(
                        Filters.gt("age", 25),
                        Filters.eq("city", "Moscow")
                )
        ).into(new ArrayList<>());
        
        assertEquals(2, users.size());
        
        // OR условие: age < 30 OR city = "SPB"
        List<Document> usersOr = collection.find(
                Filters.or(
                        Filters.lt("age", 30),
                        Filters.eq("city", "SPB")
                )
        ).into(new ArrayList<>());
        
        assertEquals(2, usersOr.size());
        
        // IN условие: city IN ["Moscow", "SPB"]
        List<Document> usersIn = collection.find(
                Filters.in("city", "Moscow", "SPB")
        ).into(new ArrayList<>());
        
        assertEquals(3, usersIn.size());
    }

    /**
     * Пример 11: Сортировка и лимит (ORDER BY, LIMIT в SQL)
     */
    @Test
    void testSortAndLimit() {
        // Вставляем тестовые данные
        collection.insertOne(new Document("name", "John Doe").append("age", 30));
        collection.insertOne(new Document("name", "Jane Doe").append("age", 25));
        collection.insertOne(new Document("name", "Bob Smith").append("age", 35));
        
        // Сортируем по age (по возрастанию) и берем первые 2
        List<Document> users = collection.find()
                .sort(new Document("age", 1))  // 1 = ascending, -1 = descending
                .limit(2)
                .into(new ArrayList<>());
        
        assertEquals(2, users.size());
        assertEquals("Jane Doe", users.get(0).getString("name"));  // Самый младший
        assertEquals("John Doe", users.get(1).getString("name"));
    }
}
