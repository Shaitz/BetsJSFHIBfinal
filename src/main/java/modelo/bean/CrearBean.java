package modelo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

import domain.Event;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import dataAccess.HibernateDataAccess;
import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;

public class CrearBean 
{
	private Date fecha;
	private Event evento;
	private static List<Event> events = new ArrayList<Event>();
	private String question;
	private float minBet;
	private BLFacade bl;
	
	public CrearBean() 
	{ 
		bl = IniBean.getBusinessLogic();
	}
	
	public Date getFecha() {return fecha;}

	public void setFecha(Date fecha) {this.fecha = fecha;}

	public float getMinBet() {return minBet;}

	public void setMinBet(float minBet) {this.minBet = minBet;}
	
	public String getQuestion() {return question;}

	public void setQuestion(String question) {this.question = question;}
	
	public List<Event> getEvents() {return events;}

	public void setEvents(List<Event> events) {this.events = events;}

	public Event getEvento() {return evento;}

	public void setEvento(Event evento) {this.evento = evento;}

	public void onDateSelect(SelectEvent event) 
	{ 
		this.setFecha((Date)event.getObject());
	    FacesContext.getCurrentInstance().addMessage(null,  
	        new FacesMessage("Fecha escogida: "+this.getFecha()));
	    this.events = bl.getEvents(this.getFecha());
	}
	
	public void onEventSelect(SelectEvent event) 
	{ 
		 this.evento=(Event)event.getObject();  
		 FacesContext.getCurrentInstance().addMessage("crearForm:mensajes",  
		   new FacesMessage("Evento escogido: "+evento.getEventNumber()+" - "+evento.getDescription()));
	}
	
	public void crearPregunta() 
	{
		if (this.evento != null)
		{
			if (this.question != "")
			{
				Question q = null;
				try 
				{
					q = bl.createQuestion(this.evento, this.question, this.minBet);
				} catch (EventFinished e) 
				{
					e.printStackTrace();
				} catch (QuestionAlreadyExist e) 
				{
					e.printStackTrace();
				}
				if (q != null)
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La pregunta ha sido añadida correctamente."));
				else
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ha surgido un error creando la pregunta.")); 
			}
			else
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La pregunta está vacía."));
		}
		else
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Por favor elige un evento.")); 
	}

}
