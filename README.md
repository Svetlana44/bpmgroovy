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
что помогает обеспечить правильность данных, возвращаемых из API.
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

### <ins>  Установка параметра trustServerCertificate в true  </ins>

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