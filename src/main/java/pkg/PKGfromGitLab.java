package pkg;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PKGfromGitLab {

    String env_jsonURL = "https://gitlab.omnichannel.ru/bpmsoft/packagesinsidedb/-/raw/develop/env.json";
    String saveDir = "java/pkg/resoursesfiles/env.json"; /* Путь к файлу на локальной машине  */

    public static void download_env_json(String fileURL, String saveDir) {
        try {
            URL url = new URL(fileURL);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            int responseCode = httpConn.getResponseCode();

            // Проверяем HTTP ответ
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Получаем входной поток для чтения данных
                InputStream inputStream = httpConn.getInputStream();
                // Открываем выходной поток для записи данных
                FileOutputStream outputStream = new FileOutputStream(saveDir);

                int bytesRead;
                byte[] buffer = new byte[4096];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                outputStream.close();
                inputStream.close();

                System.out.println("Файл скачан и сохранен: " + saveDir);
            } else {
                System.out.println("No file to download. Server replied HTTP code: " + responseCode);
            }
            httpConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String fileURL = "https://gitlab.omnichannel.ru/bpmsoft/packagesinsidedb/-/raw/develop/env.json?inline=false";
        String saveDir = "src/main/java/pkg/resoursesfiles/env.json"; // Путь к файлу на локальной машине
        download_env_json(fileURL, saveDir);
    }
}
