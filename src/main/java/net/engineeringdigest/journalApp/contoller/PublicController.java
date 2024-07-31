package net.engineeringdigest.journalApp.contoller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/public-check")
    public String healthCheck() {
        return "OK shubham bhhaiia ";
    }

    @PostMapping
    public ResponseEntity<User> createEntry (@RequestBody User x) {
        System.out.println("coming to here ******************4");
        try {
            userService.saveNewEntry(x);
            return new ResponseEntity<>(x, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
