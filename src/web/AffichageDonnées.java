package web;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import Fonctions.fonction;


@Controller()
@RequestMapping("/Affichage")
public class AffichageDonnées 
{

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView affichageDate()
	{
		fonction a = new fonction();
		//return new ModelAndView("/date.jsp");
		String date = a.requete();
		return new ModelAndView("/date.jsp", "date", date);
	}
    
    

}