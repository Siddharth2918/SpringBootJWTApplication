package springJWT.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import springJWT.models.Users;
import springJWT.models.User;
import springJWT.utils.JWTUtils;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private Users usersRepo;

    @Autowired
    private JWTUtils jwtUtils;


    @PostMapping("/users/createUser")
    public ResponseEntity<?> createUser(@RequestBody User user){

        String username = user.getUsername();

        if(usersRepo.getUser(username)!= null) return ResponseEntity.badRequest().body("User Already Exists!");

        usersRepo.putUser(user);
        return ResponseEntity.ok("User Added successfully");
    }

    @PostMapping("/users/signIn")
    public ResponseEntity<?> signInUser(@RequestBody User user){

        String username = user.getUsername();

        if(usersRepo.getUser(username)==null) return ResponseEntity.badRequest().body("User does not exist!");

        if(!user.getPassword().equals(usersRepo.getUser(user.getUsername()).getPassword())) return ResponseEntity.badRequest().body("Wrong Password!");

        Map<String , Object> claims = new HashMap<>();
        String token = jwtUtils.createToken(claims, user.getUsername());
        return ResponseEntity.ok(token);
    }

    @GetMapping("/allUsers")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(usersRepo.getAllUsers());
    }
}

