package com.example.assignment;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;

public class AsyncHttpPost extends AsyncTask<Object, String, ArrayList<String>> {
	ArrayList<String> arr1 = new ArrayList<String>();
	async_int op;

	public AsyncHttpPost(async_int op) {
		this.op = op;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected ArrayList<String> doInBackground(Object... params) {
		byte[] result = null;
		String str = "";
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost((String) params[0]);// in this case,// params[0] is URL
		try {
			post.setEntity(new UrlEncodedFormEntity(
					(List<NameValuePair>) params[1]));
			HttpResponse response = client.execute(post);
			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() == HttpURLConnection.HTTP_OK) {
				result = EntityUtils.toByteArray(response.getEntity());
				str = new String(result, "UTF-8");
				arr1.add(str);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
		}
		return arr1;
	}

	@Override
	protected void onPostExecute(ArrayList<String> output) {
		op.onpost(output.get(0));
	}
}
