package com.example.petsapplication.repository.repositoryimpl;

import com.example.petsapplication.dto.CreateCatDTO;
import com.example.petsapplication.entity.Cat;
import com.example.petsapplication.entity.Owner;
import com.example.petsapplication.pojo.AuthenticationInfo;
import com.example.petsapplication.repository.CatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.example.petsapplication.mapper.PetMapper.toCat;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static utils.CheckEntityExistence.setMapOfEntities;
import static utils.HttpHeaderUtil.createHeaders;

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
    private final AuthenticationInfo authenticationInfo;

    @Override
    public List<Cat> findAll() {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<CreateCatDTO> httpEntity = new HttpEntity<>(createHeaders(httpHeaders, authenticationInfo.getAuthenticationInfo()));
        List<Cat> cats = restTemplate.exchange
                (backendServerUrl + catsUrl, GET,
                        httpEntity, List.class).getBody();
        return cats;
    }

    @Override
    public Cat save(CreateCatDTO catDTO, Owner owner) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<CreateCatDTO> httpEntity = new HttpEntity<>(catDTO, createHeaders(httpHeaders, authenticationInfo.getAuthenticationInfo()));
        catDTO.setOwnerId(owner.getId());
        ResponseEntity<CreateCatDTO> savedCat = restTemplate.exchange(backendServerUrl + catsUrl, POST,
                httpEntity, CreateCatDTO.class);
        setMapOfEntities(savedCat.getBody(), savedCat.getStatusCode());
        return toCat(savedCat.getBody(), owner);
    }

    @Override
    public void delete(long id) {
//        restTemplate.exchange(backendServerUrl + petsUrl + "/" + id, DELETE,
//                new HttpEntity<>(httpHeadersService.createHeaders()), Owner.class);
    }
}
