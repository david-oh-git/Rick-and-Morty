package io.audioshinigami.core.network.responses

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class SingleDataResponseTest {

    @Test
    @DisplayName("When a SingleDataResponse class is created, its attributes should be correct")
    fun createSingleDataResponse_getCorrectAttributes() {
        // Arrange:
        val data = "FAKE DATA"

        // Act: create SingleDataResponse instance
        val singleDataResponse = SingleDataResponse(
            data = data
        )

        // Assert: Attributes values are accurate
        assertThat(data).isEqualTo(singleDataResponse.data)
    }
}
