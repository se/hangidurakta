package devsmobile.android.hangidurakta;

import java.io.IOException;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActMain extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_main);

		Button btnDurakBilgisi = (Button) findViewById(R.id.btnDurakBilgisi);
		btnDurakBilgisi.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				try {
					EditText etHatNumarasi = (EditText) findViewById(R.id.etHatNumarasi);
					TextView tvDurakBilgisi = (TextView) findViewById(R.id.tvDurakBilgisi);

					String html;

					html = Jsoup
							.connect(
									"http://3n.iett.gov.tr/?hat="
											+ etHatNumarasi.getText()
													.toString()).get().html();

					html = html.substring(html.indexOf("</form>") + 7,
							html.indexOf("</body>"));

					tvDurakBilgisi.setText(Html.fromHtml(html));

					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(etHatNumarasi.getWindowToken(), 0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.act_main, menu);
		return true;
	}
}
