package user_update_password;

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

public class Test0 {

	public static void main(String[] args) throws MalformedURLException, ProtocolException, IOException {

		System.out.println("nhap dung");
		URL url = new URL(Constant.USER_UPDATE_PASSWORD
				+ "?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYwNzUyZjg4MjE5OWRkMDAxNTBjNDYzOSIsInVzZXJuYW1lIjoidHV5ZXRodDQwMTIiLCJyb2xlIjoibWFzdGVyIiwiaWF0IjoxNjE4MjkyNjUyLCJleHAiOjE2MTgzMTQyNTJ9.ZCSTQ5Qc8dSGt8d4JD72cUeqDD-bXRbhllWkwwdqhQ8"
				+ "&old_password=123456" + "&new_password=123456789"
				+ "&retype_password=123456789");
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
			ResponseUserUpdatePassword rp = g.fromJson(content.toString(), ResponseUserUpdatePassword.class);

			System.out.println("Unit test 0: The code and message strings shall be not NULL as well as non-empty:");
			assert (rp.code != null && !"".equals(rp.code));
			assert (rp.message != null && !"".equals(rp.message));
			System.out.println("Finished! Satisfied!");
		} finally {
			connection.disconnect();
		}
	}

}
