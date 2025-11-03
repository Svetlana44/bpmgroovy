### При использовании протокола OData необходимо учитывать следующие ограничения:

- невозможно создать системных пользователей;
  -- Системный пользователь обладает полными правами доступа и максимальным набором лицензий. Такой пользователь может
  быть только один.
  В BPMSoft системным пользователем является «Supervisor».
- невозможно задать культуру возвращаемых данных. Культура определяется культурой пользователя, от имени которого
  выполняется запрос;
- тело ответа на запрос может содержать максимум 20 000 строк;
- пакетный запрос($batch) может содержать максимум 100 подзапросов;
- максимальный размер файла, который можно загрузить с помощью запроса, задается системной настройкой «Максимальный
  размер файла»/«MaxFileSize» (по умолчанию — 10 Мб).

### для запуска через bash(консоль) тестов указанных в файле

src\test\resources\FailedTests.txt

создадим файл rerun.sh в папке с проектом
(если windows-нужно стававить спец терминал Linux)
в файле пишем

#### #!/bin/sh

(можно скопировать из файла gradlew-там немного по др.)

#### !/usr/bin/env sh

### Модели данных

Для того, чтобы заменить пустое значение null на пустую строку в Lombok,
можно использовать аннотацию @JsonInclude с параметром Include.NON_NULL.
Добавьте аннотацию @JsonInclude(Include.NON_NULL) к вашему классу Contact,
и это позволит сериализатору Jackson исключать значения null при преобразовании объекта в JSON.

````java

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Contact {
// Ваши поля класса здесь
}
````

### JsonSchema в RestAssured

JsonSchema в RestAssured - это инструмент, позволяющий проверить,
что возвращаемый JSON-ответ соответствует определенной схеме (JSON-схеме).
JSON-схема определяет ожидаемую структуру данных в JSON-ответе, включая типы данных,
обязательные и дополнительные поля, ограничения на значения и другие правила.
RestAssured позволяет использовать JSON-схему для автоматической проверки соответствия JSON-ответа этой схеме,
что помогает обеспечить правильность данных, возвращаемых из api.
// https://mvnrepository.com/artifact/io.rest-assured/json-schema-validator
implementation 'io.rest-assured:json-schema-validator:5.4.0'

````java
import java.net.URL;

public class Test {
    public static void test() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .get(URL)
                .then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(schema));
    }
}
````

Пример схемы

````java
String JsonSchema =
        """
                {
                    "type": "object",
                    "properties": {
                        "@odata.context": {"type": "string"},
                        "value": {
                            "type": "array",
                            "items": {
                                "type": "object",
                                "properties": {
                                    "Id": {"type": "string"},
                                    "Name": {"type": "string"}
                                },
                                "required": ["Id", "Name"]
                            }
                        }
                    },
                    "required": ["@odata.context", "value"]
                }""";
````

Для обрезания ZWNBSP (неразрывного пробела) в тексте используется

````
str.replace("\uFEFF", "").replace("\u200B", "");
````

для поиска в ответе

#### "message": "Not found"

````
{
    "error": {
        "code": "1",
        "message": "Not found",
        "innererror": {
            "message": "Not found",
            "type": "BPMSoft.Web.OData.Exceptions.GenericODataNotFoundException",
            "stacktrace": "   at BPMSoft.Web.OData.Controllers.BaseGenericODataController`1.ThrowNotFound()\r\n   at lambda_method(Closure , Object , Object[] )\r\n   at System.Web.Http.Controllers.ReflectedHttpActionDescriptor.ActionExecutor.<>c__DisplayClass6_2.<GetExecutor>b__2(Object instance, Object[] methodParameters)\r\n   at System.Web.Http.Controllers.ReflectedHttpActionDescriptor.ExecuteAsync(HttpControllerContext controllerContext, IDictionary`2 arguments, CancellationToken cancellationToken)\r\n--- End of stack trace from previous location where exception was thrown ---\r\n   at System.Runtime.ExceptionServices.ExceptionDispatchInfo.Throw()\r\n   at System.Runtime.CompilerServices.TaskAwaiter.HandleNonSuccessAndDebuggerNotification(Task task)\r\n   at System.Web.Http.Controllers.ApiControllerActionInvoker.<InvokeActionAsyncCore>d__1.MoveNext()\r\n--- End of stack trace from previous location where exception was thrown ---\r\n   at System.Runtime.ExceptionServices.ExceptionDispatchInfo.Throw()\r\n   at System.Runtime.CompilerServices.TaskAwaiter.HandleNonSuccessAndDebuggerNotification(Task task)\r\n   at System.Web.Http.Filters.ActionFilterAttribute.<CallOnActionExecutedAsync>d__6.MoveNext()\r\n--- End of stack trace from previous location where exception was thrown ---\r\n   at System.Runtime.ExceptionServices.ExceptionDispatchInfo.Throw()\r\n   at System.Web.Http.Filters.ActionFilterAttribute.<CallOnActionExecutedAsync>d__6.MoveNext()\r\n--- End of stack trace from previous location where exception was thrown ---\r\n   at System.Runtime.ExceptionServices.ExceptionDispatchInfo.Throw()\r\n   at System.Runtime.CompilerServices.TaskAwaiter.HandleNonSuccessAndDebuggerNotification(Task task)\r\n   at System.Web.Http.Filters.ActionFilterAttribute.<ExecuteActionFilterAsyncCore>d__5.MoveNext()\r\n--- End of stack trace from previous location where exception was thrown ---\r\n   at System.Runtime.ExceptionServices.ExceptionDispatchInfo.Throw()\r\n   at System.Runtime.CompilerServices.TaskAwaiter.HandleNonSuccessAndDebuggerNotification(Task task)\r\n   at System.Web.Http.Controllers.ActionFilterResult.<ExecuteAsync>d__5.MoveNext()\r\n--- End of stack trace from previous location where exception was thrown ---\r\n   at System.Runtime.ExceptionServices.ExceptionDispatchInfo.Throw()\r\n   at System.Runtime.CompilerServices.TaskAwaiter.HandleNonSuccessAndDebuggerNotification(Task task)\r\n   at System.Web.Http.Filters.AuthorizationFilterAttribute.<ExecuteAuthorizationFilterAsyncCore>d__3.MoveNext()\r\n--- End of stack trace from previous location where exception was thrown ---\r\n   at System.Runtime.ExceptionServices.ExceptionDispatchInfo.Throw()\r\n   at System.Runtime.CompilerServices.TaskAwaiter.HandleNonSuccessAndDebuggerNotification(Task task)\r\n   at System.Web.Http.Dispatcher.HttpControllerDispatcher.<SendAsync>d__15.MoveNext()"
        }
    }
}
````

использовать

````
 Assertions.assertEquals("Not found", responseDel.path(("error.message")));
````

Ошибка для gitlab-runner в Windows 11
"shell" executor **"pwsh"** переименовали в **powershell**

````
Running with gitlab-runner 16.9.1 (782c6ecb)
on 2duscrib yryxg5Zo, system ID: s_11f5ba618cf6
Preparing the "shell" executor
00:00
Using Shell (pwsh) executor...
Preparing environment
00:00
ERROR: Job failed (system failure): prepare environment: failed to start process: exec: "pwsh": executable file not found in %PATH%. Check https://docs.gitlab.com/runner/shells/index.html#shell-profile-loading for more information
````

нужно в папке с ранером C:\GitLab-Runner в конфиге поменять
**"pwsh"** на **powershell**

###

### <ins>  БД Установка параметра trustServerCertificate в true  </ins>

Properties properties = new Properties();
properties.setProperty("urlDB", "jdbc:sqlserver://1.1.1.1:
1433/48_SVET4_1.4.0.3084_Net_MsSQL;trustServerCertificate=true");
properties.setProperty("userDB", "your_username");
properties.setProperty("passwordDB", "your_password");

// Загрузка JDBC драйвера
Class.forName(properties.getProperty("driverDB-class-name"));

// Установка соединения с базой данных
Connection connection = DriverManager.getConnection(
properties.getProperty("urlDB"),
properties.getProperty("userDB"),
properties.getProperty("passwordDB"));

### <ins> REST Assured  отключение проверки сертификатов HTTPS   </ins>

Для отключения проверки сертификатов HTTPS в REST Assured можно использовать метод relaxedHTTPSValidation(). Этот метод
позволяет игнорировать проверку сертификатов и продолжать работу без них. Пример использования:

```java

RestAssured.given()
.

relaxedHTTPSValidation()
// Другие методы конфигурации
.

when()
.

get("https://example.com")
.

then()
// Другие методы проверки ответа
```

Таким образом, REST Assured будет обходить проверку сертификатов и продолжать работу с HTTPS-сайтами без ошибок.

### Выборочный запуск тестов gradle

тут для русских букв в консоли
разделитель двух команд точка с запятой, можно | или ентер

```shell
chcp 65001
```

сразу установка русских букв и запуск выборочно тестов

```java
chcp 65001;./
gradlew clean
test --
tests trany.testpack.TranyTests

```

Если Gradle установлен непосредственно в среде разработки IntelliJ IDEA, то скорее всего вы используете Gradle Wrapper.
gradle это прямая команда, и нужно, чтобы gradle был установлен не как обёртка в IDE.

```java
gradle clean
test --
tests trany.testpack.TranyTests
```

#### по тегам, @Tag"name" + см. build.gradle файл, таску myTags и customTags

```java
 ./gradlew clean myTags -x test -DcustomTags=Jackson
```

```groovy
tasks.register("myTags", Test.class) {
    testLogging {
        events "passed", "skipped", "failed"
    }
    useJUnitPlatform()
    systemProperty "file.encoding", "utf-8"
    String fullTags = System.getProperty("customTags")
    if (fullTags != null) {
        String[] tags = fullTags.split(",")
        useJUnitPlatform {
            for (String tag : tags) {
                includeTags.add(tag)
            }
        }

    }
```

### ./gradlew -version

```java
echo %JAVA_HOME%
where java
gradlew -version
```

### файл gradle.properties

#### Указываем конкретную JDK для Gradle

org.gradle.java.home=C:\\Program Files\\Java\\jdk-17

#### Настройки консоли (чтобы корректно отображалась кириллица)

```java
org.gradle.console=plain
org.gradle.logging.level=info
```

#### Принудительно UTF-8 для JVM

```java
org.gradle.jvmargs=-Dfile.encoding=UTF-8
```

-----
Убиваем старые демоны Gradle

```java
.\gradlew --stop

```

Проверяем, что gradle.properties корректен

Файл:
C:\projects\bpmgroovy\gradle.properties

должен содержать строку в одну линию:

```java
org.gradle.java.home=C:\\Program Files\\JetBrains\\IntelliJ IDEA Community Edition 2023.3.4\\jbr

```

⚠️ Никаких # комментариев перед этой строкой — иначе Gradle проигнорирует.

#### В IntelliJ проверь:

File → Settings → Build, Execution, Deployment → Build Tools → Gradle

✅ Build and run using → Gradle
✅ Run tests using → Gradle
✅ Gradle JVM → JetBrains Runtime 17 (путь на ...jbr)
✅ Gradle Distribution → Use gradle-wrapper.properties

### Создание пользователя монолита.

#### jsonObject должен быть строкой, поэтому нужно экранирование в теле запроса

```java
{"jsonObject":"{\"Id\":\"d0d5b229-1778-4e85-9c51-fd5d39399195\",\"CreatedBy\":\"410006e1-ca4e-4502-a9ec-e54d922d2c00\",\"ModifiedBy\":\"410006e1-ca4e-4502-a9ec-e54d922d2c00\",\"Name\":\"SVETuser3260\",\"SysAdminUnitType\":\"472e97c7-6bd7-df11-9b2a-001d60e938c6\",\"Contact\":\"f7aac61b-28fb-448c-86f1-f2a4a99c8cbb\",\"TimeZone\":null,\"UserPassword\":\"SVETuser3260!\",\"Active\":true,\"SynchronizeWithLDAP\":false,\"SysCulture\":\"1a778e3f-0a8e-e111-84a3-00155d054c03\",\"ConnectionType\":0,\"LDAPElement\":null,\"UserConnectionType\":\"df8ff7a2-c9bf-4f28-80b1-ed16fa77818d\"}","roleId":"a29a3ba5-4b0d-de11-9a51-005056c00008"}
```

```html
https://alcm-bpms-005.bpmrnd.ru/0/rest/AdministrationService/UpdateOrCreateUser
```