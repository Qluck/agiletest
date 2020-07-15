package agiletest.service.impl;

import agiletest.entity.Token;
import agiletest.service.TokenService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    RestTemplate restTemplate;

    @Value("${url}")
    private String url;

    @Value("${apiKey}")
    private String apiKey;

    @Override
    public Token getToken() {
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("apiKey", apiKey);
        MultiValueMap<String, String> multiValueMap = new HttpHeaders();
        multiValueMap.add("Content-Type", "application/json");
        HttpEntity<String> httpEntity = new HttpEntity<>(gson.toJson(jsonObject), multiValueMap);
        String json = restTemplate.postForObject(url + "/auth", httpEntity, String.class);
        return gson.fromJson(json, Token.class);
    }
}
