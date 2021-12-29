package pm.travelcommunity.domain.scene;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pm.travelcommunity.domain.BaseEntity;
import pm.travelcommunity.domain.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author YHT
 **/
@Entity
public class ContributionComment extends BaseEntity {
    @ManyToOne
    @JsonIgnore
    private User author;

    private String authorName;

    @ManyToOne
    @JsonIgnore
    private Contribution contribution;

    private String content;

    private int score;

    public ContributionComment() {
    }

    public ContributionComment(User author, Contribution contribution, String content, int score) {
        this.author = author;
        this.authorName = author.getUsername();
        this.contribution = contribution;
        this.content = content;
        this.score = score;
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

    public Contribution getContribution() {
        return contribution;
    }

    public void setContribution(Contribution contribution) {
        this.contribution = contribution;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

