package pl.jointrip.services.tripService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.jointrip.dao.CommentsRepository;
import pl.jointrip.dao.TripRepository;
import pl.jointrip.dao.UserRepository;
import pl.jointrip.models.Comments;
import pl.jointrip.models.Trip;
import pl.jointrip.models.User;

import java.util.Date;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TripRepository tripRepository;
    @Autowired
    CommentsRepository commentsRepository;

    @Override
    public boolean saveTrip(Trip trip) {

        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername());


        trip.setTripCreateDate(new Date());
        trip.setTripEditDate(new Date());
        trip.setTripStatus(0);
        trip.setUserByUserId(user);
        try {
            tripRepository.save(trip);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean saveComment(Comments comment, int tripId) {

        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername());

        comment.setUserId(user.getUserId());
        comment.setTripId(tripId);
        try {
            commentsRepository.save(comment);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
