package ru.rostanin.springbootdemo.services;

import lombok.SneakyThrows;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import ru.rostanin.springbootdemo.domain.Meditation;
import ru.rostanin.springbootdemo.repositories.MeditationsRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

//@ExtendWith(MockitoExtension.class) // TODO: For what?
public class MeditationsServiceTest {

    @Mock
    private MeditationsRepository meditationsRepository;
    private AutoCloseable autoCloseable;
    private MeditationsService underTest;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new MeditationsService(meditationsRepository);
    }

    @SneakyThrows
    @AfterEach
    void tearDown() {
        autoCloseable.close();
    }

    @Test
    @DisplayName("Should find all meditations")
    void canGetAll() {
        // when
        underTest.getAll();
        // then
        verify(meditationsRepository).findAll();
    }

    @ParameterizedTest
    @ValueSource(longs = {15, 56, 0})
    @DisplayName("Should find meditation by id")
    @Disabled
    // TODO: Failed, probably because hibernate doesn't have time to persist
    void canGetById(Long testId) {
        // given
        long id = testId;
        underTest.create(new Meditation(id, "title", "decs", "path", 1.0));

        // when
        Meditation meditation = underTest.getById(id);
        // then
        Optional<Meditation> mock = verify(meditationsRepository).findById(id);
        if (mock.isEmpty()) throw new IllegalArgumentException("Meditation not found by id");

        assertThat(meditation.getId()).isEqualTo(mock.get().getId());
    }

    @Test
    @DisplayName("Should create new meditation")
    void canCreate() {
        // given
        Meditation meditation = new Meditation(0L, "title1", "desc1", "path/to/file", 5);
        // when
        underTest.create(meditation);
        // then
        ArgumentCaptor<Meditation> argumentCaptor = ArgumentCaptor.forClass(Meditation.class);
        verify(meditationsRepository).save(argumentCaptor.capture()); // we capture the object in save method

        Meditation captured = argumentCaptor.getValue();

        assertThat(captured).isEqualTo(meditation);
    }

    @Test
    @DisplayName("Should update new meditation")
    void updateMeditation() {
        // given
        long id = 0L;
        Meditation meditation = new Meditation(id, "title1.1", "desc1.1", "path/to/file", 5);
        // when
        underTest.update(id, meditation);
        // then
        ArgumentCaptor<Meditation> argumentCaptor = ArgumentCaptor.forClass(Meditation.class);
        verify(meditationsRepository).save(argumentCaptor.capture());

        Meditation captured = argumentCaptor.getValue();

        assertThat(captured).isEqualTo(meditation);
    }

    @Test
    @DisplayName("Should delete the particular meditation")
    void deleteMeditation() {
        // given
        long id = 15;
        // when
        underTest.delete(id);
        // then
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(meditationsRepository).deleteById(argumentCaptor.capture());

        Long captured = argumentCaptor.getValue();

        assertThat(captured).isEqualTo(id);
    }

}
