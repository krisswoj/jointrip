package pl.jointrip.controllers.logged.documentsApproval;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import pl.jointrip.models.entities.trip.Trip;
import pl.jointrip.models.entities.user.User;
import pl.jointrip.services.imagesUploadServices.core.StorageFileNotFoundException;
import pl.jointrip.services.imagesUploadServices.core.StorageService;

import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;

@Controller
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/u")
    public String listUploadedFiles(Model model){

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @PostMapping("/u")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {

        User user = new User();
        user.setUserId(1);

        Trip trip = new Trip();
        trip.setId(1);


//        storageService.store(file);
        storageService.storeFromTrip(file, trip, user);
        return "redirect:/u";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<byte[]> serveFile(@PathVariable String filename) throws IOException {
        byte[] image = Files.readAllBytes(storageService.load(filename));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(image.length);
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
