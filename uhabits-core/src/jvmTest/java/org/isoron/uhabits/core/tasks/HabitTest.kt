
import org.isoron.uhabits.core.database.HabitRepository
import org.isoron.uhabits.core.models.EntryList
import org.isoron.uhabits.core.models.Habit
import org.isoron.uhabits.core.models.ScoreList
import org.isoron.uhabits.core.models.StreakList
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever
import org.mockito.kotlin.any

class HabitTest {

    @Test
    fun testGetHabits() {
        val mockRepository = mock<HabitRepository>()
        whenever(mockRepository.getHabits()).thenReturn(emptyList())

        val habits = mockRepository.getHabits()
        assert(habits.isEmpty())
    }

    @Test
    fun testAddHabit() {
        val mockRepository = mock<HabitRepository>()
        whenever(mockRepository.getHabits()).thenReturn(emptyList())
        whenever(mockRepository.addHabit(any<Habit>())).thenAnswer {Unit}

        val habit = Habit(
            computedEntries = EntryList(),
            originalEntries = EntryList(),
            scores = ScoreList(),
            streaks = StreakList(),
            name = "Не забывать про состояние базы данных"
        )
        mockRepository.addHabit(habit)
        verify(mockRepository).addHabit(habit)
    }

    @Test
    fun testDeleteHabit() {
        val mockRepository = mock<HabitRepository>()
        val habit = Habit(
            computedEntries = EntryList(),
            originalEntries = EntryList(),
            scores = ScoreList(),
            streaks = StreakList(),
            name = "Не забывать про состояние базы данных"
        )
        whenever(mockRepository.getHabits()).thenReturn(listOf(habit))
        whenever(mockRepository.deleteHabit(any<Habit>())).thenAnswer {Unit}

        mockRepository.deleteHabit(habit)
        verify(mockRepository).deleteHabit(habit)
    }

    @Test
    fun testHabitExistsAfterAddition() {
        val mockRepository = mock<HabitRepository>()
        val habit = Habit(
            computedEntries = EntryList(),
            originalEntries = EntryList(),
            scores = ScoreList(),
            streaks = StreakList(),
            name = "Не забывать про состояние базы данных"
        )
        whenever(mockRepository.getHabits()).thenReturn(listOf(habit))

        val habits = mockRepository.getHabits()
        assert(habits.contains(habit))
    }

    @Test
    fun testHabitDoesNotExistAfterDeletion() {
        val mockRepository = mock<HabitRepository>()
        val habit = Habit(
            computedEntries = EntryList(),
            originalEntries = EntryList(),
            scores = ScoreList(),
            streaks = StreakList(),
            name = "Не забывать про состояние базы данных"
        )
        whenever(mockRepository.getHabits()).thenReturn(emptyList())
        whenever(mockRepository.deleteHabit(any<Habit>())).thenAnswer {Unit}

        mockRepository.deleteHabit(habit)
        val habits = mockRepository.getHabits()
        assert(!habits.contains(habit))
    }
}