package modelo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import domain.Event;
import domain.Question;

public class VerBean 
{
	private Event evento;
	private static List<Event> events = new ArrayList<Event>();
	private static List<Question> questions;
	private BLFacade bl;
	
	public VerBean() 
	{ 
		bl = IniBean.getBusinessLogic();
	}
	
	public List<Event> getEvents() {return events;}

	public void setEvents(List<Event> events) {this.events = events;}

	public Event getEvento() {return evento;}

	public void setEvento(Event evento) {this.evento = evento;}

	public List<Question> getQuestions() {return questions;}

	public void setQuestions(List<Question> questions) {this.questions = questions;}

	public void onDateSelect(SelectEvent event) 
	{ 
		Date dateEvents = ((Date)event.getObject());
	    FacesContext.getCurrentInstance().addMessage(null,  
	        new FacesMessage("Fecha escogida: "+dateEvents));
	    this.events = bl.getEvents(dateEvents);
	    //System.out.println(bl.getEvents(dateEvents));
	}
	
	public void onEventSelect(SelectEvent event) 
	{ 
		this.evento=(Event)event.getObject();  
		this.questions = this.evento.getQuestions();
		
		FacesContext.getCurrentInstance().addMessage("crearForm:mensajes",  
		   new FacesMessage("Evento escogido: "+evento.getEventNumber()+" - "+evento.getDescription()));	
	}
}
