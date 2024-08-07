package errorparser;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class AppErrorTest {
    @Test
    public void errUIandDBequal() {
        ErrorPage errorPage = new ErrorPage();
        errorPage.parseErrorDataAndSQLscripts();
        Set<String> uierr = errorPage.getPakcagiesName();
        Set<String> packagies = new HashSet<>();

        ErrorDB db = new ErrorDB();
        List<ErrorModel> errors = db.findLastErrors();
        if (errors.isEmpty()) {
            System.out.println("Ошибок нет.");
        } else {
            errors.forEach(e -> packagies.add(e.packageName));
        }
        Assertions.assertThat(uierr).containsAll(packagies);
        Assertions.assertThat(uierr.size()).isEqualTo(packagies.size());
    }

    @Test
    public void errDB() {
//        ErrorPage errorPage = new ErrorPage();
//        errorPage.parseErrorDataAndSQLscripts();
//        Set<String> uierr = errorPage.getPakcagiesName();
        Set<String> packagies = new HashSet<>();

        ErrorDB db = new ErrorDB();
        List<ErrorModel> errors = db.findLastErrors();
        if (errors.isEmpty()) {
            System.out.println("Ошибок нет.");
        } else {
            errors.forEach(e -> packagies.add(e.packageName));
        }
        AtomicInteger i = new AtomicInteger();
        errors.forEach(e -> System.out.println(i.incrementAndGet() + ") " + e));
        System.out.println("Вывод списка пакетов:");
        packagies.forEach(e -> System.out.println(e));
    }

    @Test
    public void errUI() {
        ErrorPage errorPage = new ErrorPage();
        errorPage.parseErrorDataAndSQLscripts();
        Set<String> uierr = errorPage.getPakcagiesName();

        AtomicInteger i = new AtomicInteger();
        uierr.forEach(e -> System.out.println(i.incrementAndGet() + ") " + e));

        System.out.println("-------------------");
        System.out.println(errorPage.getPakcagiesName());
//        Set<String> packagies = new HashSet<>();
//
//        ErrorDB db = new ErrorDB();
//        List<ErrorModel> errors = db.findLastErrors();
//        if (errors.isEmpty()) {
//            System.out.println("Ошибок нет.");
//        } else {
//            errors.forEach(e -> packagies.add(e.packageName));
//        }
//        AtomicInteger i = new AtomicInteger();
//        errors.forEach(e -> System.out.println(i.incrementAndGet() + ") " + e));
    }

}
