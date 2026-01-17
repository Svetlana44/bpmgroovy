# Для обработки исключений используется методы try-catch-finally. Если у нас есть задача "записать какие-то данные в текстовый файл", то что будет в каждом из блоков?

# try / catch / finally при записи в файл

## Что в каждом блоке

- **try** — открытие файла и запись данных
- **catch** — обработка IOException/Unchecked исключений
- **finally** — закрытие ресурсов (если не используется try‑with‑resources)

## Пример

```java
FileWriter writer = null;
try {
  writer = new FileWriter("data.txt");
  writer.write("hello");
} catch (IOException ex) {
  // логируем или пробрасываем
} finally {
  if (writer != null) {
    writer.close();
  }
}
```
