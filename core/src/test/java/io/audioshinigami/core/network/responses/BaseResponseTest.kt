package io.audioshinigami.core.network.responses

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock

@Suppress("UNCHECKED_CAST")
internal class BaseResponseTest {

    @Test
    @DisplayName("When a BaseResponse class is created, its attributes should be correct")
    fun createBaseResponse_CorrectAttributes() {
        // Arrange
        val code = 200
        val status = "Ok"
        val message = "Success"

        val data: SingleDataResponse<String> = mock(SingleDataResponse::class.java, "FAKE RESPONSE")
            as SingleDataResponse<String>

        // Act: create BaseResponse instance
        val baseResponse = BaseResponse(
            code = code,
            status = status,
            message = message,
            data = data
        )

        // Assert: Attributes values are accurate
        assertThat(code).isEqualTo(baseResponse.code)
        assertThat(status).isEqualTo(baseResponse.status)
        assertThat(message).isEqualTo(baseResponse.message)
        assertThat(data).isEqualTo(baseResponse.data)
    }
}
