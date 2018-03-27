package coursework.mobile.mobilecoursework;
// Usman Iqbal - S1425850

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Usman on 23/03/2018.
 */

public class PlannedRoadworksActivity extends AppCompatActivity {

    String url = "https://trafficscotland.org/rss/feeds/plannedroadworks.aspx";
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.planned_roadworks);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        XMLparser xmlparser = new XMLparser(this, recyclerView);
        xmlparser.execute();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Planned Roadworks");
    }

    public class XMLparser extends AsyncTask<Void, Void, Void> {

        Context context;
        String urladdress = "http://www.trafficscotland.org/rss/feeds/plannedroadworks.aspx";
        ProgressDialog progressDialog;
        ArrayList<RssItems> Items;
        RecyclerView recyclerView;
        URL url;

        public XMLparser(Context context, RecyclerView recyclerView) {
            this.recyclerView = recyclerView;
            this.context = context;
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Loading Data");
        }

        @Override
        protected void onPreExecute() {
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            Adapter adapter = new Adapter(context, Items);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(adapter);
        }

        @Override
        protected Void doInBackground(Void... params) {
            parseData(RssReader());
            return null;
        }

        public void parseData(Document data) {
            if (data != null) {
                Items = new ArrayList<>();
                Element root = data.getDocumentElement();
                Node channel = root.getChildNodes().item(1);
                NodeList items = channel.getChildNodes();
                for (int i = 0; i < items.getLength(); i++) {
                    Node currentchild = items.item(i);
                    if (currentchild.getNodeName().equalsIgnoreCase("item")) {
                        RssItems item = new RssItems();
                        NodeList itemchilds = currentchild.getChildNodes();
                        for (int j = 0; j < itemchilds.getLength(); j++) {
                            Node current = itemchilds.item(j);
                            if (current.getNodeName().equalsIgnoreCase("title")) {
                                item.setTitle(current.getTextContent());
                            } else if (current.getNodeName().equalsIgnoreCase("description")) {
                                item.setDescription(current.getTextContent());
                            } else if (current.getNodeName().equalsIgnoreCase("pubDate")) {
                                item.setPubDate(current.getTextContent());
                            } else if (current.getNodeName().equalsIgnoreCase("link")) {
                                item.setLink(current.getTextContent());
                            }
                        }
                        Items.add(item);
                    }

                }
            }
        }

        public Document RssReader() {
            try {
                url = new URL(urladdress);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                InputStream inputStream = connection.getInputStream();
                DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = builderFactory.newDocumentBuilder();
                Document xmlDoc = builder.parse(inputStream);
                return xmlDoc;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

}
