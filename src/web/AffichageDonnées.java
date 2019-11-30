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
import springapp.business.QueryManager;


@Controller()
@RequestMapping("/Affichage")
public class AffichageDonnées
{
	@Autowired
	QueryManager q;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView affichageDate()
	{
		//return new ModelAndView("/date.jsp");
		
		String date = q.queryDate();
		return new ModelAndView("/date.jsp", "date", date);
	}
    
    

}