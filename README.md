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



