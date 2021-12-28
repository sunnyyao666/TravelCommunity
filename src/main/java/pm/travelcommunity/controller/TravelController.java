package pm.travelcommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pm.travelcommunity.controller.request.TravelRequest;
import pm.travelcommunity.domain.*;
import pm.travelcommunity.domain.travel.*;
import pm.travelcommunity.exception.BadCredentialsException;
import pm.travelcommunity.exception.ErrorIDException;
import pm.travelcommunity.repository.*;

/**
 * @author YHT
 **/
@RestController
@RequestMapping(value = "/travel")
public class TravelController {
    private final UserRepository userRepository;
    private final TravelRepository travelRepository;
    private final TravelCommentRepository travelCommentRepository;
    private final TravelLikeRepository travelLikeRepository;
    private final TravelStarRepository travelStarRepository;

    @Autowired
    public TravelController(UserRepository userRepository, TravelRepository travelRepository, TravelCommentRepository travelCommentRepository, TravelLikeRepository travelLikeRepository, TravelStarRepository travelStarRepository) {
        this.userRepository = userRepository;
        this.travelRepository = travelRepository;
        this.travelCommentRepository = travelCommentRepository;
        this.travelLikeRepository = travelLikeRepository;
        this.travelStarRepository = travelStarRepository;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createTravel(@RequestBody TravelRequest request) throws BadCredentialsException {
        User user = findUserByUsername(request.getUsername());
        Travel travel = new Travel(user, request.getContent());
        travelRepository.save(travel);
        return ResponseEntity.ok(travel);
    }

    @PostMapping(value = "/comment")
    public ResponseEntity<?> createComment(@RequestBody TravelRequest request) throws BadCredentialsException, ErrorIDException {
        User user = findUserByUsername(request.getUsername());
        Travel travel = findTravelByTravelID(user.getId(), request.getTravelID());
        TravelComment comment = new TravelComment(user, travel, request.getContent());
        travelCommentRepository.save(comment);
        travel.addTravelComment(comment);
        return ResponseEntity.ok(travel);
    }

    @PostMapping(value = "/like")
    public ResponseEntity<?> updateLike(@RequestBody TravelRequest request) throws BadCredentialsException, ErrorIDException {
        User user = findUserByUsername(request.getUsername());
        Travel travel = findTravelByTravelID(user.getId(), request.getTravelID());
        int update = 1;
        if (travel.isLiked()) {
            TravelLike travelLike = travelLikeRepository.findByUser_IdAndTravel_Id(user.getId(), travel.getId());
            user.removeTravelLike(travelLike);
            travel.removeTravelLike(travelLike);
            travelLikeRepository.delete(travelLike);
            update = -1;
            travel.setLiked(false);
            userRepository.save(user);
        } else {
            TravelLike travelLike = new TravelLike(user, travel);
            travelLikeRepository.save(travelLike);
            travel.setLiked(true);
        }
        travel.setLikeNumber(travel.getLikeNumber() + update);
        travelRepository.save(travel);
        return ResponseEntity.ok(travel);
    }

    @PostMapping(value = "/star")
    public ResponseEntity<?> updateStar(@RequestBody TravelRequest request) throws BadCredentialsException, ErrorIDException {
        User user = findUserByUsername(request.getUsername());
        Travel travel = findTravelByTravelID(user.getId(), request.getTravelID());
        if (travel.isStared()) {
            TravelStar travelStar = travelStarRepository.findByUser_IdAndTravel_Id(user.getId(), travel.getId());
            user.removeTravelStar(travelStar);
            travel.removeTravelStar(travelStar);
            travelStarRepository.delete(travelStar);
            travel.setStared(false);
            userRepository.save(user);
            travelRepository.save(travel);
        } else {
            TravelStar travelStar = new TravelStar(user, travel);
            travelStarRepository.save(travelStar);
            travel.setStared(true);
        }
        return ResponseEntity.ok(travel);
    }

    private User findUserByUsername(String username) throws BadCredentialsException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new BadCredentialsException();
        }
        return user;
    }

    private Travel findTravelByTravelID(int userID, int travelID) throws ErrorIDException {
        Travel travel = travelRepository.findById(travelID);
        if (travel == null) {
            throw new ErrorIDException("Travel", travelID);
        }
        TravelLike travelLike = travelLikeRepository.findByUser_IdAndTravel_Id(userID, travelID);
        if (travelLike != null) {
            travel.setLiked(true);
        }
        TravelStar travelStar = travelStarRepository.findByUser_IdAndTravel_Id(userID, travelID);
        if (travelStar != null) {
            travel.setStared(true);
        }
        return travel;
    }
}

