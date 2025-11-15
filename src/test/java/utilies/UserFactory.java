package utilies;

import api.rND21689GetZipPackages.models.User;

/**
 * Фабрика для создания тестовых пользователей.
 * Централизует создание стандартных пользователей и использует значения из properties.

 * Преимущества:
 * - Единая точка создания тестовых пользователей
 * - Использование значений из properties вместо хардкода
 * - Легко добавлять новые типы пользователей
 * - Улучшенная читаемость тестов
 */
public class UserFactory {
    
    private static final PropertiesReader propertiesReader = PropertiesReader.getInstance();
    
    /**
     * Создает пользователя Supervisor с полными правами.
     * 
     * @return User с правами администратора
     */
    public static User createSupervisor() {
        return User.builder()
                .name(getLoginFromProperties())
                .pass(getPasswordFromProperties())
                .isCanManageSolution(true)
                .isCanViewConfiguration(true)
                .build();
    }
    
    /**
     * Создает пользователя SVETuser с ограниченными правами.
     * 
     * @return User без прав управления решением
     */
    public static User createSvetUser() {
        return User.builder()
                .name("SVETuser")
                .pass(getPasswordFromProperties())
                .isCanManageSolution(false)
                .isCanViewConfiguration(true)
                .build();
    }
    
    /**
     * Создает пользователя с кастомными правами.
     * 
     * @param name имя пользователя
     * @param canManageSolution может ли управлять решением
     * @param canViewConfiguration может ли просматривать конфигурацию
     * @return User с указанными правами
     */
    public static User createCustomUser(String name, boolean canManageSolution, boolean canViewConfiguration) {
        return User.builder()
                .name(name)
                .pass(getPasswordFromProperties())
                .isCanManageSolution(canManageSolution)
                .isCanViewConfiguration(canViewConfiguration)
                .build();
    }
    
    /**
     * Создает пользователя с кастомным паролем.
     * 
     * @param name имя пользователя
     * @param password пароль
     * @param canManageSolution может ли управлять решением
     * @param canViewConfiguration может ли просматривать конфигурацию
     * @return User с указанными параметрами
     */
    public static User createCustomUser(String name, String password, boolean canManageSolution, boolean canViewConfiguration) {
        return User.builder()
                .name(name)
                .pass(password)
                .isCanManageSolution(canManageSolution)
                .isCanViewConfiguration(canViewConfiguration)
                .build();
    }
    
    /**
     * Получает логин из properties файла.
     * 
     * @return логин из properties или "Supervisor" по умолчанию
     */
    private static String getLoginFromProperties() {
        String login = propertiesReader.getProperty("login");
        return login != null && !login.isEmpty() ? login : "Supervisor";
    }
    
    /**
     * Получает пароль из properties файла.
     * 
     * @return пароль из properties или "BPMAdmin123!" по умолчанию
     */
    private static String getPasswordFromProperties() {
        String password = propertiesReader.getProperty("password");
        return password != null && !password.isEmpty() ? password : "BPMAdmin123!";
    }
}

