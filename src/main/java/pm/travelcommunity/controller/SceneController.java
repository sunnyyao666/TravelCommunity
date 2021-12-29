package pm.travelcommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pm.travelcommunity.controller.request.SceneRequest;
import pm.travelcommunity.domain.*;
import pm.travelcommunity.domain.scene.*;
import pm.travelcommunity.exception.BadCredentialsException;
import pm.travelcommunity.exception.ErrorIDException;
import pm.travelcommunity.repository.SceneRepository;
import pm.travelcommunity.repository.SceneStarRepository;
import pm.travelcommunity.repository.UserRepository;

import java.util.Set;

/**
 * @author YHT
 **/
@RestController
@RequestMapping(value = "/scene")
public class SceneController {
    private final UserRepository userRepository;
    private final SceneRepository sceneRepository;
    private final SceneStarRepository sceneStarRepository;

    @Autowired
    public SceneController(UserRepository userRepository, SceneRepository sceneRepository, SceneStarRepository sceneStarRepository) {
        this.userRepository = userRepository;
        this.sceneRepository = sceneRepository;
        this.sceneStarRepository = sceneStarRepository;
    }

    @PostMapping(value = "/getAll")
    public ResponseEntity<?> getAllScenes(@RequestBody SceneRequest request) throws BadCredentialsException {
        boolean f = false;
        int userID = 0;
        int sceneID = request.getSceneID();
        if (request.getUsername() != null && !"".equals(request.getUsername())) {
            f = true;
            User user = findUserByUsername(request.getUsername());
            userID = user.getId();
        }
        Set<Scene> result = sceneRepository.findAllByOrderByCreateTimeDesc();
        if (f) {
            for (Scene scene : result) {
                SceneStar sceneStar = sceneStarRepository.findByUser_IdAndScene_Id(userID, sceneID);
                if (sceneStar != null) {
                    scene.setStared(true);
                }
            }
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/get")
    public ResponseEntity<?> getScene(@RequestBody SceneRequest request) throws BadCredentialsException, ErrorIDException {
        if (request.getUsername() == null || "".equals(request.getUsername())) {
            return ResponseEntity.ok(findSceneById(0, request.getSceneID()));
        }
        User user = findUserByUsername(request.getUsername());
        return ResponseEntity.ok(findSceneById(user.getId(), request.getSceneID()));
    }

    private User findUserByUsername(String username) throws BadCredentialsException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new BadCredentialsException();
        }
        return user;
    }

    private Scene findSceneById(int userID, int sceneID) throws ErrorIDException {
        Scene scene = sceneRepository.findById(sceneID);
        if (scene == null) {
            throw new ErrorIDException("Scene", sceneID);
        }
        if (userID == 0) {
            return scene;
        }
        SceneStar sceneStar = sceneStarRepository.findByUser_IdAndScene_Id(userID, sceneID);
        if (sceneStar != null) {
            scene.setStared(true);
        }
        return scene;
    }
}

