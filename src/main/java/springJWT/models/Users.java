package springJWT.models;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Users {

    Map<String, User> users = new HashMap<>();

    public User getUser(String username){
        return users.get(username);
    }

    public List<User> getAllUsers(){
        return new ArrayList<>(users.values());
    }

    public void putUser(User user){
        String username = user.getUsername();
        users.put(username, user);
    }
}
