package net.engineeringdigest.journalApp.contoller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<JournalEntry> list = journalEntryService.getallEntry();
        if (list != null && !list.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry (@RequestBody JournalEntry x) {
        try {
            journalEntryService.saveEntry(x);
            return new ResponseEntity<>(x,HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/id/{myid}")
    public ResponseEntity<JournalEntry> getEntryBid(@PathVariable ObjectId myid) {
      Optional<JournalEntry> journalEntry = journalEntryService.getByid(myid);
      return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // @Requestbody used to map the http request data to domain object.
    @PutMapping("/id/{id}")
    public JournalEntry putNewEntry(@PathVariable ObjectId id,@RequestBody JournalEntry x) {
        JournalEntry old = journalEntryService.getByid(id).orElse(null);
        if (old!=null) {
            System.out.println("coming to here");
            old.setTitle(x.getTitle() != null || x.getTitle().isEmpty() ? x.getTitle() : old.getTitle());
            old.setContent(x.getContent() != null || x.getContent().isEmpty() ? x.getContent() : old.getTitle());
        }
        journalEntryService.saveEntry(old);
        return old;
    }

    @DeleteMapping("/id/{id}")
    public boolean deleteEntry(@PathVariable ObjectId id) {
        journalEntryService.deleteByid(id);
        return true;
    }
}
