package net.engineeringdigest.journalApp.contoller;

import net.engineeringdigest.journalApp.api.response.WeathertResponse;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.EmailService;
import net.engineeringdigest.journalApp.service.UserService;
import net.engineeringdigest.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    EmailService emailService;

    @GetMapping
    public List<User> getAll() {
//        System.out.println("coming to here ******************3");
//        String to = "sictlove99@gmail.com";
//        String subject ="Testing Java mail sender";
//        String text ="Hi, aap kaise hain ?";
//        //emailService.sendEmail(to,subject,text);
        return userService.getallEntry();
    }

    //@RolesAllowed({ "ROLE_ADMIN", "ROLE_USER" })
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

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userindb = userService.findByUserNamee(userName);
        if (userindb != null) {
            userindb.setUserName(user.getUserName());
            userindb.setPassword(user.getPassword());
            userService.saveEntry(userindb);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @PutMapping("/{userName}")
//    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName) {
//        User userindb = userService.findByUserNamee(userName);
//        if (userindb != null) {
//            userindb.setUserName(user.getUserName());
//            userindb.setPassword(user.getPassword());
//            userService.saveEntry(userindb);
//            return new ResponseEntity<>(HttpStatus.CREATED);
//        }
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

    @DeleteMapping("/{userName}")
    public ResponseEntity<?> deleteUser(@PathVariable String userName) {
        try {
            userService.deleteEntry(userName);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/greetings")
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        WeathertResponse weathertResponse = weatherService.getWeather("Mumbai");
        String greeting = "inside iff";
        if (weathertResponse != null) {
            greeting = "Weather feels like " + "min temp " + weathertResponse.getMain().getFeelsLike();
       }
        return new ResponseEntity<>("hi good "+ userName + greeting, HttpStatus.OK);
    }

}
