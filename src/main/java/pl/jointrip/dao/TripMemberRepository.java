package pl.jointrip.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jointrip.models.entities.trip.TripMember;
import pl.jointrip.models.entities.user.User;

import java.util.List;

@Repository
public interface TripMemberRepository extends CrudRepository <TripMember, Integer> {

    List<TripMember> findAllByTripMember(User user);
    TripMember findById(int id);
}
