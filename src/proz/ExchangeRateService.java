package proz;

import java.io.StringReader;
import java.net.URI;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;


@ApplicationScoped
public class ExchangeRateService {
	public String getNBPrate(String table, String code, String topCount) {
		Client client = ClientBuilder.newClient();
		URI uri = UriBuilder
				.fromUri("http://api.nbp.pl/api/exchangerates/rates/" + table + "/" + code + "/last/" + topCount)
				.build();
		
		WebTarget target = client.target(uri);
		String xmlResponse = target.request().accept(MediaType.TEXT_XML).get(String.class);
		
		NbpRate nbpRates = null;

		try {
			JAXBContext context = JAXBContext.newInstance(NbpRate.class);
			nbpRates = (NbpRate) context.createUnmarshaller().unmarshal(new StringReader(xmlResponse));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		if (nbpRates == null ) {
			return null;
		} else {
			return nbpRates.calculateAvarage();
		}

	}
}
