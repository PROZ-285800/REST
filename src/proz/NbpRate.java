package proz;


import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ExchangeRatesSeries")
public class NbpRate {
	
	@XmlElement(name= "Table")
	private String Table;
	
	@XmlElement(name = "Currency")
	private String Currency;
	
	@XmlElement(name = "Code")
	private String Code;
	
	@XmlElementWrapper(name = "Rates")
	@XmlElement (name = "Rate")
	private List<Rate> rates;
	
	public static class Rate {
		@XmlElement(name ="No")
		private String No;
		
		@XmlElement(name = "EffectiveDate")
		private String EffectiveDate;
		
		@XmlElement(name = "Mid")
		private String Mid;
		
		@XmlElement(name = "Ask")
		private String Ask;
		
		@XmlElement(name = "Bid")
		private String Bid;
	}
	
	public String calculateAvarage () {
		double sum = 0;
		if (Table.equals("C")) {
			for( Rate rate : rates) {
				sum += new Double(rate.Ask).doubleValue();
			}
		} else if (Table.equals("A")) {
			for( Rate rate : rates) {
				sum += new Double(rate.Mid).doubleValue();
			} 
		}
		
		double ret = sum / rates.size();
		String str = new Double(ret).toString();
		return str.substring(0, str.indexOf('.') + 3);
		
	}
}
