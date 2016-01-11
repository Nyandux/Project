package se.mah.ae2942.project;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.DecimalFormat;
import cz.msebera.android.httpclient.Header;

/**
 * Converts SEK to USD, GBP and EUR.
 */
public class CurrencyConverterFragment extends Fragment {

    private View view;
    private Button btnGet;
    private TextView tvGBP, tvUSD, tvEUR;
    private EditText etSEK;
    private static final String PAGEAPI = "http://api.fixer.io/latest?base=SEK";

    /**
     * Constructor
     */
    public CurrencyConverterFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_currency_converter, container, false);
        btnGet = (Button) view.findViewById(R.id.fragment_cc_button_get);
        tvEUR = (TextView) view.findViewById(R.id.fragment_cc_textview_eur);
        tvGBP = (TextView) view.findViewById(R.id.fragment_cc_textview_gbp);
        tvUSD = (TextView) view.findViewById(R.id.fragment_cc_textview_usd);
        etSEK = (EditText) view.findViewById(R.id.fragment_cc_edittext_sek);

        btnGet.setOnClickListener(new ButtonGetOnClick());

        return view;
    }

    /**
     * ButtonListener, fetches latest currency rates from api.fixer.io
     */
    private class ButtonGetOnClick implements View.OnClickListener {

        public void onClick(View v) {

            AsyncHttpClient client = new AsyncHttpClient();
            client.get(PAGEAPI, new JsonHttpResponseHandler() {
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                    JSONObject ratesObject = null;

                    try {
                        ratesObject = response.getJSONObject("rates");

                        double gbpRate = ratesObject.getDouble("GBP");
                        double eurRate = ratesObject.getDouble("EUR");
                        double usdRate = ratesObject.getDouble("USD");

                        Log.i("CHACHING", "GBP: " + gbpRate);
                        Log.i("CHACHING", "EUR: " + eurRate);
                        Log.i("CHACHING", "USD: " + usdRate);

                        double seks = Double.valueOf(etSEK.getText().toString());

                        double gbps = seks * gbpRate;
                        double euros = seks * eurRate;
                        double usds = seks * usdRate;

                        DecimalFormat df = new DecimalFormat("#.##");

                        tvEUR.setText("EUR: " + String.valueOf(df.format(euros)));
                        tvUSD.setText("USD: " + String.valueOf(df.format(usds)));
                        tvGBP.setText("GBP: " + String.valueOf(df.format(gbps)));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}