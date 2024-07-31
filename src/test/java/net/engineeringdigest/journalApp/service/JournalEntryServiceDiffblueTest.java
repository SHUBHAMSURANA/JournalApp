package net.engineeringdigest.journalApp.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JournalEntryService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class JournalEntryServiceDiffblueTest {
    @MockBean
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private JournalEntryService journalEntryService;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link JournalEntryService#saveEntry(JournalEntry)}
     */
    @Test
    void testSaveEntry() {
        // Arrange
        JournalEntry journalEntry = new JournalEntry();
        journalEntry.setContent("Not all who wander are lost");
        journalEntry.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        journalEntry.setId(ObjectId.get());
        journalEntry.setTitle("Dr");
        when(journalEntryRepository.save(Mockito.<JournalEntry>any())).thenReturn(journalEntry);

        JournalEntry journalEntry2 = new JournalEntry();
        journalEntry2.setContent("Not all who wander are lost");
        journalEntry2.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        journalEntry2.setId(ObjectId.get());
        journalEntry2.setTitle("Dr");

        // Act
        journalEntryService.saveEntry(journalEntry2);

        // Assert
        verify(journalEntryRepository).save(isA(JournalEntry.class));
    }

    /**
     * Method under test: {@link JournalEntryService#getallEntry()}
     */
    @Test
    void testGetallEntry() {
        // Arrange
        ArrayList<JournalEntry> journalEntryList = new ArrayList<>();
        when(journalEntryRepository.findAll()).thenReturn(journalEntryList);

        // Act
        List<JournalEntry> actualGetallEntryResult = journalEntryService.getallEntry();

        // Assert
        verify(journalEntryRepository).findAll();
        assertTrue(actualGetallEntryResult.isEmpty());
        assertSame(journalEntryList, actualGetallEntryResult);
    }

    /**
     * Method under test: {@link JournalEntryService#getByid(ObjectId)}
     */
    @Test
    void testGetByid() {
        // Arrange
        JournalEntry journalEntry = new JournalEntry();
        journalEntry.setContent("Not all who wander are lost");
        journalEntry.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        journalEntry.setId(ObjectId.get());
        journalEntry.setTitle("Dr");
        Optional<JournalEntry> ofResult = Optional.of(journalEntry);
        when(journalEntryRepository.findById(Mockito.<ObjectId>any())).thenReturn(ofResult);

        // Act
        Optional<JournalEntry> actualByid = journalEntryService.getByid(ObjectId.get());

        // Assert
        verify(journalEntryRepository).findById(isA(ObjectId.class));
        assertSame(ofResult, actualByid);
    }

    /**
     * Method under test: {@link JournalEntryService#putEntry(JournalEntry)}
     */
    @Test
    void testPutEntry() {
        // Arrange
        JournalEntry journalEntry = new JournalEntry();
        journalEntry.setContent("Not all who wander are lost");
        journalEntry.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        journalEntry.setId(ObjectId.get());
        journalEntry.setTitle("Dr");
        when(journalEntryRepository.insert(Mockito.<JournalEntry>any())).thenReturn(journalEntry);

        JournalEntry x = new JournalEntry();
        x.setContent("Not all who wander are lost");
        x.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        x.setId(ObjectId.get());
        x.setTitle("Dr");

        // Act
        journalEntryService.putEntry(x);

        // Assert that nothing has changed
        verify(journalEntryRepository).insert(isA(JournalEntry.class));
    }

    /**
     * Method under test: {@link JournalEntryService#deleteByid(ObjectId)}
     */
    @Test
    void testDeleteByid() {
        // Arrange
        doNothing().when(journalEntryRepository).deleteById(Mockito.<ObjectId>any());

        // Act
        journalEntryService.deleteByid(ObjectId.get());

        // Assert that nothing has changed
        verify(journalEntryRepository).deleteById(isA(ObjectId.class));
    }

    /**
     * Method under test:
     * {@link JournalEntryService#saveEntryByUser(JournalEntry, String)}
     */
    @Test
    void testSaveEntryByUser() {
        // Arrange
        JournalEntry journalEntry = new JournalEntry();
        journalEntry.setContent("Not all who wander are lost");
        journalEntry.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        journalEntry.setId(ObjectId.get());
        journalEntry.setTitle("Dr");
        when(journalEntryRepository.save(Mockito.<JournalEntry>any())).thenReturn(journalEntry);
        doNothing().when(userService).saveEntry(Mockito.<User>any());
        when(userService.findByUserNamee(Mockito.<String>any())).thenReturn(new User("janedoe", "iloveyou"));

        JournalEntry journalEntry2 = new JournalEntry();
        journalEntry2.setContent("Not all who wander are lost");
        journalEntry2.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        journalEntry2.setId(ObjectId.get());
        journalEntry2.setTitle("Dr");

        // Act
        journalEntryService.saveEntryByUser(journalEntry2, "janedoe");

        // Assert
        verify(userService).findByUserNamee(eq("janedoe"));
        verify(userService).saveEntry(isA(User.class));
        verify(journalEntryRepository).save(isA(JournalEntry.class));
    }

    /**
     * Method under test:
     * {@link JournalEntryService#deleteByUsername(ObjectId, String)}
     */
    @Test
    void testDeleteByUsername() {
        // Arrange
        doNothing().when(journalEntryRepository).deleteById(Mockito.<ObjectId>any());
        doNothing().when(userService).saveEntry(Mockito.<User>any());
        when(userService.findByUserNamee(Mockito.<String>any())).thenReturn(new User("janedoe", "iloveyou"));

        // Act
        journalEntryService.deleteByUsername(ObjectId.get(), "janedoe");

        // Assert
        verify(userService).findByUserNamee(eq("janedoe"));
        verify(userService).saveEntry(isA(User.class));
        verify(journalEntryRepository).deleteById(isA(ObjectId.class));
    }
}
