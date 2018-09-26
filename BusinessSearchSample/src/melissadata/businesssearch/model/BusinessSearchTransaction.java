package melissadata.businesssearch.model;

import org.apache.sling.commons.json.JSONObject;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;

public class BusinessSearchTransaction {

    private final String endpoint;
    private BusinessSearchOptions options;
    private String columns;
    private String identNumber;
    private String company, phoneNumber, webAddress, stockTicker, addressLine1, addressLine2, locality, postal, administrativeArea, country, locationType, format;
    private boolean selectAllColumns, columnLocationType, columnPhone, columnEIN, columnMoveDate, columnStockTicker, columnWebAddress, columnPreviousAddress;

    public BusinessSearchTransaction () {
        endpoint = "https://globalbusinesssearch.melissadata.net/web/dobusinesssearch?";
        options = new BusinessSearchOptions();
        columns = "";
        identNumber = "";
        company = "";
        phoneNumber = "";
        webAddress = "";
        stockTicker = "";
        addressLine1 = "";
        addressLine2 = "";
        locality = "";
        postal = "";
        administrativeArea = "";
        country = "";
        format = "";
        locationType = "";
    }
    public String processTransaction(String request) {
        String response = "";
        URI uri;
        URL url;
        try {
            uri = new URI(request);
            url = new URL(uri.toURL().toString());

            HttpURLConnection urlConn = (HttpURLConnection)(url.openConnection());

            BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

            StringReader reader;
            StringWriter writer = new StringWriter();
            StringBuilder jsonResponse = new StringBuilder();
            String inputLine = "";

            if (format.equals("XML"))
            {
                String xmlLine = "";
                String xmlString = "";

                while((xmlLine = in.readLine()) != null) {
                    xmlString += xmlLine + "\n";
                }

                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer t = tf.newTransformer();
                t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "10");
                t.setOutputProperty(OutputKeys.INDENT, "yes");

                reader = new StringReader(xmlString);
                try {
                    t.transform(new javax.xml.transform.stream.StreamSource(reader), new javax.xml.transform.stream.StreamResult(writer));
                } catch (TransformerException e) {
                    e.printStackTrace();
                }
                response = writer.toString();

            } else {
                while ((inputLine = in.readLine()) != null) {
                    jsonResponse.append(inputLine);
                }
                @SuppressWarnings("deprecation")
                JSONObject test = new JSONObject(jsonResponse.toString());
                response = test.toString(10);

            }
        } catch (Exception e){
            System.out.println("Error sending request: \n" + e);
        }
        return response;
    }

    public String generateRequestString() {
        String request = "";
        request = endpoint;
        request += "&id=" + getIdentNumber();
        request += "&opt=" + options.generateOptionString();
        request += "&cols=" + getColumns();
        try {
            if(!getCompany().equals(""))
                request += "&comp=" + URLEncoder.encode(getCompany(), "UTF-8");

            if(!getPhoneNumber().equals(""))
                request += "&phone=" + URLEncoder.encode(getPhoneNumber(), "UTF-8");

            if(!getWebAddress().equals(""))
                request += "&web=" + URLEncoder.encode(getWebAddress(), "UTF-8");

            if(!getStockTicker().equals(""))
                request += "&stock=" + URLEncoder.encode(getStockTicker(), "UTF-8");

            if(!getAddressLine1().equals(""))
                request += "&a1=" + URLEncoder.encode(getAddressLine1(), "UTF-8");

            if(!getAddressLine2().equals(""))
                request += "&a2=" + URLEncoder.encode(getAddressLine2(), "UTF-8");

            if(!getLocality().equals(""))
                request += "&loc=" + URLEncoder.encode(getLocality(), "UTF-8");

            if(!getAdministrativeArea().equals(""))
                request += "&area=" + URLEncoder.encode(getAdministrativeArea(), "UTF-8");

            if(!getPostal().equals(""))
                request += "&postal=" + URLEncoder.encode(getPostal(), "UTF-8");

            if(!getCountry().equals(""))
                request += "&ctry=" + URLEncoder.encode(getCountry(), "UTF-8");

            if(!getLocationType().equals(""))
                request += "&LocationType=" + URLEncoder.encode(getLocality(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("Unsupported Encoding Exception: " +e);
        }
        request += "&format=" + getFormat();

        return request;
    }

    public BusinessSearchOptions getOptions() {
        return options;
    }

    public void setOptions(BusinessSearchOptions options) {
        this.options = options;
    }

    private String getColumns() {
        String columnString = "";
        if(isSelectAllColumns()){
            columnString += "grpAll";
        } else {
            if (isColumnLocationType())
                columnString += "LocationType";

            if (isColumnPhone() && columnString.equals(""))
                columnString += "Phone";
            else if (isColumnPhone() && !columnString.equals(""))
                columnString += ",Phone";

            if (isColumnEIN() && columnString.equals(""))
                columnString += "EIN";
            else if (isColumnEIN() && !columnString.equals(""))
                columnString += ",EIN";

            if (isColumnMoveDate() && columnString.equals(""))
                columnString += "MoveDate";
            else if (isColumnMoveDate() && !columnString.equals(""))
                columnString += ",MoveDate";

            if (isColumnStockTicker() && columnString.equals(""))
                columnString += "StockTicker";
            else if (isColumnStockTicker() && !columnString.equals(""))
                columnString += ",StockTicker";

            if (isColumnWebAddress() && columnString.equals(""))
                columnString += "WebAddress";
            else if (isColumnWebAddress() && !columnString.equals(""))
                columnString += ",WebAddress";

            if (isColumnPreviousAddress() && columnString.equals(""))
                columnString += "PreviousAddress";
            else if (isColumnPreviousAddress() && !columnString.equals(""))
                columnString += ",PreviousAddress";
        }
        return columnString;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    private String getIdentNumber() {
        return identNumber;
    }

    public void setIdentNumber(String identNumber) {
        this.identNumber = identNumber;
    }

    private String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    private String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    private String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    private String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    private String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    private String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    private String getAdministrativeArea() {
        return administrativeArea;
    }

    public void setAdministrativeArea(String administrativeArea) {
        this.administrativeArea = administrativeArea;
    }

    private String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    private String getStockTicker() {
        return stockTicker;
    }

    public void setStockTicker(String stockTicker) {
        this.stockTicker = stockTicker;
    }

    private String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public boolean isSelectAllColumns() {
        return selectAllColumns;
    }

    public void setSelectAllColumns(boolean selectAllColumns) {
        this.selectAllColumns = selectAllColumns;
    }

    private boolean isColumnLocationType() {
        return columnLocationType;
    }

    public void setColumnLocationType(boolean columnLocationType) {
        this.columnLocationType = columnLocationType;
    }

    private boolean isColumnPhone() {
        return columnPhone;
    }

    public void setColumnPhone(boolean columnPhone) {
        this.columnPhone = columnPhone;
    }

    private boolean isColumnEIN() {
        return columnEIN;
    }

    public void setColumnEIN(boolean columnEIN) {
        this.columnEIN = columnEIN;
    }

    private boolean isColumnMoveDate() {
        return columnMoveDate;
    }

    public void setColumnMoveDate(boolean columnMoveDate) {
        this.columnMoveDate = columnMoveDate;
    }

    private boolean isColumnStockTicker() {
        return columnStockTicker;
    }

    public void setColumnStockTicker(boolean columnStockTicker) {
        this.columnStockTicker = columnStockTicker;
    }

    private boolean isColumnWebAddress() {
        return columnWebAddress;
    }

    public void setColumnWebAddress(boolean columnWebAddress) {
        this.columnWebAddress = columnWebAddress;
    }

    private boolean isColumnPreviousAddress() {
        return columnPreviousAddress;
    }

    public void setColumnPreviousAddress(boolean columnPreviousAddress) {
        this.columnPreviousAddress = columnPreviousAddress;
    }

    private String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }
}
