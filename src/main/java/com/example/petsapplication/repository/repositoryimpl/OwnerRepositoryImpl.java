package com.example.petsapplication.repository.repositoryimpl;

import com.example.petsapplication.entity.Owner;
import com.example.petsapplication.repository.OwnerRepository;
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
public class OwnerRepositoryImpl implements OwnerRepository {

    @Value("${backend.server.url}")
    private final String backendServerUrl;
    @Value("${owners.url}")
    private final String ownersUrl;
    private final RestTemplate restTemplate;
    private final HttpHeadersService httpHeadersService;

    @Override
    public List<Owner> findAll() {
        List<Owner> owners = restTemplate.exchange
                (backendServerUrl + ownersUrl, GET,
                        new HttpEntity<Owner>(httpHeadersService.createHeaders()), List.class).getBody();
        return owners;
    }

    @Override
    public Optional<Owner> save(Owner owner) {
        Owner savedOwner = restTemplate.exchange(backendServerUrl + ownersUrl, POST,
                new HttpEntity<>(owner, httpHeadersService.createHeaders()), Owner.class).getBody();
        return Optional.ofNullable(savedOwner);
    }

    @Override
    public void delete(long id) {
        restTemplate.exchange(backendServerUrl + ownersUrl + "/" + id, DELETE,
                new HttpEntity<>(httpHeadersService.createHeaders()), Owner.class);
    }
}
