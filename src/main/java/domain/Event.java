package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Vector;

import javax.persistence.*;

import exceptions.QuestionAlreadyExist;
import domain.Question;

@Entity
public class Event{
	
	@Id 
	private Integer eventNumber;
	private String description; 
	@Column(nullable = false)  
	private Date eventDate;
	@OneToMany(targetEntity=Question.class, mappedBy="event", fetch=FetchType.EAGER, cascade= {CascadeType.REMOVE,CascadeType.PERSIST})
	private List<Question> questions = new ArrayList<>();

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Event() {
	}

	public Event(Integer eventNumber, String description,Date eventDate) {
		this.eventNumber = eventNumber;
		this.description = description;
		this.eventDate=eventDate;
	}
	
	public Event( String description,Date eventDate) {
		this.description = description;
		this.eventDate=eventDate;
	}

	public Integer getEventNumber() {
		return eventNumber;
	}

	public void setEventNumber(Integer eventNumber) {
		this.eventNumber = eventNumber;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description=description;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	
	
	public String toString(){
		return eventNumber+";"+description;
	}
	
	/**
	 * This method creates a bet with a question, minimum bet ammount and percentual profit
	 * 
	 * @param question to be added to the event
	 * @param betMinimum of that question
	 * @return Bet
	 */
	public Question addQuestion(String question, float betMinimum)  {
        Question q=new Question(question,betMinimum, this);
        questions.add(q);
        return q;
	}

	
	/**
	 * This method checks if the question already exists for that event
	 * 
	 * @param question that needs to be checked if there exists
	 * @return true if the question exists and false in other case
	 */
	public boolean DoesQuestionExists(String question)  {	
		for (Question q:this.getQuestions()){
			if (q.getQuestion().compareTo(question)==0)
				return true;
		}
		return false;
	}
		

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + eventNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (eventNumber != other.eventNumber)
			return false;
		return true;
	}
	
}
