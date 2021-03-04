package com.example.petsapplication.repository.repositoryimpl;

import com.example.petsapplication.dto.CreateDogDTO;
import com.example.petsapplication.dto.DogDTO;
import com.example.petsapplication.entity.Dog;
import com.example.petsapplication.entity.Owner;
import com.example.petsapplication.mapper.PetMapper;
import com.example.petsapplication.pojo.AuthenticationInfo;
import com.example.petsapplication.repository.DogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static com.example.petsapplication.mapper.PetMapper.toDogDto;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static com.example.petsapplication.utils.CheckEntityExistence.setMapOfEntities;
import static com.example.petsapplication.utils.HttpHeaderUtil.setHeaderAuth;

@Repository
@RequiredArgsConstructor
public class DogRepositoryImpl implements DogRepository {

    @Value("${backend.server.url}")
    private final String backendServerUrl;
    @Value("${dogs.url}")
    private final String dogsUrl;
    @Value("${pets.url}")
    private final String petsUrl;
    private final RestTemplate restTemplate;
    private final AuthenticationInfo authenticationInfo;

    @Override
    public List<DogDTO> findAll() {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<CreateDogDTO> httpEntity = new HttpEntity<>(setHeaderAuth(httpHeaders,
                authenticationInfo.getAuthenticationInfo()));
        DogDTO[] dogs = restTemplate.exchange(backendServerUrl + dogsUrl, GET,
                        httpEntity, DogDTO[].class).getBody();
        return isNull(dogs) ? emptyList() : asList(dogs);
    }

    @Override
    public DogDTO save(CreateDogDTO dogDTO, Owner owner) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<DogDTO> httpEntity = new HttpEntity<>(toDogDto(dogDTO, owner),
                setHeaderAuth(httpHeaders, authenticationInfo.getAuthenticationInfo()));
        ResponseEntity<DogDTO> savedDog = restTemplate.exchange(backendServerUrl + dogsUrl,
                POST, httpEntity, DogDTO.class);
        return savedDog.getBody();
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
