package io.audioshinigami.core.network.responses

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock

internal class DataResponseTest {

    @Test
    @DisplayName("When DataResponse is created, correct attributes are retained.")
    fun createDataResponse_getCorrectAttributes(){

        // Arrange:
        val info = mock(Info::class.java)
        val results = listOf<String>()

        // Act: create DataResponse instance
        val dataResponse = DataResponse(
            info = info,
            results = results
        )

        // Assert: Attributes values are accurate
        assertThat(info).isEqualTo(dataResponse.info)
        assertThat(results).isEqualTo(dataResponse.results)
    }
}