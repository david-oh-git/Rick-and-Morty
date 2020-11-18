/*
 * MIT License
 *
 * Copyright (c) $today.day/$today.month/2020 $today.hour24:$today.minute   David Osemwota.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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
