package utilies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Singleton класс для чтения properties файлов.
 * Гарантирует единственный экземпляр и централизованное управление конфигурацией.
 * Поддерживает загрузку нескольких файлов properties с кэшированием.
 */
public class PropertiesReader {
    private static PropertiesReader instance;
    /**
     * Отдельный объект для синхронизации вместо синхронизации по классу.
     * Преимущества:
     * - Более явный и понятный код
     * - Меньше вероятность конфликтов с другими синхронизациями
     * - Не блокирует весь класс PropertiesReader.class
     * Альтернатива: synchronized (PropertiesReader.class) - работает, но менее предпочтительно
     */
    private static final Object lock = new Object();
    
    /**
     * Кэш загруженных properties файлов.
     * Ключ - путь к файлу, значение - загруженные properties.
     */
    private final Map<String, Properties> propertiesCache = new ConcurrentHashMap<>();
    
    private static final Logger LOG = LoggerFactory.getLogger(PropertiesReader.class);
    
    /**
     * Дефолтный файл properties для обратной совместимости.
     * Используется в методах без указания файла.
     */
    private static final String DEFAULT_PROPERTIES_FILE = "configs/stands.properties";

    /**
     * Приватный конструктор для предотвращения создания экземпляров извне.
     * Не загружает файлы при создании - используется ленивая загрузка.
     */
    private PropertiesReader() {
        // Ленивая загрузка - файлы будут загружены при первом обращении
    }

    /**
     * Получение единственного экземпляра класса (ленивая инициализация с двойной проверкой).
     *
     * @return экземпляр PropertiesReader
     */
    public static PropertiesReader getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new PropertiesReader();
                }
            }
        }
        return instance;
    }

    /**
     * Получение properties из указанного файла (с кэшированием).
     * Файл загружается только один раз при первом обращении.
     *
     * @param propertiesFile путь к файлу properties относительно classpath
     * @return объект Properties
     */
    public Properties getPropertiesFromFile(String propertiesFile) {
        return propertiesCache.computeIfAbsent(propertiesFile, this::loadProperties);
    }

    /**
     * Получение properties из дефолтного файла (для обратной совместимости).
     *
     * @return объект Properties из дефолтного файла
     */
    public Properties getProperties() {
        return getPropertiesFromFile(DEFAULT_PROPERTIES_FILE);
    }

    /**
     * Загрузка properties из файла.
     *
     * @param propertiesFile путь к файлу properties относительно classpath
     * @return загруженный объект Properties
     */
    private Properties loadProperties(String propertiesFile) {
        Properties props = new Properties();
        try (InputStream in = PropertiesReader.class.getClassLoader().getResourceAsStream(propertiesFile)) {
            if (in == null) {
                LOG.error("Файл properties не найден: {}", propertiesFile);
                throw new RuntimeException("Файл properties не найден: " + propertiesFile);
            }
            props.load(in);
            LOG.info("Properties успешно загружены из файла: {}", propertiesFile);
        } catch (IOException e) {
            LOG.error("Ошибка при загрузке properties из файла: {}", propertiesFile, e);
            throw new RuntimeException("Ошибка при загрузке properties: " + e.getMessage(), e);
        }
        return props;
    }

    /**
     * Получение значения свойства по ключу из дефолтного файла.
     *
     * @param key ключ свойства
     * @return значение свойства или null, если ключ не найден
     */
    public String getProperty(String key) {
        return getProperty(DEFAULT_PROPERTIES_FILE, key);
    }

    /**
     * Получение значения свойства по ключу из дефолтного файла с значением по умолчанию.
     *
     * @param key          ключ свойства
     * @param defaultValue значение по умолчанию, если ключ не найден
     * @return значение свойства или defaultValue
     */
    public String getProperty(String key, String defaultValue) {
        return getPropertyFromFile(DEFAULT_PROPERTIES_FILE, key, defaultValue);
    }

    /**
     * Получение значения свойства по ключу из указанного файла.
     *
     * @param propertiesFile путь к файлу properties
     * @param key            ключ свойства
     * @return значение свойства или null, если ключ не найден
     */
    public String getPropertyFromFileByKey(String propertiesFile, String key) {
        Properties props = getPropertiesFromFile(propertiesFile);
        return props.getProperty(key);
    }

    /**
     * Получение значения свойства по ключу из указанного файла с значением по умолчанию.
     *
     * @param propertiesFile путь к файлу properties
     * @param key            ключ свойства
     * @param defaultValue   значение по умолчанию, если ключ не найден
     * @return значение свойства или defaultValue
     */
    public String getPropertyFromFile(String propertiesFile, String key, String defaultValue) {
        Properties props = getPropertiesFromFile(propertiesFile);
        return props.getProperty(key, defaultValue);
    }

    /**
     * Перезагрузка properties из файла (полезно для тестов или динамической конфигурации).
     * Удаляет файл из кэша и загружает заново.
     *
     * @param propertiesFile путь к файлу properties
     */
    public void reloadProperties(String propertiesFile) {
        synchronized (lock) {
            propertiesCache.remove(propertiesFile);
            getPropertiesFromFile(propertiesFile); // Загрузит заново
            LOG.info("Properties перезагружены из файла: {}", propertiesFile);
        }
    }

    /**
     * Очистка кэша properties.
     */
    public void clearCache() {
        synchronized (lock) {
            propertiesCache.clear();
            LOG.info("Кэш properties очищен");
        }
    }
}

