package edu.pkch.mock.mockito;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@MockitoSettings(strictness = Strictness.LENIENT)
class MockTest {

    @Mock
    private List<String> mockAnnotatedList;

    @Test
    void annotatedMock() {
        // given
        given(mockAnnotatedList.get(eq(0))).willReturn(null);
        given(mockAnnotatedList.get(eq(1))).willReturn(null);

        // when
        String actual = mockAnnotatedList.get(0);

        // then
        assertThat(actual).isEqualTo(null);
    }
}
