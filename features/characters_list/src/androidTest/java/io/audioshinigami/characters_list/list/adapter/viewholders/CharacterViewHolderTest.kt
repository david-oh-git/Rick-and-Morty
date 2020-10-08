package io.audioshinigami.characters_list.list.adapter.viewholders

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import io.audioshinigami.characters_list.databinding.ListItemCharacterBinding
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockkStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@SmallTest
class CharacterViewHolderTest {

    // TODO  fix issue as test wont run
    @MockK
    lateinit var view: View
    @MockK
    lateinit var layoutInflater: LayoutInflater
    @MockK(relaxed = true)
    lateinit var binding: ListItemCharacterBinding
    lateinit var viewHolder: CharacterViewHolder

    @Before
    fun init(){
        MockKAnnotations.init(this)
    }

    @Test
    fun createViewHolder_ShouldInitializeCorrectly(){
        mockkStatic(ListItemCharacterBinding::class)
        every { (binding as ViewDataBinding).root } returns view
        every { ListItemCharacterBinding.inflate(layoutInflater) } returns binding

        viewHolder = CharacterViewHolder(layoutInflater)

        assertThat(viewHolder.binding).isEqualTo(binding)
    }
}