package agiletest.service.impl;

import agiletest.entity.Media;
import agiletest.service.MediaService;
import agiletest.service.TokenService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class MediaServiceImpl implements MediaService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    TokenService tokenService;

    @Value("${url}")
    private String url;

    @Override
    public List<Media> getMediaByPage(Integer pageNumber) {
        Map<String, Integer> params = new HashMap<>();
        params.put("page", pageNumber);
        MultiValueMap<String, String> multiValueMap = new HttpHeaders();
        multiValueMap.add("Authorization", "Bearer " + tokenService.getToken().getToken());
        HttpEntity<String> httpEntity = new HttpEntity(multiValueMap);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url + "/images", HttpMethod.GET, httpEntity, String.class, params);

        JsonObject jsonObject = new Gson().fromJson(responseEntity.getBody(), JsonObject.class);
        JsonArray jsonArray = jsonObject.getAsJsonArray("pictures");

        Media[] arrName = new Gson().fromJson(jsonArray, Media[].class);

        List<Object> media = new ArrayList<>();
        media = Arrays.asList(arrName);

        return new ArrayList<>();
    }
}
