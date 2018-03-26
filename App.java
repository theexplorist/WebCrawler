package WEBCRAWLER;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebCrawler crawler = new WebCrawler();
		
		String rootUrl = "https://www.tesla.com/";
        crawler.discoverWeb(rootUrl);
	}

}
