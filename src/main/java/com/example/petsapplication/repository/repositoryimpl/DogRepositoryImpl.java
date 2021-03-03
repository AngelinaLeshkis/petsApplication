package com.example.petsapplication.repository.repositoryimpl;

import com.example.petsapplication.dto.CreateDogDTO;
import com.example.petsapplication.entity.Dog;
import com.example.petsapplication.entity.Owner;
import com.example.petsapplication.pojo.AuthenticationInfo;
import com.example.petsapplication.repository.DogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.example.petsapplication.mapper.PetMapper.toDog;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static utils.CheckEntityExistence.setMapOfEntities;
import static utils.HttpHeaderUtil.createHeaders;

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
    public List<Dog> findAll() {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<CreateDogDTO> httpEntity = new HttpEntity<>(createHeaders(httpHeaders, authenticationInfo.getAuthenticationInfo()));
        List<Dog> dogs = restTemplate.exchange
                (backendServerUrl + dogsUrl, GET,
                        httpEntity, List.class).getBody();
        return dogs;
    }

    @Override
    public Dog save(CreateDogDTO dogDTO, Owner owner) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<CreateDogDTO> httpEntity = new HttpEntity<>(dogDTO, createHeaders(httpHeaders, authenticationInfo.getAuthenticationInfo()));
        dogDTO.setOwnerId(owner.getId());
        ResponseEntity<CreateDogDTO> savedDog = restTemplate.exchange(backendServerUrl + dogsUrl, POST,
                httpEntity, CreateDogDTO.class);
        setMapOfEntities(savedDog.getBody(), savedDog.getStatusCode());
        return toDog(savedDog.getBody(), owner);
    }

    @Override
    public void delete(long id) {
//        restTemplate.exchange(backendServerUrl + petsUrl + "/" + id, DELETE,
//                new HttpEntity<>(httpHeadersService.createHeaders()), Owner.class);
    }
}
