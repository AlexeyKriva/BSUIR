package org.example.labapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class SimpleRestController {
    private InMemoryUserDetailsManager userDetailsManager;
    private List<UserDetails> users = new ArrayList<>();

    @Autowired
    public SimpleRestController(InMemoryUserDetailsManager userDetailsManager) {
        this.userDetailsManager = userDetailsManager;

        users.add(userDetailsManager.loadUserByUsername("admin"));
        users.add(userDetailsManager.loadUserByUsername("user"));
        users.add(userDetailsManager.loadUserByUsername("guest"));
    }

    @GetMapping("/admin")
    public ResponseEntity<List<UserDetails>> admin() {
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/admin/add")
    public ResponseEntity<String> registration(
            @RequestParam("username") String username, @RequestParam("password") String password,
            @RequestParam("role") String role) {
        UserDetails newUser = User.withDefaultPasswordEncoder()
                .username(username)
                .password(password)
                .roles(role)
                .build();

        userDetailsManager.createUser(newUser);
        users.add(newUser);
        return new ResponseEntity<>(role.toLowerCase() + " " + username + " was successfully added.",
                HttpStatus.OK);
    }

    @GetMapping("/admin/update")
    public ResponseEntity<String> updateUser(
            @RequestParam(name = "oldUsername") String oldUsername,
            @RequestParam(name = "newUsername", required = false) String newUsername,
            @RequestParam(name = "newPassword", required = false) String newPassword,
            @RequestParam(name = "newRole", required = false) String newRole) {

        for (UserDetails userDetails : users) {

            if (userDetails.getUsername().equals(oldUsername)) {
                UserDetails newUser = User.withDefaultPasswordEncoder()
                        .username(newUsername != null ? newUsername : oldUsername)
                        .password(newPassword != null ? newPassword : userDetails.getPassword())
                        .roles(newRole != null ? newRole : userDetails.getAuthorities().stream()
                                .map(GrantedAuthority::getAuthority)
                                .map(role -> role.replaceFirst("^ROLE_", ""))
                                .toArray(String[]::new)[0])
                        .build();

                users.remove(userDetails);

                users.add(newUser);

                userDetailsManager.deleteUser(oldUsername);

                userDetailsManager.createUser(newUser);

                return new ResponseEntity<>("User with username " + oldUsername + " successfully changed.",
                        HttpStatus.OK);
            }
        }

        return new ResponseEntity<>("User with username " + oldUsername + " no found.",
                HttpStatus.NOT_FOUND);
    }

    @GetMapping("/admin/delete")
    public ResponseEntity<String> deleteUser(
            @RequestParam("username") String username) {
        userDetailsManager.deleteUser(username);

        for (UserDetails userDetails : users) {
            if (userDetails.getUsername().equals(username)) {
                users.remove(userDetails);
                break;
            }
        }
        return new ResponseEntity<>(username + " was successfully deleted.",
                HttpStatus.OK);
    }

    @GetMapping("/admin/down")
    public ResponseEntity<String> dropWebApp(@RequestParam("pushDown") Boolean pushDown) {
        if (pushDown) {
            System.exit(0);
        }

        return new ResponseEntity<>("pushDown should be true to off web-app", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/user")
    public ResponseEntity<String> user() {
        return new ResponseEntity<>("Hello from user.", HttpStatus.OK);
    }

    @GetMapping("/guest")
    public ResponseEntity<String> guest() {
        return new ResponseEntity<>("Hello from guest.", HttpStatus.OK);
    }
}
