package net.engineeringdigest.journalApp.entity;

import com.mongodb.connection.ProxySettings;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Document(collection = "users")
@Data

public class User {
    @Id
    private ObjectId id;
    // Data Type for mongo db id

    @Indexed(unique = true)
    @NonNull
    private String userName;
    @NonNull
    private String password;
    private List<String> roles;
    private String email;
    private boolean sentimentAnalysis;
    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();

}
