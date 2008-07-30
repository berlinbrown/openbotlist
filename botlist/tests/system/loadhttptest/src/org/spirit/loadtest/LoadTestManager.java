/*-----------------------------------------------
 * Berlin Brown
 * Created on Apr 13, 2007
 * Released under a BSD style license
 *
 * LoadTestManager.java
 *
 * -- Notes ---------------------------
 * Two classes needed to compile, LoadTestManager and
 * LoadTestManagerThread.
 *
 * -- Todo ----------------------------
 * Finish SSL support and login functionality.
 * Add socket based connect functionality (for finer grain testing)
 *
 * -------------------------- COPYRIGHT_AND_LICENSE --
 * Botlist contains an open source suite of software applications for
 * social bookmarking and collecting online news content for use on the web.
 * Multiple web front-ends exist for Django, Rails, and J2EE.
 * Users and remote agents are allowed to submit interesting articles.
 *
 * Copyright (c) 2007, Botnode.com (Berlin Brown)
 * http://www.opensource.org/licenses/bsd-license.php
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * * Neither the name of the Newspiritcompany.com (Berlin Brown) nor
 * the names of its contributors may be used to endorse or promote
 * products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * -------------------------- END_COPYRIGHT_AND_LICENSE --
 */
package org.spirit.loadtest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Simple HTTP Load Test framework, create the property file with the following
 * key values and launch the application to get status code and response time
 * information. This is not a replacement for Grinder or any of the other
 * frameworks. Two simple classes for quickly evaluating a set of links.
 *
 * Example Property Settings, in the testclient.properties property file:
 *
 * enable.proxy=true -- Enable Proxying proxy.host=theproxy -- Proxy Host
 * proxy.port=9999 -- Proxy Port test.url = http://localhost:8080/ -- Test URL
 * number.requests = 1 -- Number of Requests sleep = 100 -- Sleep time between
 * requests (in ms) thread.count = 1 -- Number of threads to launch debug =
 * false -- Enable debug information use.logfile = true -- Enable a logfile
 * logfile = output/loadtest.log -- Log File to write to use.datafile = true --
 * Enable an input data file (enable set of URLs) datafile = testurldata.dat --
 * Input data file to use (text file with set of URLs)
 */
public class LoadTestManager {

    private static final String PROPERTY_FILE = "testclient.properties";

    public static final int MAX_LOG_RESULT_TUPLE = 5;

    public static final String [] SYSTEM_DIRS = {"data", "data/html", "data/logs", "cookies", "output" };

    public static final int MAX_LINE_MESSAGES = 40;

    public static final int MAX_HEADER_THRES = 60;

    public static final String DEFAULT_USER_AGENT_IE = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; InfoPath.1)";

    private int numberThreads = 1;

    private int threadSleepTime = 200;

    // lines write = number of requests
    private int linesWrite = 10;

    private boolean propertiesLoaded = false;

    private String defaultUserAgent = DEFAULT_USER_AGENT_IE;

    private String testURL = "http://localhost";

    private boolean useSynchronized = false;

    private boolean sslEnabled = false;

    private boolean login = false;

    private String username = "user";

    private String password = "abc";

    private boolean debugEnabled = false;

    private boolean sequencesEnabled = false;

    private String sequenceFile = "data/sequence_requests.dat";

    private boolean useLogFile = false;

    private String logFile = "data/loadtest_local.txt";

    private BufferedWriter testBufferWriter = null;

    private boolean useDataFile = false;

    private String dataFile = "testurldata.dat";

    private int numberOfRequests = 0;

    private long totalTime = 0;

    private boolean enableProxy = false;

    private String proxyHost = "theproxy";

    private String proxyPort = "8080";

    private boolean validateXHTMLEnabled = false;

    private LoadTestHtmlOutput htmlOutput = new LoadTestHtmlOutput().setEnabled(true);

    /**
     * Singleton Object, Load Test Manager.
     */
    private static LoadTestManager client;

    public static final boolean safeStringToBoolean(final String bstr) {
        if ((bstr == null) || (bstr.length() == 0)) {
            return false;
        } else {
            return Boolean.valueOf(bstr).booleanValue();
        } // End of the If
    }
    public static LoadTestManager getTestClient() {
        if (client == null) {
            client = new LoadTestManager();
            // Load the news reader properties
            Properties properties = new Properties();
            try {
                System.out.println("Loading " + PROPERTY_FILE);
                properties.load(new FileInputStream(PROPERTY_FILE));

                String userAgent = properties.getProperty("user.agent") != null ? properties.getProperty("user.agent")
                        .trim() : DEFAULT_USER_AGENT_IE;
                String url = properties.getProperty("test.url") != null ? properties.getProperty("test.url").trim()
                        : "http://localhost";
                int iLines = Integer.parseInt(properties.getProperty("number.requests") != null ? properties
                        .getProperty("number.requests").trim() : "10");
                int iSleep = Integer.parseInt(properties.getProperty("sleep") != null ? properties.getProperty("sleep")
                        .trim() : "200");
                int iThreads = Integer.parseInt(properties.getProperty("thread.count") != null ? properties
                        .getProperty("thread.count").trim() : "1");
                boolean sync = Boolean.valueOf(
                        properties.getProperty("use.synchronized") != null ? properties.getProperty("use.synchronized")
                                .trim() : "false").booleanValue();
                boolean debug = Boolean.valueOf(
                        properties.getProperty("debug") != null ? properties.getProperty("debug").trim() : "false")
                        .booleanValue();

                boolean ssl = Boolean.valueOf(
                        properties.getProperty("ssl.enabled") != null ? properties.getProperty("ssl.enabled").trim()
                                : "" + client.isSslEnabled()).booleanValue();
                // Load the sequences enabled data
                boolean sequences_enabled = Boolean.valueOf(
                        properties.getProperty("sequences.enabled") != null ? properties.getProperty(
                                "sequences.enabled").trim() : "" + client.isSequencesEnabled()).booleanValue();

                boolean log = Boolean.valueOf(
                        properties.getProperty("login") != null ? properties.getProperty("login").trim() : ""
                                + client.isLogin()).booleanValue();
                String username = properties.getProperty("username") != null ? properties.getProperty("username")
                        .trim() : client.getUsername();
                String password = properties.getProperty("password") != null ? properties.getProperty("password")
                        .trim() : client.getPassword();

                boolean use_logfile = Boolean.valueOf(
                        properties.getProperty("use.logfile") != null ? properties.getProperty("use.logfile").trim()
                                : "" + client.isUseLogFile()).booleanValue();
                String logfile = properties.getProperty("logfile") != null ? properties.getProperty("logfile").trim()
                        : client.getLogFile();

                boolean use_datafile = Boolean.valueOf(
                        properties.getProperty("use.datafile") != null ? properties.getProperty("use.datafile").trim()
                                : "" + client.isUseDataFile()).booleanValue();
                String data_file = properties.getProperty("datafile") != null ? properties.getProperty("datafile")
                        .trim() : client.getDataFile();

                String proxy_host = properties.getProperty("proxy.host") != null ? properties.getProperty("proxy.host")
                        .trim() : "localhost";
                String proxy_port = properties.getProperty("proxy.port") != null ? properties.getProperty("proxy.port")
                        .trim() : "8080";

                String enable_proxy = properties.getProperty("enable.proxy") != null ? properties.getProperty(
                        "proxy.port").trim() : "false";
                boolean hasProxy = Boolean.valueOf(enable_proxy).booleanValue();

                String enable_xhtml_validate = properties.getProperty("enable.xhtml.validate") != null
                                ? properties.getProperty("enable.xhtml.validate").trim() : "false";
                //*******************************
                // Set the client properties
                //*******************************
                client.setValidateXHTMLEnabled(Boolean.valueOf(enable_xhtml_validate).booleanValue());
                client.setTestURL(url).setDefaultUserAgent(userAgent).setLinesWrite(iLines);
                client.setThreadSleepTime(iSleep).setUseSynchronized(sync).setNumberThreads(iThreads);

                client.setSslEnabled(ssl).setLogin(log).setUsername(username).setPassword(password);
                client.setDebugEnabled(debug);

                client.setUseLogFile(use_logfile).setLogFile(logfile);

                client.setDataFile(data_file);
                client.setUseDataFile(use_datafile);

                client.setSequencesEnabled(sequences_enabled);
                // If sequence files are enabled, disable the url data file
                if (client.isSequencesEnabled()) {
                    System.out.println("INFO: sequences enabled");
                    //client.setUseDataFile(false);
                }
                client.setProxyHost(proxy_host).setProxyPort(proxy_port).setEnableProxy(hasProxy);

                if (client.isUseLogFile()) {
                    BufferedWriter errorout = new BufferedWriter(new FileWriter(client.getLogFile(), false));
                    client.setTestBufferWriter(errorout);
                }

            } catch (IOException e) {
                client.setPropertiesLoaded(false);
                e.printStackTrace();
                throw new RuntimeException("Invalid Properties File");
            }
        }
        return client;
    }

    public static void prettyPrintTrimData(final String data, final int maxLen) {
        String res = "";
        if (data != null) {
            if (data.length() > maxLen) {
                res = data.substring(0, maxLen) + "...";
            } else {
                res = data;
            }
        } else {
            res = "";
        }
        System.out.println(res);
    }

    public static void log(long diff, String[] responseTuple, String url) {
        String smsg = responseTuple[1].length() < MAX_LINE_MESSAGES ? responseTuple[1] : "";
        String errmsg = responseTuple[2].length() < MAX_LINE_MESSAGES ? responseTuple[2] : "";
        //*******************
        // Create Tab Delimited File (url, no, response-time, status-code,
        // message, errmsg)
        //*******************
        final StringBuffer logLine = new StringBuffer();
        logLine.append("url=").append(url).append("\tno=");
        logLine.append((getTestClient().getNumberOfRequests()) + "\trtime=" + diff + "\tcode=");
        logLine.append(responseTuple[0]).append("\tmessage=[" + smsg + "]").append("\terrmsg=[" + errmsg + "]");

        String additional_msg = "";
        boolean valid_xhtml = false;
        //*****************************
        // TODO: fix me
        // Add additional response tuple values
        //*****************************
        if (responseTuple.length >= LoadTestManager.MAX_LOG_RESULT_TUPLE) {
            additional_msg = responseTuple[3];
            valid_xhtml = LoadTestManager.safeStringToBoolean(responseTuple[4]);
        }
        //*************************
        // Add the information about this request for later use.
        //*************************
        getTestClient().getHtmlOutput().addRequest(Thread.currentThread().getName(), url, (int) diff, responseTuple[0], additional_msg, valid_xhtml);
        try {
            //*************************
            // Also, add additional message to log output
            //*************************
            getTestClient().writeLogFile(logLine + "\n");
        } catch (IOException e) {
            System.out.println("ERR: error writing to logfile - " + getTestClient().getLogFile());
            e.printStackTrace();
        }
    }

    public static Object[] loadDataFile(final String filename) {
        List lData = new ArrayList();
        String feed = null;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(filename));
            while ((feed = in.readLine()) != null) {
                feed = feed.trim();
                if ((feed != null) && (!feed.startsWith("#")) && (feed.length() > 2)) {
                    lData.add(feed);
                }
            } // End of the While
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    ;
                }
            }
        }
        return lData.toArray();
    }

    public static void printHeaderInfo(URLConnection conn) {
        if (getTestClient().debugEnabled) {
            // Debugging (where 50 equals some arbitrary number of headers)
            for (int i = 0; i < MAX_HEADER_THRES; i++) {
                String headerval = conn.getHeaderField(i);
                if (headerval == null) {
                    break;
                }
                System.out.println("    header# " + conn.getHeaderFieldKey(i) + " = " + conn.getHeaderField(i));
            }
        }
    }

    public static String[] connectURL(final String fullURL, final boolean loadExistingCookies) {
        String[] tuple = new String[3];
        tuple[0] = "";
        tuple[1] = "";
        tuple[2] = "";
        HttpURLConnection conn = null;
        try {
            System.getProperties().put("http.agent", getTestClient().getDefaultUserAgent());
            System.getProperties().put("proxySet", "" + getTestClient().isEnableProxy());
            System.getProperties().put("proxyHost", getTestClient().getProxyHost());
            System.getProperties().put("proxyPort", getTestClient().getProxyPort());

            URL url = new URL(fullURL);
            // Read all the text returned by the server
            conn = (HttpURLConnection) url.openConnection();
            LoadTestCookieManager cookieManager = new LoadTestCookieManager();
            setReadCookieData(cookieManager, conn, url.getHost(), loadExistingCookies);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            printHeaderInfo(conn);
            cookieManager.parseCookieData(conn, url.getHost(), loadExistingCookies);
            cookieManager.writeCookieData();

            String str;
            StringBuffer buf = new StringBuffer(500);
            while ((str = in.readLine()) != null) {
                buf.append(str);
            }

            //**************
            // Write the html document to the cache directory (filesystem), similar to a browser
            //**************
            LoadTestWriteHtmlDoc.writeOutput("data/html/"
                    + LoadTestWriteHtmlDoc.generatedHtmlFilename(fullURL) + ".html", buf.toString());

            in.close();
            tuple[0] = "" + conn.getResponseCode();
            tuple[1] = buf.toString();
            if (getTestClient().isDebugEnabled()) {
                LoadTestManager.prettyPrintTrimData("[" + conn.getResponseCode() + "] Data Returned=" + buf.toString(),
                        MAX_HEADER_THRES);
            }

        } catch (MalformedURLException me) {
            me.printStackTrace();
        } catch (IOException e) {
            System.out.println("ERR: IOException error=" + e.getMessage());
            if (conn != null) {
                try {
                    if (conn != null) {
                        String errContent = readInputStream(new BufferedReader(
                        new InputStreamReader(((HttpURLConnection) conn).getErrorStream())));
                        tuple[2] = errContent;
                    }
                    tuple[0] = "" + conn.getResponseCode();
                    tuple[1] = conn.getResponseMessage();
                } catch (IOException ie) {
                    ;
                }
            }
            e.printStackTrace();
        }
        return tuple;
    }
    // ===============================================================
    // SSL Utilities
    // ===============================================================
    public static URL getSSLURL(final String urlString) throws IOException {
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }
        // make untrusted SSL certificates work
        if (url.getProtocol().equals("https")) {
            try {
                // System.setProperty("java.protocol.handler.pkgs",
                // "com.ibm.net.ssl.internal.www.protocol");
                // TODO: May need a provider here
                // Security.addProvider(new com.ibm.jsse.IBMJSSEProvider());

                javax.net.ssl.SSLSocketFactory sf = (javax.net.ssl.SSLSocketFactory) javax.net.ssl.SSLSocketFactory
                        .getDefault();
                javax.net.ssl.SSLSocket sock = null;
                X509TrustManager tm = new MyX509TrustManager();
                HostnameVerifier hm = new MyHostnameVerifier();
                KeyManager[] km = null;
                TrustManager[] tma = {tm};
                SSLContext sc = SSLContext.getInstance("SSL");
                sc.init(km, tma, new java.security.SecureRandom());
                SSLSocketFactory sf1 = sc.getSocketFactory();

                HttpsURLConnection.setDefaultSSLSocketFactory(sf1);
                HttpsURLConnection.setDefaultHostnameVerifier(hm);
            } catch (KeyManagementException e) {
                IOException e2 = new IOException();
                e2.initCause(e);
                throw e2;
            } catch (NoSuchAlgorithmException e) {
                IOException e2 = new IOException();
                e2.initCause(e);
                throw e2;
            }
        }
        return url;
    }

    public static void setReadCookieData(LoadTestCookieManager cookieManager, HttpURLConnection conn,
            final String hostname, final boolean loadExistingCookies) {
        // If cookies enabled, load them in this request.
        if (loadExistingCookies) {
            cookieManager.readCookieData(hostname);
            String cookieData = cookieManager.getIncomingCookieData(hostname);
            if ((cookieData != null) && (cookieData.length() > 0)) {
                conn.setRequestProperty("Cookie", cookieData);
            }
        }
        // Also set the referer
        String referer = cookieManager.getRefererUrl();
        if ((referer != null) && (referer.length() > 0)) {
            System.out.println("INFO!! Setting Referer for connection=" + referer);
            conn.setRequestProperty("Referer", referer);
        }
    }

    public static String[] connectURLSSL(String fullURL, final boolean loadExistingCookies) {
        HttpURLConnection conn = null;
        String[] tuple = new String[3];
        tuple[0] = "";
        tuple[1] = "";
        tuple[2] = "";
        try {
            System.getProperties().put("http.agent", getTestClient().getDefaultUserAgent());
            URL url = getSSLURL(fullURL);
            conn = (HttpsURLConnection) url.openConnection();

            boolean followRedirects = false;
            HttpURLConnection.setFollowRedirects(followRedirects);
            LoadTestCookieManager cookieManager = new LoadTestCookieManager();
            setReadCookieData(cookieManager, conn, url.getHost(), loadExistingCookies);

            // Updated modification, set the referer URL
            cookieManager.queueRefererUrl(fullURL);
            conn.setDoInput(true);
            printHeaderInfo(conn);
            // Load the cookie manager to write content
            cookieManager.parseCookieData(conn, url.getHost(), loadExistingCookies);
            cookieManager.writeCookieData();

            // Read all the text returned by the server
            InputStream inStream = conn.getInputStream();
            System.out.println("DEBUG: inputstream available=" + inStream.available());
            // TODO: test, is this correct, changed getInputStream from open
            // stream
            BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
            String str;
            StringBuffer buf = new StringBuffer(500);
            while ((str = in.readLine()) != null) {
                buf.append(str);
            }
            //**************
            // Write the html document to the cache directory (filesystem), similar to a browser
            //**************
            LoadTestWriteHtmlDoc.writeOutput("data/html/"
                    + LoadTestWriteHtmlDoc.generatedHtmlFilename(fullURL) + ".html", buf.toString());
            in.close();
            System.out.println("INFO: SSL content:");
            prettyPrintTrimData(buf.toString(), 40);

            // Also handle redirects outside of Java's implementation
            int statusCode = conn.getResponseCode();
            if (!followRedirects) {
                if (statusCode == 302) {
                    String newLocation = conn.getHeaderField("Location");
                    System.out.println("INFO: following redirect to=" + newLocation);
                    tuple = connectURLSSL(newLocation, loadExistingCookies);
                }
            }

        } catch (MalformedURLException me) {
            me.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tuple;
    }

    public static String [] postDataSSL(Map mapData, HttpURLConnection conn, URL url, final String fullURL,
            final boolean followRedirects, final boolean loadExistingCookies) {
        OutputStreamWriter wr = null;
        BufferedReader rd = null;
        String[] tuple = new String[3];
        tuple[0] = "";
        tuple[1] = "";
        tuple[2] = "";
        try {
            // Construct data
            String data = "";
            for (Iterator it = mapData.keySet().iterator(); it.hasNext();) {
                String key = (String) it.next();
                String keyValue = (String) mapData.get(key);
                if (data.length() != 0) {
                    data += "&";
                }
                data += URLEncoder.encode(key, "UTF-8") + "=" + URLEncoder.encode(keyValue, "UTF-8");
            }
            conn.setDoOutput(true);
            conn.setDoInput(true);
            LoadTestCookieManager cookieManager = new LoadTestCookieManager();
            setReadCookieData(cookieManager, conn, url.getHost(), loadExistingCookies);

            wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            // Get the response
            InputStream inStream = conn.getInputStream();
            System.out.println("DEBUG: inputstream available=" + inStream.available());
            rd = new BufferedReader(new InputStreamReader(inStream));
            printHeaderInfo(conn);

            // Load the cookie manager to write content
            cookieManager.parseCookieData(conn, url.getHost(), loadExistingCookies);
            cookieManager.writeCookieData();

            String line;
            StringBuffer buf = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                buf.append(line);
            }

            //**************
            // Write the html document to the cache directory (filesystem), similar to a browser
            //**************
            LoadTestWriteHtmlDoc.writeOutput("data/html/"
                    + LoadTestWriteHtmlDoc.generatedHtmlFilename(fullURL) + ".html", buf.toString());

            wr.flush();
            rd.close();
            wr.close();

            int statusCode = conn.getResponseCode();
            LoadTestManager.prettyPrintTrimData("[" + statusCode + "] Data Returned=" + buf.toString(),
                    MAX_HEADER_THRES);

            // Also handle redirects outside of Java's implementation
            if (!followRedirects) {
                if (statusCode == 302) {
                    String newLocation = conn.getHeaderField("Location");
                    System.out.println("INFO: following redirect to=" + newLocation);
                    connectURLSSL(newLocation, loadExistingCookies);
                }
            }

            tuple[0] = "" + statusCode;
            tuple[1] = buf.toString();

        } catch (Exception e) {
            System.out.println("* ERR: while connecting = connectWithURL()");
            e.printStackTrace();
            try {
                // Clear the error response buffer.
                if (conn != null) {
                    String errContent = readInputStream(new BufferedReader(new InputStreamReader(
                            ((HttpURLConnection) conn).getErrorStream())));
                    System.err.println(errContent);
                    System.err.println("* Done with error response body");
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            if (rd != null) {
                try {
                    rd.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } // End of if

            if (wr != null) {
                try {
                    wr.close();
                } catch (IOException e1) {
                    ;
                }
            } // End of If

        }

        return tuple;
    }

    // == End of SSL Utilities =================================================

    /**
     * Ensure that 'data' and 'data/log' directories are created and/or
     * validated.
     */
    private static void verifySystemDirs() {
        for (int i = 0; i < SYSTEM_DIRS.length; i++) {
            File f = new File(SYSTEM_DIRS[i]);
            System.out.print("  verifying system file=" + f.getName());
            if (f.exists()) {
                System.out.print(" [  exists ]");
            } else {
                System.out.print(" [  not found, creating ] ");
                boolean res = f.mkdirs();
                System.out.print(res);
            }
            System.out.println();
        }
    }

    private static String readInputStream(BufferedReader br) throws IOException {
        String line = null;
        StringBuffer buf = new StringBuffer();
        // read the response body
        while ((line = br.readLine()) != null) {
            buf.append(line);
        }
        // close the errorstream
        br.close();
        return buf.toString();
    }

    public static void init() {
        System.out.println("running...servlet log client");
        getTestClient();
    }

    public static void shutdown() throws Exception {
        System.out.println("-------------------------------");
        System.out.println(" * Total Requests=" + getTestClient().getNumberOfRequests());
        System.out.println(" * Total Time=" + getTestClient().getTotalTime());
        System.out.println("-------------------------------");
        log();
        getTestClient().closeLogFile();
        getTestClient().getHtmlOutput().writeOutput();
    }

    private static void log() {
        // Create Tab Delimited File for response time
        String logLine = "url=" + "TOTAL_REQUESTS" + "\tno=" + getTestClient().getNumberOfRequests() + "\trtime="
                + getTestClient().getTotalTime() + "\tcode=" + -1 + "\tmessage=[]" + "\terrmsg=[]";
        try {
            getTestClient().writeLogFile(logLine + "\r\n");
        } catch (IOException e) {
            System.out.println("ERR: error writing to logfile - " + getTestClient().getLogFile());
            e.printStackTrace();
        }
    }

    // =====================================================
    // Bean Methods
    // =====================================================

    protected void incNumberOfRequests() {
        this.numberOfRequests++;
    }

    protected void incTotalTime(long time) {
        this.totalTime += time;
    }

    protected void writeLogFile(String line) throws IOException {
        if (this.testBufferWriter != null) {
            this.testBufferWriter.write(line);
        }
    }

    protected void closeLogFile() throws IOException {
        if (this.testBufferWriter != null) {
            this.testBufferWriter.flush();
            this.testBufferWriter.close();
        }
    }

    public boolean isValidateXHTMLEnabled() {
        return validateXHTMLEnabled;
    }

    public LoadTestManager setValidateXHTMLEnabled(boolean validateXHTMLEnabled) {
        this.validateXHTMLEnabled = validateXHTMLEnabled;
        return this;
    }

    /**
     * @return
     */
    public int getLinesWrite() {
        return linesWrite;
    }

    /**
     * @return
     */
    public int getNumberThreads() {
        return numberThreads;
    }

    /**
     * @return
     */
    public boolean isPropertiesLoaded() {
        return propertiesLoaded;
    }

    /**
     * @return
     */
    public int getThreadSleepTime() {
        return threadSleepTime;
    }

    /**
     * @param i
     * @return
     */
    public LoadTestManager setLinesWrite(int i) {
        linesWrite = i;
        return this;
    }

    /**
     * @param i
     * @return
     */
    public LoadTestManager setNumberThreads(int i) {
        numberThreads = i;
        return this;
    }

    /**
     * @param b
     */
    public void setPropertiesLoaded(boolean b) {
        propertiesLoaded = b;
    }

    /**
     * @param i
     * @return
     */
    public LoadTestManager setThreadSleepTime(int i) {
        threadSleepTime = i;
        return this;
    }

    /**
     * @return
     */
    public String getDefaultUserAgent() {
        return defaultUserAgent;
    }

    /**
     * @param string
     */
    public LoadTestManager setDefaultUserAgent(String string) {
        defaultUserAgent = string;
        return this;
    }

    /**
     * @return
     */
    public String getTestURL() {
        return testURL;
    }

    /**
     * @param string
     */
    public LoadTestManager setTestURL(String string) {
        testURL = string;
        return this;
    }

    /**
     * @return
     */
    public boolean isUseSynchronized() {
        return useSynchronized;
    }

    /**
     * @param b
     * @return
     */
    public LoadTestManager setUseSynchronized(boolean b) {
        useSynchronized = b;
        return this;
    }

    /**
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param string
     * @return
     */
    public LoadTestManager setUsername(String string) {
        username = string;
        return this;
    }

    /**
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param string
     * @return
     */
    public LoadTestManager setPassword(String string) {
        password = string;
        return this;
    }

    /**
     * @return
     */
    public boolean isSslEnabled() {
        return sslEnabled;
    }

    /**
     * @param b
     */
    public LoadTestManager setSslEnabled(boolean b) {
        sslEnabled = b;
        return this;
    }

    /**
     * @return
     */
    public boolean isLogin() {
        return login;
    }

    /**
     * @param b
     */
    public LoadTestManager setLogin(boolean b) {
        login = b;
        return this;
    }

    /**
     * @return
     */
    public boolean isDebugEnabled() {
        return debugEnabled;
    }

    /**
     * @param b
     */
    public void setDebugEnabled(boolean b) {
        debugEnabled = b;
    }

    /**
     * @return
     */
    public int getNumberOfRequests() {
        return numberOfRequests;
    }

    /**
     * @param i
     */
    public void setNumberOfRequests(int i) {
        numberOfRequests = i;
    }

    /**
     * @return
     */
    public long getTotalTime() {
        return totalTime;
    }

    /**
     * @param l
     */
    public void setTotalTime(long l) {
        totalTime = l;
    }

    /**
     * @return
     */
    public boolean isUseLogFile() {
        return useLogFile;
    }

    /**
     * @param b
     * @return
     */
    public LoadTestManager setUseLogFile(boolean b) {
        useLogFile = b;
        return this;
    }

    /**
     * @return
     */
    public String getLogFile() {
        return logFile;
    }

    /**
     * @param string
     * @return
     */
    public LoadTestManager setLogFile(String string) {
        logFile = string;
        return this;
    }

    public BufferedWriter getTestBufferWriter() {
        return testBufferWriter;
    }

    public void setTestBufferWriter(BufferedWriter writer) {
        testBufferWriter = writer;
    }

    public boolean isEnableProxy() {
        return enableProxy;
    }

    public LoadTestManager setEnableProxy(boolean b) {
        enableProxy = b;
        return this;
    }

    public LoadTestManager setProxyHost(String string) {
        proxyHost = string;
        return this;
    }

    public LoadTestManager setProxyPort(String string) {
        proxyPort = string;
        return this;
    }

    public boolean isSequencesEnabled() {
        return sequencesEnabled;
    }

    public LoadTestManager setSequencesEnabled(boolean sequencesEnabled) {
        this.sequencesEnabled = sequencesEnabled;
        return this;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public String getProxyPort() {
        return proxyPort;
    }

    public String getDataFile() {
        return dataFile;
    }

    public boolean isUseDataFile() {
        return useDataFile;
    }

    public void setDataFile(String string) {
        dataFile = string;
    }

    public String getSequenceFile() {
        return sequenceFile;
    }

    public void setSequenceFile(String sequenceFile) {
        this.sequenceFile = sequenceFile;
    }

    public void setUseDataFile(boolean b) {
        useDataFile = b;
    }

    public static Object[] loadTextFile(final String filename) {
        List lData = new ArrayList();
        String feed = null;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(filename));
            while ((feed = in.readLine()) != null) {
                feed = feed.trim();
                if (feed != null) {
                    lData.add(feed);
                }
            } // End of the While
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    ;
                }
            }
        }
        return lData.toArray();
    }

    public LoadTestHtmlOutput getHtmlOutput() {
        return htmlOutput;
    }

    // =====================================================
    // Static Classes for SSL
    // =====================================================
    static class MyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String urlHostname, SSLSession session) {
            return true;
        }
    }

    static class MyX509TrustManager implements X509TrustManager {
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    // =====================================================
    // Main - Entry Point
    // =====================================================
    private static void launchThreads() throws InterruptedException {
        final int noThreads = getTestClient().getNumberThreads();
        Thread[] threadSet = new Thread[noThreads];
        for (int i = 0; i < getTestClient().getNumberThreads(); i++) {
            LoadTestManagerThread c = new LoadTestManagerThread(getTestClient());
            threadSet[i] = new Thread(c);
            threadSet[i].start();
            Thread.sleep(getTestClient().getThreadSleepTime());
        }
        // Join all of threads just created.
        for (int i = 0; i < noThreads; ++i) {
            try {
                threadSet[i].join();
            } catch (InterruptedException e) {
                System.out.println("ERR: Join interrupted");
            }
        }
    }

    private static void launchFromSequenceData() {
        LoadTestSequenceParser parser = new LoadTestSequenceParser();
        parser.parse(getTestClient().getSequenceFile());
        parser.printSummary();
        parser.handleSequence();
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Usage: LoadTestManager -f <test properties filename>");
            System.exit(-1);
            return;
        }
        PROPERTY_FILE = args[1];
        verifySystemDirs();
        init();
        if (getTestClient().isUseDataFile()) {
            // Check for use data file first, with sequences taking lower priority
            launchThreads();
        } else if (getTestClient().isSequencesEnabled()) {
            launchFromSequenceData();
        } else {
            launchThreads();
        }
        shutdown();
    }
}
// End of File
