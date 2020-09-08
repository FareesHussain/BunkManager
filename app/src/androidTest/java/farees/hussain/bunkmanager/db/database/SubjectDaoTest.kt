package farees.hussain.bunkmanager.db.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import farees.hussain.bunkmanager.db.model.Subject
import farees.hussain.bunkmanager.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/*for runBlockingTest*/
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class SubjectDaoTest {
    //! error: This job has not completed yet
    /* this is for the above error */
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: SubjectItemsDatabase
    private lateinit var dao: SubjectDao

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            SubjectItemsDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.subjectDao()
    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun insertSubjectItem() = runBlockingTest {
        val subjectItem = Subject("physics","not implemented","not implemented",4,5,id=1)
        dao.insertSubjectItem(subjectItem)
        val allShoppingItems = dao.observeAllSubjectItems().getOrAwaitValue()
        assertThat(allShoppingItems).contains(subjectItem)
    }
    @Test
    fun deleteSubjectItem() = runBlockingTest {
        val subjectItem = Subject("physics","not implemented","not implemented",4,5,id=1)
        dao.insertSubjectItem(subjectItem)
        dao.deleteSubjectItem(subjectItem)
        val allShoppingItems = dao.observeAllSubjectItems().getOrAwaitValue()
        assertThat(allShoppingItems).doesNotContain(subjectItem)
    }
}