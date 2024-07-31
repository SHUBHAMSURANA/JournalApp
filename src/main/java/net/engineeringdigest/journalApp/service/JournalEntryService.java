package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
// This comes with lombok in dependency
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    public  void saveEntry (JournalEntry journalEntry) {
        try {
            journalEntry.setDate(LocalDateTime.now());
            journalEntryRepository.save(journalEntry);
        }
        catch (Exception e) {
            log.error("EXCEPTION", e);
        }
    }

    public List<JournalEntry> getallEntry () {
        return journalEntryRepository.findAll();
    }

    // here optional is data type
    // do not think it will return list
    // we use optional when we have doubt it we will get value or not
   public Optional<JournalEntry> getByid (ObjectId id) {
        return journalEntryRepository.findById(id);
   }

    public void putEntry(JournalEntry x) {
        journalEntryRepository.insert(x);
    }

    public void deleteByid(ObjectId id) {
        journalEntryRepository.deleteById(id);
    }

    // It will create one Context for spring to run , now it need some bean to execute it , so that bean will be created in main file
    @Transactional
    public  void saveEntryByUser (JournalEntry journalEntry, String username) {
        try {
            User user = userService.findByUserNamee(username);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while saving the entry.", e);
        }
    }

    public void deleteByUsername(ObjectId id, String userName) {
        User user = userService.findByUserNamee(userName);
        user.getJournalEntries().removeIf(x ->x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);
    }
}
