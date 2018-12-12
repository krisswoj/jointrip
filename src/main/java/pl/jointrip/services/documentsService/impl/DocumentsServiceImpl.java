package pl.jointrip.services.documentsService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.jointrip.dao.DocumentsRepository;
import pl.jointrip.models.Documentstore;
import pl.jointrip.models.User;
import pl.jointrip.services.documentsService.DocumentsService;

import java.io.IOException;
import java.util.Date;

@Service
public class DocumentsServiceImpl implements DocumentsService {
    @Autowired
    DocumentsRepository documentsRepository;

    public boolean saveDocument(byte[] content, User loggedUser, String fileName, String contentType, Integer documentKind) {
        Documentstore documentstore = documentStoreMapper(content, loggedUser, fileName, contentType, documentKind);
        try {
            documentsRepository.save(documentstore);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public byte[] handleUploadFile(MultipartFile file) {
        byte[] bytes;
        try {
            bytes = file.getBytes();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Documentstore documentStoreMapper(byte[] content, User loggedUser, String fileName, String contentType, Integer documentKind) {
        Documentstore docStore = new Documentstore();
        docStore.setFile(content);
        docStore.setUserId(loggedUser);
        docStore.setFilename(fileName);
        docStore.setCreatedate(new Date());
        docStore.setModifydate(new Date());
        docStore.setContentType(contentType);
        docStore.setFilestatus(0);
        docStore.setDocumentKind(documentKind);
        return docStore;
    }
}
