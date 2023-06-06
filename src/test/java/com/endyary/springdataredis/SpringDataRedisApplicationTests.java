package com.endyary.springdataredis;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class SpringDataRedisApplicationTests {

    @Test
    void contextLoads(final ApplicationContext context) {
        assertThat(context).isNotNull();
    }

}
