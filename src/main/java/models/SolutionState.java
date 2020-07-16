package models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "solution_states")
public class SolutionState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "solution_id")
    private Solution solution;
    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;
    private String description;
    private Date date;

    public SolutionState(Solution solution, State state, String description) {
        this.solution = solution;
        this.state = state;
        this.description = description;
        this.date = new Date();
    }

    public SolutionState(int id, Solution solution, State state, String description, Date date) {
        this.id = id;
        this.solution = solution;
        this.state = state;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
