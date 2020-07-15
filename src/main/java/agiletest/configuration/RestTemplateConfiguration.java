package agiletest.configuration;

import agiletest.entity.Token;
import agiletest.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.function.Supplier;

import static java.util.Arrays.asList;

@Configuration
public class RestTemplateConfiguration {
    private static final StringHttpMessageConverter STRING_HTTP_MESSAGE_CONVERTER = new StringHttpMessageConverter();
    private static final GsonHttpMessageConverter GSON_HTTP_MESSAGE_CONVERTER = new GsonHttpMessageConverter();

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Value("${url}")
    private String url;

    @Bean
    @Primary
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean(name = "agile")
    public RestTemplate agileRestTemplate() {
        return restTemplateBuilder
                .messageConverters(asList(STRING_HTTP_MESSAGE_CONVERTER, GSON_HTTP_MESSAGE_CONVERTER))
                .interceptors((request, body, execution) -> {
                    String token = Base64Utils.encodeToString((tokenService.getToken().getToken()).getBytes(StandardCharsets.UTF_8));
                    request.getHeaders().add("Authorization", "Bearer " + token);
                    request.getHeaders().add("Content-Type", "application/json");
                    return execution.execute(request, body);
                })
                .rootUri(url)
                .build();
    }

    @Bean
    public BufferingClientHttpRequestFactory requestFactory() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setOutputStreaming(false);
        return new BufferingClientHttpRequestFactory(requestFactory);
    }
}
