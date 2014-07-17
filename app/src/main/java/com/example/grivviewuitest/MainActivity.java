package com.example.grivviewuitest;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.grivviewuitest.adapter.ImageAdapter;
import com.example.grivviewuitest.parser.RssatomParser;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends Activity {
    boolean mIsDualPane;
    public static GridView gridview;
    public static final ArrayList<String> list = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadPage();

        gridview = (GridView) findViewById(R.id.gridview);
        //gridview.setAdapter(new ImageAdapter(this, list));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),ArticleActivity.class);
                intent.putExtra("id",position);
                startActivity(intent);

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:
                openSearch();
                return true;
            case R.id.action_settings:
                openSettings();
                return true;
            case R.id.add_feed:
                openAddFeed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    private void openAddFeed() {
        startActivity(new Intent(MainActivity.this, AddContentActivity.class));
    }

    private void openSettings() {

    }

    private void openSearch() {

    }


    //*******************************************************************************************************************************
    // //////////////////////////////////////  NETWORK ACTIVITY IMPLEMENTATION WITH XML PULL PARSER /////////////////////////////////
    //*******************************************************************************************************************************
    public static final String ANY = "Any";
    private static final String URL = "http://stackoverflow.com/feeds/tag?tagnames=android&sort=newest";


    // Uses AsyncTask to download the XML feed from stackoverflow.com.
    public void loadPage() {

        //  if((sPref.equals(ANY)) && (wifiConnected || mobileConnected)) {
        new DownloadXmlTask().execute(URL);

    }
    // Implementation of AsyncTask used to download XML feed from stackoverflow.com.
    private class DownloadXmlTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                return loadXmlFromNetwork(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return getResources().getString(R.string.connection_error);
            } catch (XmlPullParserException e) {
                return getResources().getString(R.string.xml_error);
            }
        }

        @Override
        protected void onPostExecute(String result) {
            MainActivity.gridview.setAdapter(new ImageAdapter(getApplicationContext(), MainActivity.list));
           // finish();
           // setContentView(R.layout.activity_network);
            // Displays the HTML string in the UI via a WebView
           // WebView myWebView = (WebView) findViewById(R.id.webview);
           // myWebView.setWebViewClient(new WebViewClient());  // Magic
           // myWebView.loadData(result, "text/html", null);
        }
    }

    // Uploads XML from stackoverflow.com, parses it, and combines it with
// HTML markup. Returns HTML string.
    private String loadXmlFromNetwork(String urlString) throws XmlPullParserException, IOException {
        InputStream stream = null;
        // Instantiate the parser
        RssatomParser stackOverflowXmlParser = new RssatomParser();
        List<RssatomParser.Entry> entries = null;
        String title = null;
        String url = null;
        String summary = null;
        Calendar rightNow = Calendar.getInstance();
        DateFormat formatter = new SimpleDateFormat("MMM dd h:mmaa");

        // Checks whether the user set the preference to include summary text
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean pref = sharedPrefs.getBoolean("summaryPref", false);

        StringBuilder htmlString = new StringBuilder();
        htmlString.append("<h3>" + getResources().getString(R.string.page_title) + "</h3>");
        htmlString.append("<em>" + getResources().getString(R.string.updated) + " " +
                formatter.format(rightNow.getTime()) + "</em>");

        try {
            stream = downloadUrl(urlString);
            entries = stackOverflowXmlParser.parse(stream);
            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (stream != null) {
                stream.close();
            }
        }

        // StackOverflowXmlParser returns a List (called "entries") of Entry objects.
        // Each Entry object represents a single post in the XML feed.
        // This section processes the entries list to combine each entry with HTML markup.
        // Each entry is displayed in the UI as a link that optionally includes
        // a text summary.
        for (RssatomParser.Entry entry : entries) {
            htmlString.append("<p><a href='");
            htmlString.append(entry.link);
            htmlString.append("'>" + entry.title + "</a></p>");
            MainActivity.list.add(entry.title);
            // If the user set the preference to include summary text,
            // adds it to the display.
            if (pref) {
                htmlString.append(entry.summary);
            }
        }
        return htmlString.toString();
    }

    // Given a string representation of a URL, sets up a connection and gets
// an input stream.
    private InputStream downloadUrl(String urlString) throws IOException {
        java.net.URL url = new java.net.URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(15000 /* milliseconds */);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        // Starts the query
        conn.connect();
//        Toast.makeText(NetworkActivity.this, "downloadUrl" , Toast.LENGTH_SHORT).show();

        return conn.getInputStream();

    }
}
