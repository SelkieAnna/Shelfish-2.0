package com.falafelteam.shelfish.model;

import com.falafelteam.shelfish.model.documents.Document;
import com.falafelteam.shelfish.model.users.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Class for the document and user relation model
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
public class DocumentUser {

    private final String statusNEW = "new";
    private final String statusTAKEN = "taken";
    private final String statusRENEWED = "renewed";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Date date;
    private Integer weekNum;
    private String status;

    public DocumentUser(Document document, User user, int weekNum) {
        this.document = document;
        this.user = user;
        this.status = this.statusNEW;
        this.date = new Date();
        this.weekNum = weekNum;
    }

    /**
     * method for removing connections to other objects before removal of the object
     */
    @PreRemove
    private void preRemove() {
        this.document.getUsers().remove(this);
        this.document = null;
        this.user = null;
    }
}
