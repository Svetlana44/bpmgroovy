package listener;

import io.qameta.allure.restassured.AllureRestAssured;

public class CustomTpl {
    public static final AllureRestAssured FILTER = new AllureRestAssured();

    private CustomTpl() {
    }

    public static CustomTpl customLogFilter() {
        return InitLogFilter.logFilter;
    }

    public AllureRestAssured withCustomTemplates() {
        FILTER.setRequestTemplate("request.ftl");
        FILTER.setResponseTemplate("response.ftl");
        return FILTER;
    }

    private static class InitLogFilter {
        public static final CustomTpl logFilter = new CustomTpl();
    }
}
