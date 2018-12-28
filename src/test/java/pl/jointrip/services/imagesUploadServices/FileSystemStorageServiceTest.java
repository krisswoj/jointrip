package pl.jointrip.services.imagesUploadServices;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import pl.jointrip.models.entities.trip.Trip;
import pl.jointrip.models.entities.user.User;
import pl.jointrip.services.imagesUploadServices.core.StorageProperties;

@SpringBootTest
public class FileSystemStorageServiceTest {

    @Mock
    StorageProperties properties;


    @Test
    public void testGenerateName(){


        String oryginalFileName = "dupajasiu.jpg";

        Trip trip = new Trip();
        trip.setId(35);

        User user = new User();
        user.setUserId(33);


        String[] splited = oryginalFileName.split("\\.");
        String generatedString = RandomStringUtils.randomAlphanumeric(10).toLowerCase();
        String newName =  String.format("%s_%s_%s.%s", trip.getId(), user.getUserId(), generatedString, splited[0]);

        System.out.println(newName);

    }

}