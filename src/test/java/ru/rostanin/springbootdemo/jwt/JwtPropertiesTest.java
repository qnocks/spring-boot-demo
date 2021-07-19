package ru.rostanin.springbootdemo.jwt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class JwtPropertiesTest {
    @Autowired
    private JwtProperties underTest;

    @Test
    @DisplayName("Should inject jwt properties from YAML file")
    void shouldInjectJwtPropertiesFromYamlFile() {
        assertThat(underTest.headerString).isEqualTo("Authorization");
        assertThat(underTest.tokenPrefix).isEqualTo("Bearer");
        assertThat(underTest.expirationTime).isEqualTo(864000);
    }
}
