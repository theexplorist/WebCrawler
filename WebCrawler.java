package WEBCRAWLER;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler {

	private Queue<String> queue;
	private ArrayList<String> discoveredWebsitesList;

	public WebCrawler() {
		this.queue = new LinkedList<>();
		this.discoveredWebsitesList = new ArrayList<>();
	}

	public void discoverWeb(String root) {
		this.queue.add(root);
		this.discoveredWebsitesList.add(root);

		while (!queue.isEmpty()) {
			String v = this.queue.remove();
			StringBuilder rawHtml = readUrl(v);
			String regexe = "https://(\\w+\\.)*(\\w+)";
			Pattern pattern = Pattern.compile(regexe);
			Matcher matcher = pattern.matcher(rawHtml);
			
			while(matcher.find()){
				String actualUrl = matcher.group();
				if(!this.discoveredWebsitesList.contains(actualUrl)){
					this.discoveredWebsitesList.add(actualUrl);
					System.out.println("website has been found with URL :" + actualUrl);
					this.queue.add(actualUrl);
				}
			}
		}
	}

	public StringBuilder readUrl(String v) {

		StringBuilder rawHtml = new StringBuilder() ;
		URL ur;
		try {
			ur = new URL(v);
			BufferedReader br = new BufferedReader(new InputStreamReader(ur.openStream()));
			String inputLine = "";
			while((inputLine = br.readLine()) != null){
				 rawHtml.append(inputLine);
 			}br.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return rawHtml;

	}
}
