# StringBuilder и работа со строками

## Вопросы и ответы

**1. Почему String неизменяемый (immutable)?**  
String создан как immutable для безопасности (thread-safe), кэширования (String Pool), и использования в качестве ключей в HashMap. Изменение строки создаёт новый объект.

**2. Разница между String, StringBuilder и StringBuffer?**  
- **String** — immutable, каждое изменение создаёт новый объект (медленно при множественных операциях).
- **StringBuilder** — mutable, не thread-safe, быстрее для однопоточных операций.
- **StringBuffer** — mutable, thread-safe (synchronized), медленнее StringBuilder.

**3. Когда использовать StringBuilder?**  
При множественных операциях конкатенации строк (в циклах, при построении длинных строк). Обычная конкатенация `+` создаёт много промежуточных объектов.

**4. Есть ли у String метод reverse()?**  
Нет, у String нет метода reverse(). Используйте `new StringBuilder(s).reverse().toString()` или `new StringBuffer(s).reverse().toString()`.

**5. Как работает StringBuilder.reverse()?**  
Меняет порядок символов в текущем объекте StringBuilder на обратный, возвращает тот же объект (fluent interface).

**6. Когда конкатенация через `+` эффективна?**  
Для небольшого количества строк (2-3) компилятор оптимизирует в StringBuilder автоматически. В циклах всегда используйте StringBuilder явно.

**7. Что такое String Pool?**  
Пул строк — область памяти для хранения строковых литералов. Строки с одинаковым содержимым могут ссылаться на один объект, что экономит память.

**8. Разница между `==` и `equals()` для String?**  
`==` сравнивает ссылки, `equals()` — содержимое. Для строк всегда используйте `equals()`.

**9. Как эффективно перевернуть строку?**  
```java
// Самый эффективный способ
new StringBuilder(s).reverse().toString()

```

**10. Что важно на Senior‑уровне?**  
Понимание производительности операций со строками, выбор правильного класса (String/StringBuilder/StringBuffer), работа с большими данными, memory leaks при неправильном использовании.

## Основные методы StringBuilder

| Метод | Описание | Упражнение |
|-------|----------|------------|
| `append()` | добавление в конец | `joinStrings()`, `buildComplexString()` |
| `insert()` | вставка в указанную позицию | `insertString()`, `buildComplexString()` |
| `delete()` | удаление подстроки | `deleteSubstring()` |
| `deleteCharAt()` | удаление символа по индексу | `deleteCharAt()` |
| `replace()` | замена подстроки | `replaceSubstring()`, `buildComplexString()` |
| `reverse()` | переворот строки | `reverseString()` (в обоих классах) |
| `toString()` | преобразование в String | используется во всех упражнениях |
| `length()` | длина строки | `getLength()` |
| `capacity()` | ёмкость буфера | `getCapacity()` |

## Примеры использования

```java
// Переворот строки
String reversed = new StringBuilder("hello").reverse().toString(); // "olleh"

// Построение строки в цикле
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 10; i++) {
    sb.append(i).append(" ");
}
String result = sb.toString(); // "0 1 2 3 4 5 6 7 8 9 "

// Замена подстроки
StringBuilder sb = new StringBuilder("Hello World");
sb.replace(0, 5, "Hi");
// "Hi World"
```

## LeetCode упражнения

### Easy (8)

- **Reverse String** — https://leetcode.com/problems/reverse-string/  
  Перевернуть строку (массив символов in-place).  
  В проекте: `src/test/java/leetcode/string/StringEasyTasks.java::reverseString()`

- **Valid Palindrome** — https://leetcode.com/problems/valid-palindrome/  
  Проверить, является ли строка палиндромом (игнорируя регистр и неалфавитные символы).  
  В проекте: `src/test/java/leetcode/string/StringEasyTasks.java::isPalindrome()`

- **First Unique Character in a String** — https://leetcode.com/problems/first-unique-character-in-a-string/  
  Найти индекс первого уникального символа.  
  В проекте: `src/test/java/leetcode/string/StringEasyTasks.java::firstUniqChar()`

- **Valid Anagram** — https://leetcode.com/problems/valid-anagram/  
  Проверить, является ли одна строка анаграммой другой.  
  В проекте: `src/test/java/leetcode/string/StringEasyTasks.java::isAnagram()`

- **Longest Common Prefix** — https://leetcode.com/problems/longest-common-prefix/  
  Найти общий префикс массива строк.  
  В проекте: `src/test/java/leetcode/string/StringEasyTasks.java::longestCommonPrefix()`

- **Reverse Words in a String** — https://leetcode.com/problems/reverse-words-in-a-string/  
  Перевернуть порядок слов в строке.

- **Reverse String II** — https://leetcode.com/problems/reverse-string-ii/  
  Перевернуть каждые k символов строки.

- **Reverse Only Letters** — https://leetcode.com/problems/reverse-only-letters/  
  Перевернуть только буквы, оставив остальные символы на месте.

### Medium (4)

- **Reverse Words in a String III** — https://leetcode.com/problems/reverse-words-in-a-string-iii/  
  Перевернуть каждое слово в строке, сохранив порядок слов.

- **Group Anagrams** — https://leetcode.com/problems/group-anagrams/  
  Сгруппировать анаграммы вместе.

- **Longest Palindromic Substring** — https://leetcode.com/problems/longest-palindromic-substring/  
  Найти самую длинную палиндромную подстроку.

- **String to Integer (atoi)** — https://leetcode.com/problems/string-to-integer-atoi/  
  Преобразовать строку в целое число с обработкой граничных случаев.

## Практические упражнения в проекте

### Упражнения на методы StringBuilder

Все методы StringBuilder покрыты упражнениями в `src/test/java/leetcode/string/StringBuilderMethodsTasks.java`:

1. **joinStrings()** — использует `append()` — объединение строк из массива
2. **insertString()** — использует `insert()` — вставка строки в позицию
3. **deleteSubstring()** — использует `delete()` — удаление подстроки
4. **deleteCharAt()** — использует `deleteCharAt()` — удаление символа по индексу
5. **replaceSubstring()** — использует `replace()` — замена подстроки
6. **reverseString()** — использует `reverse()` — переворот строки
7. **getLength()** — использует `length()` — получение длины
8. **getCapacity()** — использует `capacity()` — получение ёмкости
9. **buildComplexString()** — комплексное упражнение с `append()`, `insert()`, `replace()`

### Дополнительные задачи на строки

Задачи в `src/test/java/leetcode/string/StringEasyTasks.java`:

1. **reverseString()** — переворот строки через StringBuilder
2. **isAnagram()** — проверка анаграммы через сортировку
3. **firstUniqChar()** — поиск первого уникального символа
4. **isPalindrome()** — проверка палиндрома
5. **longestCommonPrefix()** — поиск общего префикса

Запуск тестов: `./gradlew leetcodeTest`

## Типичные ошибки

1. **Конкатенация в цикле через `+`** — создаёт много промежуточных объектов
   ```java
   // Плохо
   String result = "";
   for (String s : list) {
       result += s; // создаёт новый объект каждый раз
   }
   
   // Хорошо
   StringBuilder sb = new StringBuilder();
   for (String s : list) {
       sb.append(s);
   }
   String result = sb.toString();
   ```

2. **Использование StringBuffer вместо StringBuilder** — избыточная синхронизация в однопоточном коде

3. **Забыть вызвать toString()** — StringBuilder не равен String

4. **Сравнение через `==` вместо equals()** — сравнение ссылок вместо содержимого
