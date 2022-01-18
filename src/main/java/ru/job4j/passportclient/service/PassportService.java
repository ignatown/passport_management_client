package ru.job4j.passportclient.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.job4j.passportclient.domain.Passport;

import java.util.List;

@Service
public class PassportService {
    @Value("http://localhost:8080/")
    private String URL;

    private final RestTemplate restTemplate;

    public PassportService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Passport save(Passport passport) {
        return restTemplate.postForObject(URL + "save", passport, Passport.class);
    }

    public Passport update(Passport passport) {
        return restTemplate.exchange(
                String.format(URL + "update"),
                HttpMethod.PUT,
                new HttpEntity<>(passport),
                Passport.class
        ).getBody();
    }

    public void delete(int id) {
        restTemplate.delete(URL + "delete?id=" + id);
    }

    public List<Passport> findAll() {
        return getList(URL + "find");
    }

    public List<Passport> findBySeria(int seria) {
        return getList(URL + "find?seria=" + seria);
    }

    public List<Passport> findUnavailable() {
        return getList(URL + "unavailable");
    }

    public List<Passport> findReplaceable() {
        return getList(URL + "find-replaceable");
    }

    private List<Passport> getList(String url) {
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Passport>>() {
                }).getBody();
    }
}
