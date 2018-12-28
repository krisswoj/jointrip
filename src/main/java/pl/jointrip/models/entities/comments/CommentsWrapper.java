package pl.jointrip.models.entities.comments;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CommentsWrapper {

    List<Comments> commentsList;

    public CommentsWrapper(List<Comments> commentsList) {
        this.commentsList = commentsList;
    }

}
