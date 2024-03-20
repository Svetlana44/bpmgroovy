package version_1_3.api.odata4;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AnnotationForTest {
/*    Для создания аннотации для пользовательского класса,
 вы можете создать свой собственный аннотационный тип,
  используя аннотацию `@Retention`, `@Target` и `@interface`. Например:

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Auth {
    // здесь вы можете определить свои собственные атрибуты для аннотации
}


Затем вы можете использовать эту аннотацию для вашего пользовательского класса:

@Auth
public class FrameODataTests {
    // ваш код класса
}


Кроме того, если вы хотите добавить логику выполнения ваших тестов с использованием этой аннотации,
 вы можете создать расширение JUnit Jupiter, используя аннотацию `@ExtendWith`. Например:

import org.junit.jupiter.api.extension.ExtendWith;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Auth {
    // здесь вы можете определить свои собственные атрибуты для аннотации
}


Затем создайте своё расширение JUnit Jupiter:

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;

public class AuthExtension implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return parameterContext.getParameter().getType().equals(Auth.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        // здесь вы можете добавить логику выполнения тестов с использованием аннотации Auth
        return new Auth();
    }
}


Затем примените ваше расширение к вашему тестовому классу:

@ExtendWith(AuthExtension.class)
public class FrameODataTests {
    // ваш код класса
}*/
}
