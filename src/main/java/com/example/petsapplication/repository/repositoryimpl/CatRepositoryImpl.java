package com.example.petsapplication.repository.repositoryimpl;

import com.example.petsapplication.dto.CatDTO;
import com.example.petsapplication.dto.CreateCatDTO;
import com.example.petsapplication.entity.Dog;
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

import static com.example.petsapplication.mapper.PetMapper.toCatDto;
import static com.example.petsapplication.utils.HttpHeaderUtil.setHeaderAuth;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Objects.isNull;
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
    private final AuthenticationInfo authenticationInfo;

    @Override
    public List<CatDTO> findAll() {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<CreateCatDTO> httpEntity = new HttpEntity<>(setHeaderAuth(httpHeaders,
                authenticationInfo.getAuthenticationInfo()));
        CatDTO[] cats = restTemplate.exchange(backendServerUrl + catsUrl, GET,
                httpEntity, CatDTO[].class).getBody();
        return isNull(cats) ? emptyList() : asList(cats);
    }

    @Override
    public CatDTO save(CreateCatDTO catDTO, Owner owner) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<CatDTO> httpEntity = new HttpEntity<>(toCatDto(catDTO, owner),
                setHeaderAuth(httpHeaders, authenticationInfo.getAuthenticationInfo()));
        ResponseEntity<CatDTO> savedCat = restTemplate.exchange(backendServerUrl + catsUrl, POST,
                httpEntity, CatDTO.class);
        return savedCat.getBody();
    }

    @Override
    public void delete(long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Dog> httpEntity = new HttpEntity<>(setHeaderAuth(httpHeaders,
                authenticationInfo.getAuthenticationInfo()));
        restTemplate.exchange(backendServerUrl + petsUrl + "/" + id, DELETE,
                httpEntity, String.class);
    }
}
