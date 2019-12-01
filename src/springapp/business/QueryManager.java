package springapp.business;

import java.io.InputStream;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import org.springframework.stereotype.Service;

@Service("queryManager")
public class QueryManager implements IqueryManager
{
	
	
	@Override
	public String queryDate()
	{
		Model model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open( "aarhus_parking.ttl" );
		if (in == null) 
		{
			throw new IllegalArgumentException(
					"File: " + "aarhus_parking.ttl" + " not found");
		}
		model.read(in, null,"Turtle");
		
		
		String queryString= "PREFIX sao: <http://iot.ee.surrey.ac.uk/citypulse/resources/ontologies/sao.ttl>"
				+ " SELECT ?objet "
				+ " WHERE { ?objet  a sao:streamEvent}";
		Query query= QueryFactory.create(queryString) ;
		QueryExecution qexec= QueryExecutionFactory.create(query, model) ;
		ResultSet results= qexec.execSelect() ;
		//results.toString();
		ResultSetFormatter.out(System.out, results, query) ;
		return results.toString();
	}
	
}
