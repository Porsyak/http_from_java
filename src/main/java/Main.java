import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Main {
    private static final String URL_CATS =
            "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void main(String[] args) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(URL_CATS);
            CloseableHttpResponse response = httpClient.execute(httpGet);

            List<Post> posts = OBJECT_MAPPER.readValue(response.getEntity().getContent(),
                            new TypeReference<List<Post>>() {
                                @Override
                                public Type getType() {
                                    return super.getType();
                                }
                            })
                    .stream()
                    .filter(v -> v.getUpvotes() > 0)
                    .toList();
            posts.forEach(System.out::println);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
