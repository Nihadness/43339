package com.sqa2016.api;

import com.sqa2014.persistence.dao.impl.ProductDaoImpl;
import com.sqa2014.persistence.dao.impl.UserDaoImpl;
import com.sqa2014.persistence.entities.User;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 *
 * @author Nihadness
 */
public class ApiHandler {

	public static ApiHandler instance = null;

	public ApiHandler() {
		try {
			run();
		} catch (IOException ex) {
			java.util.logging.Logger.getLogger(ApiHandler.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void run() throws IOException {
		HttpServer server = HttpServer.create(new InetSocketAddress(9292), 0);
		server.createContext("/nm", new MyHandler());
		server.setExecutor(null);
		server.start();
	}

	public static ApiHandler getInstance() {
		if (instance == null) {
			instance = new ApiHandler();
		}
		return instance;
	}

	public static class MyHandler implements HttpHandler {

		public void handle(com.sun.net.httpserver.HttpExchange he) {
			try {
				System.out.println("**********Received************");
				Map<String, String> params = splitQuery(he.getRequestURI().getQuery());
				String id = params.get("uId"), pass = params.get("p"),add=params.get("add");
				if (id != null && pass != null) {
					UserDaoImpl users = new UserDaoImpl();
					if (users.validateUser(id, pass)) {
						sendResponse(he, "OK");
					} else {
						sendResponse(he, "FAILED");
					}
				}else if(add!=null){
					UserDaoImpl users=new UserDaoImpl();
					String name=params.get("name"),surname=params.get("surname");
					User user=new User();
					user.setName(name);
					user.setSurname(surname);
					users.create(user);
					sendResponse(he, users.getLastId().toString());
				}else {
					String color = params.get("colId"), cat = params.get("catId"), br = params.get("brId");
					if (color != null || br != null || cat != null) {
						ProductDaoImpl products=new ProductDaoImpl();
						sendResponse(he, products.getProducts(color, cat, br));
					} else {
						sendResponse(he, "FAILED");
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e);
				sendResponse(he, "FAILED");
			}
		}
		
		public String testHandle(String query) {
			try {
				System.out.println("**********Received************");
				Map<String, String> params = splitQuery(query);
				String id = params.get("uId"), pass = params.get("p");
				if (id != null && pass != null) {
					UserDaoImpl users = new UserDaoImpl();
					if (users.validateUser(id, pass)) {
						return "OK";
					} else {
						return "FAILED";
					}
				} else {
					String color = params.get("colId"), cat = params.get("catId"), br = params.get("brId");
					if (color != null || br != null || cat != null) {
						ProductDaoImpl products=new ProductDaoImpl();
						return products.getProducts(color, cat, br);
					} else {
						return "FAILED";
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e);
				return "FAILED";
			}
		}
	}

	public static Map<String, String> splitQuery(String query) throws UnsupportedEncodingException {
		Map<String, String> query_pairs = new LinkedHashMap<String, String>();
		String[] pairs = query.split("&");
		for (String pair : pairs) {
			int idx = pair.indexOf("=");
			query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"),
					URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
		}
		return query_pairs;
	}

	private static void sendResponse(com.sun.net.httpserver.HttpExchange he, String responseMessage) {
		String response = "";
		if (responseMessage != null) {
			response = responseMessage;
		}
		try {
			Headers test=he.getResponseHeaders();
			test.add("Access-Control-Allow-Origin", "*");
			he.sendResponseHeaders(200, response.length());
		} catch (IOException e) {
			e.printStackTrace();
		}
		OutputStream os = he.getResponseBody();
		try {
			os.write(response.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}