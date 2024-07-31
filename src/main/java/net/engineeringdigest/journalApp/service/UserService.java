package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
// This comes with lombok in dependency
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public  void saveEntry (User user) {
        try {
            userRepository.save(user);
        }
        catch (Exception e) {
            log.error("EXCEPTION", e);
        }
    }

    public  void saveNewEntry (User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("ADMIN"));
            userRepository.save(user);
        }
        catch (Exception e) {
            log.error("EXCEPTION", e);
        }
    }

    public List<User> getallEntry () {
        return userRepository.findAll();
    }

    // here optional is data type
    // do not think it will return list
    // we use optional when we have doubt it we will get value or not
   public Optional<User> getByid (ObjectId id) {
        return userRepository.findById(id);
   }

    public void deleteByid(ObjectId id) {
        userRepository.deleteById(id);
    }

    public void deleteEntry (String username) {
        try {
            User user = findByUserNamee(username);
            userRepository.deleteById(user.getId());
        }
        catch (Exception e) {
            throw new RuntimeException("An error occurred while saving the entry.", e);
        }
    }

    public User findByUserNamee(String userName) {
        return userRepository.findByUserName(userName);
    }
}
