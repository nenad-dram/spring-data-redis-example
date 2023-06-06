package com.endyary.springdataredis.domain;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @CachePut(value = "userCache", key = "#user.id", unless = "#user.id==null")
    public User save(final User user) {
        return userRepository.save(user);
    }

    @Cacheable(value = "userCache", key = "#id")
    public User getById(final Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public List<User> getAll() {
        Iterable<User> userIterable = userRepository.findAll();

        return StreamSupport.stream(userIterable.spliterator(), false).toList();
    }

    @CacheEvict(value = "userCache", key = "#id")
    public void delete(final Long id) {
        userRepository.deleteById(id);
    }

    @CacheEvict(value = "userCache", allEntries = true)
    public void deleteAll() {
        userRepository.deleteAll();
    }
}
