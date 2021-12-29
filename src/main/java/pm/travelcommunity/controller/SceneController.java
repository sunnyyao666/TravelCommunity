package pm.travelcommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pm.travelcommunity.controller.request.SceneRequest;
import pm.travelcommunity.domain.*;
import pm.travelcommunity.domain.scene.*;
import pm.travelcommunity.exception.*;
import pm.travelcommunity.repository.scene.*;
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
    private final ContributionRepository contributionRepository;
    private final SceneCommentRepository sceneCommentRepository;
    private final ContributionCommentRepository contributionCommentRepository;

    @Autowired
    public SceneController(UserRepository userRepository, SceneRepository sceneRepository, SceneStarRepository sceneStarRepository, ContributionRepository contributionRepository, SceneCommentRepository sceneCommentRepository, ContributionCommentRepository contributionCommentRepository) {
        this.userRepository = userRepository;
        this.sceneRepository = sceneRepository;
        this.sceneStarRepository = sceneStarRepository;
        this.contributionRepository = contributionRepository;
        this.sceneCommentRepository = sceneCommentRepository;
        this.contributionCommentRepository = contributionCommentRepository;
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

    @PostMapping(value = "/star")
    public ResponseEntity<?> updateStar(@RequestBody SceneRequest request) throws BadCredentialsException, ErrorIDException {
        User user = findUserByUsername(request.getUsername());
        Scene scene = findSceneById(user.getId(), request.getSceneID());
        if (scene.isStared()) {
            SceneStar sceneStar = sceneStarRepository.findByUser_IdAndScene_Id(user.getId(), scene.getId());
            user.removeSceneStar(sceneStar);
            scene.removeSceneStar(sceneStar);
            sceneStarRepository.delete(sceneStar);
            scene.setStared(false);
            userRepository.save(user);
            sceneRepository.save(scene);
        } else {
            SceneStar sceneStar = new SceneStar(user, scene);
            sceneStarRepository.save(sceneStar);
            scene.setStared(true);
        }
        return ResponseEntity.ok(scene);
    }

    @PostMapping(value = "/contribute")
    public ResponseEntity<?> createContribution(@RequestBody SceneRequest request) throws BadCredentialsException, ErrorIDException {
        User user = findUserByUsername(request.getUsername());
        Scene scene = findSceneById(user.getId(), request.getSceneID());
        Contribution contribution = new Contribution(user, scene, request.getTitle(), request.getContent());
        contributionRepository.save(contribution);
        return ResponseEntity.ok(contribution);
    }

    @PostMapping(value = "/comment")
    public ResponseEntity<?> createSceneComment(@RequestBody SceneRequest request) throws BadCredentialsException, ErrorIDException {
        User user = findUserByUsername(request.getUsername());
        Scene scene = findSceneById(user.getId(), request.getSceneID());
        SceneComment sceneComment = new SceneComment(user, scene, request.getContent(), request.getScore());
        sceneCommentRepository.save(sceneComment);
        scene.addSceneComment(sceneComment);
        return ResponseEntity.ok(scene);
    }

    @PostMapping(value = "/commentContribution")
    public ResponseEntity<?> createContributionComment(@RequestBody SceneRequest request) throws BadCredentialsException, ErrorIDException {
        User user = findUserByUsername(request.getUsername());
        Contribution contribution = contributionRepository.findById(request.getContributionID());
        if (contribution == null) {
            throw new ErrorIDException("Contribution", request.getContributionID());
        }
        ContributionComment contributionComment = new ContributionComment(user, contribution, request.getContent(), request.getScore());
        contributionCommentRepository.save(contributionComment);
        contribution.addContributionComment(contributionComment);
        return ResponseEntity.ok(contribution);
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

