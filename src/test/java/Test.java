import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by huangke on 2015/5/6.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        http("{\"text\": \"<https://alert-system.com/alerts/1234|Click here> for details!\"}");
    }

    public static void http(String json) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            HttpPost post = new HttpPost("incomingwebhooksurl");
            StringEntity s = new StringEntity(json);
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");
            post.setEntity(s);
            CloseableHttpResponse response = client.execute(post);
            try {
                HttpEntity entity = response.getEntity();
                System.out.println(EntityUtils.toString(entity));
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } finally {
            client.close();
        }
    }
}
