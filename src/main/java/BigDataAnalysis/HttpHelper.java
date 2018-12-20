package BigDataAnalysis;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpHelper {
//	String URL = "http://127.0.0.1:8080/sparktask/SparkTask.jsp";

	public static void sendHttp(String _URL) {
		try {
			CloseableHttpClient client = null;
			CloseableHttpResponse response = null;
			try {
				HttpGet httpGet = new HttpGet(_URL);

				client = HttpClients.createDefault();
				response = client.execute(httpGet);
				HttpEntity entity = response.getEntity();
				String result = EntityUtils.toString(entity);
				System.out.println(result);
			} finally {
				if (response != null) {
					response.close();
				}
				if (client != null) {
					client.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
