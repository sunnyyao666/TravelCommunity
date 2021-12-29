package pm.travelcommunity.domain.scene;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pm.travelcommunity.domain.BaseEntity;
import pm.travelcommunity.domain.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author YHT
 **/
@Entity
public class Contribution extends BaseEntity {
    @ManyToOne
    @JsonIgnore
    private User author;

    private String authorName;

    @ManyToOne
    @JsonIgnore
    private Scene scene;

    private String title;
    private String content;

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, mappedBy = "contribution")
    private Set<ContributionComment> contributionComments = new HashSet<>();

    public Contribution() {
    }

    public Contribution(User author, Scene scene, String title, String content) {
        this.author = author;
        this.authorName = author.getUsername();
        this.scene = scene;
        this.title = title;
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
        this.authorName = author.getUsername();
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<ContributionComment> getContributionComments() {
        return contributionComments;
    }

    public void setContributionComments(Set<ContributionComment> contributionComments) {
        this.contributionComments = contributionComments;
    }

    public void addContributionComment(ContributionComment contributionComment) {
        this.contributionComments.add(contributionComment);
    }
}
