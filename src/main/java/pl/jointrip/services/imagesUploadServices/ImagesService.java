package pl.jointrip.services.imagesUploadServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.jointrip.dao.ImagesStoreRepository;
import pl.jointrip.models.entities.documents.ImagesStore;
import pl.jointrip.models.entities.trip.Trip;
import pl.jointrip.models.entities.user.User;
import pl.jointrip.services.imagesUploadServices.core.StorageService;
import pl.jointrip.services.userService.UserService;

@Service
public class ImagesService {

    @Autowired
    UserService userService;

    @Autowired
    StorageService storageService;

    @Autowired
    ImagesStoreRepository imagesStoreRepository;

    public ImagesStore saveImage(MultipartFile file, Trip trip, ImagesStore imagesStore){

        if(imagesStore.getMainTripImg() == 1)
            imagesStoreRepository.findByMainTripImgAndSetZero(trip);

        User user = userService.getLoggedUser();
        String fileName = storageService.storeFromTrip(file, trip, user);

        if(!fileName.isEmpty()){
            imagesStore.setImgName(fileName);
            imagesStore.setUserId(user);
            imagesStore.setTripId(trip);
            return imagesStoreRepository.save(imagesStore);
        }
        return null;
    }
}
