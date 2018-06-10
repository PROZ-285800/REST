package proz;


import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("exchangerates/rates/{table}/{code}/{topCount}")	
public class ExchangesRateServlet {
	
	private ExchangeRateService exchangeRateService = new ExchangeRateService();
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getText(@PathParam("table") String table, @PathParam("code") String code,
			@PathParam("topCount") String topCount) {
		   return exchangeRateService.getNBPrate(table, code, topCount);
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getHtml(@PathParam("table") String table, @PathParam("code") String code,
			@PathParam("topCount") String topCount) {
		return  "<html><body><h1>"  + exchangeRateService.getNBPrate(table, code, topCount) + "</h1></body></html>";
	}
	
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public String getXML(@PathParam("table") String table, @PathParam("code") String code,
			@PathParam("topCount") String topCount) {
	return "<?xml version=\"1.0\"?>" + exchangeRateService.getNBPrate(table, code, topCount);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON) 
	public String getJSON(@PathParam("table") String table, @PathParam("code") String code,
			@PathParam("topCount") String topCount) {
			JsonObjectBuilder builder = Json.createObjectBuilder();
			builder.add("Avg", exchangeRateService.getNBPrate(table, code, topCount));
			return (builder.build()).toString();
	}
	
}
