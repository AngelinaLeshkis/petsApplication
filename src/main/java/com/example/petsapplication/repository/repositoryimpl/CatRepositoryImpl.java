package com.example.petsapplication.repository.repositoryimpl;

import com.example.petsapplication.dto.CreateCatDTO;
import com.example.petsapplication.entity.Cat;
import com.example.petsapplication.entity.Owner;
import com.example.petsapplication.repository.CatRepository;
import com.example.petsapplication.service.HttpHeadersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Repository
@RequiredArgsConstructor
public class CatRepositoryImpl implements CatRepository {

    @Value("${backend.server.url}")
    private final String backendServerUrl;
    @Value("${cats.url}")
    private final String catsUrl;
    @Value("${pets.url}")
    private final String petsUrl;
    private final RestTemplate restTemplate;
    private final HttpHeadersService httpHeadersService;

    @Override
    public List<Cat> findAll() {
        List<Cat> cats = restTemplate.exchange
                (backendServerUrl + catsUrl, GET,
                        new HttpEntity<Cat>(httpHeadersService.createHeaders()), List.class).getBody();
        return cats;
    }

    @Override
    public Optional<CreateCatDTO> save(CreateCatDTO catDTO, Owner owner) {
        catDTO.setOwnerId(owner.getId());
        CreateCatDTO savedCat = restTemplate.exchange(backendServerUrl + catsUrl, POST,
                new HttpEntity<>(catDTO, httpHeadersService.createHeaders()), CreateCatDTO.class).getBody();
        return Optional.ofNullable(savedCat);
    }

    @Override
    public void delete(long id) {
        restTemplate.exchange(backendServerUrl + petsUrl + "/" + id, DELETE,
                new HttpEntity<>(httpHeadersService.createHeaders()), Owner.class);
    }
}
