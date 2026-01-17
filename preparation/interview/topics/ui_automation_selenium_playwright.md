# UI автоматизация: Selenium, Selenide, Playwright

## Вопросы и ответы

**1. Чем отличаются Selenium и Playwright?**  
Selenium использует WebDriver, Playwright работает напрямую с браузерами.
Playwright быстрее, имеет auto‑waits и встроенные скриншоты/видео.

**2. Когда выбирать Selenide?**  
Если нужен более удобный API поверх Selenium и меньше кода для ожиданий.

**3. Что такое POM и зачем он нужен?**  
Page Object Model инкапсулирует работу со страницей и повышает читаемость
тестов.

**4. Как работать с ожиданиями?**  
Предпочитать explicit waits/auto‑waits, избегать Thread.sleep.

**5. Как бороться с флейками в UI?**  
Стабильные локаторы, корректные waits, isolation, перезапуски только
по необходимости.

**6. Как тестировать файлы (upload/download)?**  
Имитация ввода файла, проверка наличия файла, проверка контента.

**7. Что важно в архитектуре UI‑фреймворка?**  
Слои: tests → pages → core/utils → config.

**8. Пример Playwright теста:**
```java
@Test
void login() {
  page.navigate("https://example.com/login");
  page.fill("#username", "user");
  page.fill("#password", "pass");
  page.click("button[type='submit']");
  assertThat(page.locator(".welcome")).isVisible();
}
```

---

См. пример: `../../examples/ui/PlaywrightExampleTest.java`.
