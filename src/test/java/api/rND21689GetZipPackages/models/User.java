package api.rND21689GetZipPackages.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель пользователя для (пока API) тестов.
 * Использует Builder паттерн для создания объектов.

 * Пример использования:
 * <pre>
 * User user = User.builder()
 *     .name("Supervisor")
 *     .pass("BPMAdmin123!")
 *     .isCanManageSolution(true)
 *     .isCanViewConfiguration(true)
 *     .build();
 * </pre>
 * 
 * Или через TestUserFactory:
 * <pre>
 * User supervisor = TestUserFactory.createSupervisor();
 * </pre>
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    
    @JsonProperty("UserName")
    public String name;
    
    @JsonProperty("UserPassword")
    private String pass;
    
    private boolean isCanManageSolution;
    
    private boolean isCanViewConfiguration;
    
    /**
     * Валидация объекта после создания.
     * Можно добавить валидацию через @Builder(buildMethodName = "buildValidated")
     * или использовать Bean Validation (@NotNull, @NotBlank).
     * 
     * @return true если объект валиден
     */
    public boolean isValid() {
        return name != null && !name.isEmpty() 
            && pass != null && !pass.isEmpty();
    }
    
    /**
     * Возвращает строковое представление пользователя без пароля (для безопасности).
     * 
     * @return безопасное строковое представление
     */
    @Override
    public String toString() {
        return String.format("User{name='%s', isCanManageSolution=%s, isCanViewConfiguration=%s}", 
            name, isCanManageSolution, isCanViewConfiguration);
    }
}
