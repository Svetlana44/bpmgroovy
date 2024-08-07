package pkg.resoursesfiles;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class GitLabFileDownloader {

    public static void downloadFile(String projectId, String filePath, String branchName, String outputFileName, String accessToken) {
        String url = String.format("https://gitlab.omnichannel.ru/api/v4/projects/%s/repository/files/%s/raw?ref=%s",
                projectId, filePath, branchName);

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            request.setHeader("PRIVATE-TOKEN", accessToken);
            try (CloseableHttpResponse response = client.execute(request)) {
                if (response.getStatusLine().getStatusCode() == 200) {
                    InputStream inputStream = response.getEntity().getContent();
                    try (OutputStream outputStream = new FileOutputStream(outputFileName)) {
                        int read;
                        byte[] bytes = new byte[1024];
                        while ((read = inputStream.read(bytes)) != -1) {
                            outputStream.write(bytes, 0, read);
                        }
                    }
                } else {
                    System.out.println("Failed to download file: " + response.getStatusLine());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String projectId = "bpmsoft/packagesinsidedb";
        String filePath = "env.json";
        String branchName = "develop";
        String accessToken = System.getProperty("gitlabPrivateToken", "");
        downloadFile(projectId, filePath, branchName, "env.json", accessToken);
    }
}

//   String url = "https://gitlab.omnichannel.ru/api/v4/projects/bpmsoft/packagesinsidedb/repository/files/env.json/raw?ref=develop";
