package net.engineeringdigest.journalApp.contoller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journaluser")
public class JournalUserController {


    private JournalEntryService journalEntryService;
    private UserService userService;

    public JournalUserController(JournalEntryService journalEntryService, UserService userService) {
        this.journalEntryService = journalEntryService;
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<?> getAllJournalentryofuser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserNamee(userName);
        if (user != null) {
            List<JournalEntry> list = user.getJournalEntries();
            if (list != null && !list.isEmpty()) {
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<JournalEntry> createEntry (@RequestBody JournalEntry x) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            journalEntryService.saveEntryByUser(x,userName);
            return new ResponseEntity<>(x,HttpStatus.CREATED);

        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<JournalEntry> updateEntry (@RequestBody JournalEntry x) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            User user = userService.findByUserNamee(userName);
            if (user != null) {
                List<JournalEntry> list = user.getJournalEntries();
                if (list != null && !list.isEmpty()) {
                    list.stream()
                            .filter(l->l.getTitle().equals(x.getTitle()))
                            .peek(l -> System.out.println(l.getTitle()))
                            .forEach(l -> {
                                l.setContent(x.getContent());
                                journalEntryService.saveEntry(l);
                            });
                    user.setJournalEntries(list);
                    userService.saveEntry(user);
                    return new ResponseEntity<>(x,HttpStatus.CREATED);
                }
            }
            return new ResponseEntity<>(x,HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
