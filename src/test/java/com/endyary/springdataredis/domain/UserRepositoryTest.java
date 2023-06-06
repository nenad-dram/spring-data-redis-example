package com.endyary.springdataredis.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;

@DataRedisTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void save() {
        User user = new User(1L, "John", "Doe", "john.doe@mail.com");
        User savedUsed = userRepository.save(user);
        assertThat(savedUsed).isNotNull();

        userRepository.deleteById(1L);
    }

    @Test
    void getByIdFound() {
        User user = new User(2L, "Jane", "Doe", "jane.doe@mail.com");
        userRepository.save(user);

        Optional<User> optionalUser = userRepository.findById(2L);
        assertThat(optionalUser).isNotEmpty();
        assertThat(optionalUser.get().getEmail()).isEqualTo(user.getEmail());

        userRepository.deleteById(2L);
    }

    @Test
    void getByIdNotFound() {
        Optional<User> optionalUser = userRepository.findById(5L);
        assertThat(optionalUser).isEmpty();
    }

    @Test
    void update() {
        User user = new User(1L, "John", "Doe", "john.doe@mail.com");
        User newUser = userRepository.save(user);

        newUser.setEmail("new@mail.com");
        userRepository.save(newUser);
        User updatedUser = userRepository.findById(1L).orElseThrow();

        assertThat(updatedUser.getEmail()).isEqualTo("new@mail.com");

        userRepository.deleteById(1L);
    }
}
