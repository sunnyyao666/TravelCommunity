package pm.travelcommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pm.travelcommunity.controller.request.UserAuthRequest;
import pm.travelcommunity.domain.User;
import pm.travelcommunity.exception.BadCredentialsException;
import pm.travelcommunity.exception.DuplicateUsernameException;
import pm.travelcommunity.repository.UserRepository;

/**
 * @author: YHT
 **/
@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    private final UserRepository userRepository;

    @Autowired
    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/hello")
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok("1");
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> userLogin(@RequestBody UserAuthRequest request) throws BadCredentialsException {
        User user = userRepository.findByUsername(request.getUsername());
        if (user == null) {
            throw new BadCredentialsException();
        }

        if (!user.getPassword().equals(request.getPassword())) {
            throw new BadCredentialsException();
        }

        return ResponseEntity.ok(user);
    }

    @PostMapping("/user/register")
    public ResponseEntity<?> userRegister(@RequestBody UserAuthRequest request) throws DuplicateUsernameException {
        String username = request.getUsername();
        User user = userRepository.findByUsername(username);
        if (user != null) {
            throw new DuplicateUsernameException(username);
        }

        user = new User(username, request.getPassword(), request.getEmail(), request.getPhone());
        userRepository.save(user);

        return ResponseEntity.ok(user);
    }
}

