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
        Documentstore documentstore = this.documentStoreMapper(viewModel);

        try {
            documentsRepository.save(documentstore);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Documentstore documentStoreMapper(DocumentsApprovalViewModel viewModel) {
        Documentstore docStore = new Documentstore();
        try {
            docStore.setFile(viewModel.getFile().getBytes());
            docStore.setUserId(viewModel.getLoggedUser());
            docStore.setTripId(viewModel.getTrip());
            docStore.setFilename(viewModel.getFile().getOriginalFilename());
            docStore.setDocumenttitle(viewModel.getUserFileName());
            docStore.setCreatedate(new Date());
            docStore.setModifydate(new Date());
            docStore.setContentType(viewModel.getFile().getContentType());
            docStore.setFilestatus(viewModel.getStatus());
            docStore.setDocumentKind(viewModel.getDocumentKind());
            docStore.setDocumenttitle(viewModel.getUserFileName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return docStore;
    }


    public DocumentsApprovalViewModel findUserDocuments(User user) {
        DocumentsApprovalViewModel viewModel = new DocumentsApprovalViewModel();
        viewModel.setDocumentstoreList(documentsRepository.findAllByUserId(user));
        return viewModel;
    }

    public Documentstore findById(int id) {
        return documentsRepository.findById(id);
    }
}