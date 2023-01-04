package exercise;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import static org.apache.hc.client5.http.impl.classic.HttpClients.createDefault;
import static org.assertj.core.api.Assertions.assertThat;

import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.LifecycleException;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

class AppTest {
    private static Tomcat app;
    private static String baseUrl;

    @BeforeAll
    public static void setup() throws LifecycleException {
        app = App.getApp(0);
        app.start();
        int port = app.getConnector().getLocalPort();
        String hostname = "localhost";
        baseUrl = "http://" + hostname + ":" + port;
    }

    @Test
    void test() throws IOException, ParseException {
        CloseableHttpResponse response;
        try (CloseableHttpClient client = createDefault()) {

            HttpGet request = new HttpGet(baseUrl);
            response = client.execute(request);
        }

        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity);

        assertThat(response.getCode()).isEqualTo(200);
        assertThat(content.trim()).isEqualTo("Hello, Hexlet!");
    }

    @AfterAll
    public static void tearDown() throws LifecycleException {
        app.stop();
    }
}
