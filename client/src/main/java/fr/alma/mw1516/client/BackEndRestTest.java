package fr.alma.mw1516.client;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by julien on 04/12/15.
 */
public class BackEndRestTest {

    public static void main(String[] args) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // Définition de l'URL d'appel
        HttpGet request = new HttpGet("http://localhost:8080/ame-services-rest/service/backend/bonjour");

        request.setHeader("accept", "application/json");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("name", "Whatever"));
        UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(params, "UTF-8");
        //request.setEntity(encodedFormEntity);

        // Execution de la requête
        CloseableHttpResponse response = httpClient.execute(request);

        // Affichage des données de la réponse (statusCode + headers)
        System.out.println(response);

        // Récupération d'une valeur dans les headers
        Header header = response.getFirstHeader("Content-Type");
        System.out.println(header.getValue());

        // Affichage du contenu de la réponse
        System.out.println(getContextAsString(response));
    }

    protected static String getContextAsString(HttpResponse response) throws IOException {

        StringWriter writer = new StringWriter();
        InputStream inputStream = response.getEntity().getContent();
        try {
            IOUtils.copy(inputStream, writer, "UTF-8");
        } finally {
            inputStream.close();
        }
        return writer.toString();
    }
}
