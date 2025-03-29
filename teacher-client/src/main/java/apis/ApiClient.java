package apis;

import org.springframework.web.client.RestTemplate;

public class ApiClient {

    protected final RestTemplate restTemplate;
    private static final String PORT = "3307";
    protected static final String BASE_URL = "http://localhost:" + PORT;

    public ApiClient() {
        this.restTemplate = new RestTemplate();
    }

}
