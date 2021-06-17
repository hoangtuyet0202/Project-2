package personal_infor_update;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import com.google.gson.Gson;
import constanst.Constant;

public class Test1 {

	public static void main(String[] args) throws MalformedURLException, ProtocolException, IOException {

		System.out.println("truyen thieu token");
		URL url = new URL(Constant.PERSONAL_INFOR_UPDATE
				+ "?fullname=hoang thi tuyet" + "&email=hoangtuyetkn@gmail.com"
				+ "&avatar=https://drive.google.com/file/d/1-QJWe8BbfGerMx3c7pARp1uPcd7HOr1M/view?usp=sharing"
				+ "&mobile=0975976172");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		try (DataOutputStream writer = new DataOutputStream(connection.getOutputStream())) {

			StringBuilder content;

			try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
				String line;
				content = new StringBuilder();
				while ((line = in.readLine()) != null) {
					content.append(line);
					content.append(System.lineSeparator());
				}
			}
			System.out.println("JSON response: " + content.toString());

			Gson g = new Gson();
			ResponsePersonalInforUpdate rp = g.fromJson(content.toString(), ResponsePersonalInforUpdate.class);

			System.out.println("Unit test 1: The code and message strings shall be not NULL as well as non-empty:");
			assert (rp.code != null && !"".equals(rp.code));
			assert (rp.message != null && !"".equals(rp.message));
			System.out.println("Finished! Satisfied!");
		} finally {
			connection.disconnect();
		}
	}

}