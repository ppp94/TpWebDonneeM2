package Partie2Requetes;

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


public class JenaRDF {
	
	final String inputFileName;
	private Model model;
	private InputStream in;
	
	public JenaRDF(String fileName) {
		inputFileName = fileName;
		model = ModelFactory.createDefaultModel();
		in = FileManager.get().open(inputFileName);
		if (in == null) {
		    throw new IllegalArgumentException(
		                                 "File: " + inputFileName + " not found");
		}
		readRDF();
	}
	
	public void queryRDF(String queryString) {
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		ResultSet results = qexec.execSelect();
		ResultSetFormatter.out(System.out, results, query);		
	}
	
	public void readRDF() {
	model.read(in, null, "Turtle");
	}
	
	public static void fichierParking() {
		/*
		 * Fichier Parking
		 */
		
		JenaRDF jenaRDF = new JenaRDF("aarhus_parking.ttl");
		
		// Requete 1: objet
		jenaRDF.queryRDF(
				"PREFIX  sao: <http://iot.ee.surrey.ac.uk/citypulse/resources/ontologies/sao.ttl> "
				+ " SELECT ?objet"
				+ " WHERE {?objet a sao:streamEvent}");
	
		//Requete 2: temps de début et temps de fin
		jenaRDF.queryRDF(
				"PREFIX tl: <http://purl.org/NET/c4dm/timeline.owl#> "
				+ "SELECT ?debut ?fin "
				+ "WHERE {?objet tl:start ?debut. ?objet tl:end ?fin}");
		
		//Requete 3: unité de mesure
		jenaRDF.queryRDF(
				"PREFIX  sao: <http://iot.ee.surrey.ac.uk/citypulse/resources/ontologies/sao.ttl> "
				+ " SELECT DISTINCT ?uniteMesure"
				+ " WHERE {?objet sao:hasUnitOfMeasurement ?uniteMesure}");
		
		//Requete 4: Temps d'observation
		jenaRDF.queryRDF(
				"PREFIX  tl:<http://purl.org/NET/c4dm/timeline.owl#> "
				+ " SELECT ?tempsobservation"
				+ " WHERE {?objet tl:at ?tempsobservation}");
		
		//Requete 5: Latitude et longitude
		jenaRDF.queryRDF(
				"PREFIX ct: <http://www.insight-centre.org/citytraffic#> "
				+ "SELECT ?lon ?lat "
				+ "WHERE {?objet ct:hasLongitude ?lon. ?objet ct:hasLatitude ?lat}");
		
		//Requete 6: Nom des noeuds
		
		jenaRDF.queryRDF(
				"PREFIX ct: <http://www.insight-centre.org/citytraffic#> "
						+ "SELECT ?node "
						+ "WHERE { ?objet ct:hasNodeName ?node}");
		
		//Requete 7: Valeurs
		
		jenaRDF.queryRDF(
				"PREFIX sao: <http://iot.ee.surrey.ac.uk/citypulse/resources/ontologies/sao.ttl> "
						+ "SELECT ?valeur "
						+ "WHERE { ?objet sao:value ?valeur}");
		
		
	}
	
	public static void fichierClimat() {
		
		/*
		 * Fichier climat: pressure.ttl
		 */
		
		JenaRDF jenaRDF = new JenaRDF("pressure.ttl");
		
		// Requete 1: objet
		jenaRDF.queryRDF(
				"PREFIX  sao: <http://iot.ee.surrey.ac.uk/citypulse/resources/ontologies/sao.ttl> "
				+ " SELECT ?objet"
				+ " WHERE {?objet a sao:streamEvent}");
	
		//Requete 2: temps de début et temps de fin
		jenaRDF.queryRDF(
				"PREFIX tl: <http://purl.org/NET/c4dm/timeline.owl#> "
				+ "SELECT ?debut ?fin "
				+ "WHERE {?objet tl:start ?debut. ?objet tl:end ?fin}");
		
		//Requete 3: unité de mesure
		jenaRDF.queryRDF(
				"PREFIX  sao: <http://iot.ee.surrey.ac.uk/citypulse/resources/ontologies/sao.ttl> "
				+ " SELECT DISTINCT ?uniteMesure"
				+ " WHERE {?objet sao:hasUnitOfMeasurement ?uniteMesure}");
		
		//Requete 4: Temps d'observation
		jenaRDF.queryRDF(
				"PREFIX  tl:<http://purl.org/NET/c4dm/timeline.owl#> "
				+ " SELECT ?tempsobservation"
				+ " WHERE {?objet tl:at ?tempsobservation}");
		
		//Requete 5: Latitude et longitude
		//Vide
		jenaRDF.queryRDF(
				"PREFIX ct: <http://www.insight-centre.org/citytraffic#> "
				+ "SELECT ?lon ?lat "
				+ "WHERE {?objet ct:hasLongitude ?lon. ?objet ct:hasLatitude ?lat}");
		
		//Requete 6: Nom des noeuds
		//Vide		
		jenaRDF.queryRDF(
				"PREFIX ct: <http://www.insight-centre.org/citytraffic#> "
						+ "SELECT ?node "
						+ "WHERE { ?objet ct:hasNodeName ?node}");
		
		//Requete 7: Valeurs
		
		jenaRDF.queryRDF(
				"PREFIX sao: <http://iot.ee.surrey.ac.uk/citypulse/resources/ontologies/sao.ttl> "
						+ "SELECT ?valeur "
						+ "WHERE { ?objet sao:value ?valeur}");
		
	}
	
	public static void main(String args[]) {
		
		JenaRDF.fichierParking();
		JenaRDF.fichierClimat();
		
	}
	
	
	      
}
