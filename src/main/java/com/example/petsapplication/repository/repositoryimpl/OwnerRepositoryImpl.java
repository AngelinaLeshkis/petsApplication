package com.example.petsapplication.repository.repositoryimpl;

import com.example.petsapplication.dto.CreateOwnerDTO;
import com.example.petsapplication.entity.Owner;
import com.example.petsapplication.pojo.AuthenticationInfo;
import com.example.petsapplication.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.example.petsapplication.mapper.OwnerMapper.toOwner;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Objects.isNull;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static com.example.petsapplication.utils.CheckEntityExistence.setMapOfEntities;
import static com.example.petsapplication.utils.HttpHeaderUtil.setHeaderAuth;


@Repository
@RequiredArgsConstructor
public class OwnerRepositoryImpl implements OwnerRepository {

    @Value("${backend.server.url}")
    private final String backendServerUrl;
    @Value("${owners.url}")
    private final String ownersUrl;
    private final RestTemplate restTemplate;
    private final AuthenticationInfo authenticationInfo;

    @Override
    public List<Owner> findAll() {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Owner> httpEntity = new HttpEntity<>(setHeaderAuth(httpHeaders,
                authenticationInfo.getAuthenticationInfo()));
        Owner[] owners = restTemplate.exchange
                (backendServerUrl + ownersUrl, GET, httpEntity, Owner[].class).getBody();
        return isNull(owners) ? emptyList() : asList(owners);
    }

    @Override
    public Owner save(CreateOwnerDTO owner) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Owner> httpEntity = new HttpEntity<>(toOwner(owner),
                setHeaderAuth(httpHeaders, authenticationInfo.getAuthenticationInfo()));
        ResponseEntity<Owner> savedOwner = restTemplate.exchange(backendServerUrl + ownersUrl, POST,
                httpEntity, Owner.class);
        return savedOwner.getBody();
    }

    @Override
    public void delete(long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Owner> httpEntity = new HttpEntity<>(setHeaderAuth(httpHeaders,
                authenticationInfo.getAuthenticationInfo()));
        restTemplate.exchange(backendServerUrl + ownersUrl + "/" + id, DELETE,
                httpEntity, String.class);
    }
}
