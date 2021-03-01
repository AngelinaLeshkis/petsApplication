package com.example.petsapplication.repository.repositoryimpl;

import com.example.petsapplication.dto.CreateDogDTO;
import com.example.petsapplication.entity.Dog;
import com.example.petsapplication.entity.Owner;
import com.example.petsapplication.repository.DogRepository;
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
public class DogRepositoryImpl implements DogRepository {

    @Value("${backend.server.url}")
    private final String backendServerUrl;
    @Value("${dogs.url}")
    private final String dogsUrl;
    @Value("${pets.url}")
    private final String petsUrl;
    private final RestTemplate restTemplate;
    private final HttpHeadersService httpHeadersService;

    @Override
    public List<Dog> findAll() {
        List<Dog> dogs = restTemplate.exchange
                (backendServerUrl + dogsUrl, GET,
                        new HttpEntity<Dog>(httpHeadersService.createHeaders()), List.class).getBody();
        return dogs;
    }

    @Override
    public Optional<CreateDogDTO> save(CreateDogDTO dogDTO, Owner owner) {
        dogDTO.setOwnerId(owner.getId());
        CreateDogDTO savedDog = restTemplate.exchange(backendServerUrl + dogsUrl, POST,
                new HttpEntity<>(dogDTO, httpHeadersService.createHeaders()), CreateDogDTO.class).getBody();
        return Optional.ofNullable(savedDog);
    }

    @Override
    public void delete(long id) {
        restTemplate.exchange(backendServerUrl + petsUrl + "/" + id, DELETE,
                new HttpEntity<>(httpHeadersService.createHeaders()), Owner.class);
    }
}
