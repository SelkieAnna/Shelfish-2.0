package com.falafelteam.shelfish.controller;

import com.falafelteam.shelfish.model.AuthorKinds.Author;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for the form used in document addition and modification
 */
@Data
class DocumentForm {

    @NotEmpty
    private String name;
    private String description;
    private Boolean isBestseller;
    @Positive
    private int copies;
    @Positive
    private int price;
    private Boolean isReference;
    private String type;
    private String tags;
    private String authors;
    private String editor;
    private String publisher;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date date;

    /**
     * method that gets the authors stored in the form as a list of Author class objects
     *
     * @return list of Author class objects
     */
    public LinkedList<Author> getParsedAuthors() {
        LinkedList<Author> authors = new LinkedList<>();
        String[] authorsString = this.authors.split(", ");
        for (String author : authorsString) {
            authors.add(new Author(author));
        }
        return authors;
    }

    /**
     * method that validates the information stored in the form
     *
     * @throws Exception "Tags should be lower-case letters, in the format "tag1, tag2, tag3, tag4" "
     *                   "Authors should be separated by a comma and a space"
     *                   "There can be only ONE editor"
     *                   "There can be only ONE publisher"
     *                   "Too many bukaf"
     */
    public void validate() throws Exception {
        Pattern tagPattern = Pattern.compile("[a-z]+(, s*[a-z]+)*");
        Matcher tagMatcher = tagPattern.matcher(tags);
        if (!tagMatcher.matches()) {
            throw new Exception("Tags should be lower-case letters, in the format \"tag1, tag2, tag3, tag4\" ");
        }

        Pattern authorPattern = Pattern.compile("[A-Za-z ]+(, s*[A-Za-z ]+)*");
        Matcher authorMatcher = authorPattern.matcher(authors);
        if (!authorMatcher.matches()) {
            throw new Exception("Authors should be separated by a comma and a space");
        }

        Pattern editorPublisherPattern = Pattern.compile("[A-Za-z ]*");
        Matcher editorPublisherMatcher = editorPublisherPattern.matcher(editor);
        if (!editorPublisherMatcher.matches()) {
            throw new Exception("There can be only ONE editor");
        }

        editorPublisherMatcher = editorPublisherPattern.matcher(publisher);
        if (!editorPublisherMatcher.matches()) {
            throw new Exception("There can be only ONE publisher");
        }

        Pattern descriptionPattern = Pattern.compile("(.*?){0,2000}", Pattern.DOTALL);
        Matcher descriptionMatcher = descriptionPattern.matcher(description);
        if (!descriptionMatcher.matches()) {
            throw new Exception("Too many bukaf");
        }
    }
}
