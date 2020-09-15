package io.audioshinigami.core.network.responses

import com.google.common.truth.Truth
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock

internal class BaseListResponseTest {

    @Suppress("UNCHECKED_CAST")
    @Test
    @DisplayName("When a BaseListResponse class is created, its attributes should be correct")
    fun createBaseResponseList_getCorrectAttributes(){
        // Arrange
        val code =  200
        val status = "Ok"
        val message = "Success"
        val data: DataResponse<String> = mock(DataResponse::class.java , "FAKE RESPONSE")
            as DataResponse<String>

        // Act: create BaseListResponse instance
        val baseListResponse = BaseListResponse(
            code = code,
            status = status,
            message = message,
            data = data
        )

        // Assert: Attributes values are accurate
        Truth.assertThat(code).isEqualTo( baseListResponse.code)
        Truth.assertThat(status).isEqualTo(baseListResponse.status)
        Truth.assertThat(message).isEqualTo(baseListResponse.message)
        Truth.assertThat(data).isEqualTo(baseListResponse.data)
    }
}