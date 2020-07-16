package models;

import javafx.scene.Scene;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "solutions")
public class Solution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin creator;
    private String name;
    private String comment;
    @Column(name = "created_at")
    private Date createdAt;
    private String password;
    @OneToMany(mappedBy = "solution")
    private List<Issue> issues = new ArrayList<>();
    @OneToMany(mappedBy = "solution")
    private List<SolutionState> solutionStates = new ArrayList<>();
    @Column(name = "closed_at")
    private Date closedAt;
    private int rate;

    public Solution(){}

    public Solution(Admin creator, String name, String comment, String password, int rate) {
        this.creator = creator;
        this.name = name;
        this.comment = comment;
        this.password = password;
        this.createdAt = new Date();
        this.rate = rate;
    }

    public Solution(int id, Admin creator, String name, String comment, Date createdAt, String password, List<Issue> issues, Date closedAt, int rate) {
        this.id = id;
        this.creator = creator;
        this.name = name;
        this.comment = comment;
        this.createdAt = createdAt;
        this.password = password;
        this.issues = issues;
        this.closedAt = closedAt;
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public Admin getCreator() {
        return creator;
    }

    public void setCreator(Admin creator) {
        this.creator = creator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public List<SolutionState> getSolutionStates() {
        return solutionStates;
    }

    public void setSolutionStates(List<SolutionState> solutionStates) {
        this.solutionStates = solutionStates;
    }

    public Date getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Date closedAt) {
        this.closedAt = closedAt;
    }

    public void addIssue(Issue issue){
        issues.add(issue);
        issue.setSolution(this);
    }

    public void addSolutionState(SolutionState solutionState){
        solutionStates.add(solutionState);
        solutionState.setSolution(this);
    }
}
