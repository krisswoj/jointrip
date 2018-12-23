package pl.jointrip.dao;

import org.hibernate.sql.Update;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.jointrip.models.entities.documents.ImagesStore;
import pl.jointrip.models.entities.trip.Trip;

import javax.transaction.Transactional;

@Repository
public interface ImagesStoreRepository extends CrudRepository<ImagesStore, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE ImagesStore i SET i.mainTripImg = 0 WHERE i.mainTripImg = 1 AND i.tripId = :trip")
    void findByMainTripImgAndSetZero(@Param("trip") Trip trip);
}
