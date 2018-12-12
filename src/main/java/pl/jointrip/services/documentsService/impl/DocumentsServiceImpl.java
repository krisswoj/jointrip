package pl.jointrip.services.documentsService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jointrip.dao.DocumentsRepository;
import pl.jointrip.models.entities.documents.Documentstore;
import pl.jointrip.models.entities.user.User;
import pl.jointrip.models.viewModels.documents.DocumentsApprovalViewModel;
import pl.jointrip.services.documentsService.DocumentsService;

import java.io.IOException;
import java.util.Date;

@Service
public class DocumentsServiceImpl implements DocumentsService {
    @Autowired
    DocumentsRepository documentsRepository;

    public boolean saveDocument(DocumentsApprovalViewModel viewModel) {
        Documentstore documentstore = documentStoreMapper(viewModel);
        try {
            documentsRepository.save(documentstore);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Documentstore documentStoreMapper(DocumentsApprovalViewModel viewModel) {
        Documentstore docStore = new Documentstore();
        try {
            docStore.setFile(viewModel.getFile().getBytes());
            docStore.setUserId(viewModel.getLoggedUser());
            docStore.setFilename(viewModel.getFile().getOriginalFilename());
            docStore.setCreatedate(new Date());
            docStore.setModifydate(new Date());
            docStore.setContentType(viewModel.getFile().getContentType());
            docStore.setFilestatus(0);
            docStore.setDocumentKind(viewModel.getDocumentKind());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return docStore;
    }
    public DocumentsApprovalViewModel findUserDocuments(User user){
        DocumentsApprovalViewModel viewModel = new DocumentsApprovalViewModel();
        viewModel.setDocumentstoreList(documentsRepository.findAllByUserId(user));
        return viewModel;
    }
}
