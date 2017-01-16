import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.sqa2014.persistence.Main;
import com.sqa2014.persistence.dao.BrandDao;
import com.sqa2014.persistence.dao.CategoryDao;
import com.sqa2014.persistence.dao.ColorDao;
import com.sqa2014.persistence.dao.ProductDao;
import com.sqa2014.persistence.dao.UserDao;
import com.sqa2014.persistence.dao.impl.BrandDaoImpl;
import com.sqa2014.persistence.dao.impl.CategoryDaoImpl;
import com.sqa2014.persistence.dao.impl.ColorDaoImpl;
import com.sqa2014.persistence.dao.impl.ProductDaoImpl;
import com.sqa2014.persistence.dao.impl.UserDaoImpl;
import com.sqa2014.persistence.entities.Brand;
import com.sqa2014.persistence.entities.Category;
import com.sqa2014.persistence.entities.Color;
import com.sqa2014.persistence.entities.Product;
import com.sqa2014.persistence.entities.User;
import com.sqa2016.api.ApiHandler;

public class JUnitClass {

	private static final String USER_AGENT = null;
	ProductDao products;
	BrandDao brands;
	ColorDao colors;
	CategoryDao categories;
	UserDao users;

	@Before
	public void setup() {
		products = new ProductDaoImpl();
		brands = new BrandDaoImpl();
		colors = new ColorDaoImpl();
		categories = new CategoryDaoImpl();
		users = new UserDaoImpl();
		ProductDao products = new ProductDaoImpl();
		BrandDao brands = new BrandDaoImpl();
		ColorDao colors = new ColorDaoImpl();
		CategoryDao categories = new CategoryDaoImpl();
		UserDao users = new UserDaoImpl();

		Category cat = new Category();
		cat.setName("T-Shirt");
		categories.create(cat);

		Brand br = new Brand();
		br.setName("N&M");
		brands.create(br);

		Color col = new Color();
		col.setName("turquoise");
		colors.create(col);

		User user = new User();
		user.setName("Nihad");
		user.setSurname("ness");
		user.setPassword("guessme");
		user.setRole("Admin");
		users.create(user);

		Product product = new Product();
		product.setAddedUser(user);
		product.setBrand(br);
		product.setColor(col);
		product.setCategory(cat);
		product.setImageUrl("nm.com//blahblah");
		products.create(product);
	}

	@Test
	public void testPOJOs() {
		String expResult;
		String pro = ((Product) products.get((long) 1)).toString();
		expResult = "{\"id\":\"1\", \"brand\":\"{\"id\":\"27\", \"name\":\"N&M\"}\", \"color\":\"{\"id\":\"25\", \"name\":\"turquoise\"}\", \"category\":\"{\"id\":\"25\", \"name\":\"T-Shirt\"}\", \"url\":\"nm.com//blahblah\", \"user\":\"{\"id\":\"25\", \"name\":\"Nihad\", \"surname\":\"ness\", \"password\":\"guessme\", \"role\":\"Admin\"}\"}";
		assertEquals(expResult, pro);

		expResult = "{\"id\":\"1\", \"name\":\"Nihad\", \"surname\":\"ness\", \"password\":\"guessme\", \"role\":\"Admin\"}";
		String u = ((User) users.get((long) 1)).toString();
		assertEquals(expResult, u);

		expResult = "{\"id\":\"1\", \"name\":\"turquoise\"}";
		String col = ((Color) colors.get((long) 1)).toString();
		assertEquals(expResult, col);

		expResult = "{\"id\":\"1\", \"name\":\"N&M\"}";
		String bra = ((Brand) brands.get((long) 1)).toString();
		assertEquals(expResult, bra);

		String cat = ((Category) categories.get((long) 1)).toString();
		expResult = "{\"id\":\"1\", \"name\":\"T-Shirt\"}";
		assertEquals(expResult, cat);
	}

	@Test
	public void apiSenderSetup() {
		try {
			try {
				ApiHandler.getInstance().run();
				assertEquals(ApiHandler.instance, ApiHandler.getInstance());
			} catch (IOException e) {
				// TODO Testing
				e.printStackTrace();
			}
			Main m=new Main();
			m.main(null);
			assertEquals(true, Main.started);
			
			String url;
			String expResults;

			url = "http://127.0.0.1:9292/nm?";
			expResults = "FAILED";
			assertEquals(expResults, sendGet(url));

			url = "http://localhost:9292/nm?uId=11&p=guessme";
			expResults = "OK";
			assertEquals(expResults, sendGet(url));

			url = "http://localhost:9292/nm?uId=11";
			expResults = "FAILED";
			assertEquals(expResults, sendGet(url));
			
			url = "http://localhost:9292/nm?uId=aa&p=guessme";
			expResults = "FAILED";
			assertEquals(expResults, sendGet(url));

			url = "http://localhost:9292/nm?brId=2";
			expResults = "ZERO";
			assertEquals(expResults, sendGet(url));

			url = "http://localhost:9292/nm?brId=44";
			expResults = "[{\"id\":\"19\", \"brand\":\"{\"id\":\"44\", \"name\":\"N&M\"}\", \"color\":\"{\"id\":\"43\", \"name\":\"turquoise\"}\", \"category\":\"{\"id\":\"43\", \"name\":\"T-Shirt\"}\", \"url\":\"nm.com//blahblah\", \"user\":\"{\"id\":\"43\", \"name\":\"Nihad\", \"surname\":\"ness\", \"password\":\"guessme\", \"role\":\"Admin\"}\"},]";
			assertEquals(expResults, sendGet(url));
			
			url = "http://localhost:9292/nm?brId=44";
			assertEquals(expResults, sendGet(url));
			
			url = "http://localhost:9292/nm?catId=43";
			assertEquals(expResults, sendGet(url));
			
			url = "http://localhost:9292/nm?colId=43";
			assertEquals(expResults, sendGet(url));
			
			url = "http://localhost:9292/nm?catId=43&brId=44";
			assertEquals(expResults, sendGet(url));
			
			url = "http://localhost:9292/nm?catId=43&colId=43";
			assertEquals(expResults, sendGet(url));
			
			url = "http://localhost:9292/nm?brId=44&catId=43&colId=43";
			expResults = "[{\"id\":\"19\", \"brand\":\"{\"id\":\"44\", \"name\":\"N&M\"}\", \"color\":\"{\"id\":\"43\", \"name\":\"turquoise\"}\", \"category\":\"{\"id\":\"43\", \"name\":\"T-Shirt\"}\", \"url\":\"nm.com//blahblah\", \"user\":\"{\"id\":\"43\", \"name\":\"Nihad\", \"surname\":\"ness\", \"password\":\"guessme\", \"role\":\"Admin\"}\"},]";
			assertEquals(expResults, sendGet(url));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@Test
	public void apiReceiverTest() {
		try {
			String query;
			String expResults;

			ApiHandler.MyHandler m=new ApiHandler.MyHandler();
			query = "";
			expResults = "FAILED";
			assertEquals(expResults, m.testHandle(query));

			query = "uId=11&p=guessme";
			expResults = "OK";
			assertEquals(expResults, m.testHandle(query));

			query = "uId=11";
			expResults = "FAILED";
			assertEquals(expResults, m.testHandle(query));
			
			query = "uId=aa&p=guessme";
			expResults = "FAILED";
			assertEquals(expResults, m.testHandle(query));
			
			query = "brId=2";
			expResults = "ZERO";
			assertEquals(expResults, m.testHandle(query));

			query = "brId=44";
			expResults = "[{\"id\":\"19\", \"brand\":\"{\"id\":\"44\", \"name\":\"N&M\"}\", \"color\":\"{\"id\":\"43\", \"name\":\"turquoise\"}\", \"category\":\"{\"id\":\"43\", \"name\":\"T-Shirt\"}\", \"url\":\"nm.com//blahblah\", \"user\":\"{\"id\":\"43\", \"name\":\"Nihad\", \"surname\":\"ness\", \"password\":\"guessme\", \"role\":\"Admin\"}\"},]";
			assertEquals(expResults, m.testHandle(query));
			
			query = "brId=44&catId=43&colId=43";
			expResults = "[{\"id\":\"19\", \"brand\":\"{\"id\":\"44\", \"name\":\"N&M\"}\", \"color\":\"{\"id\":\"43\", \"name\":\"turquoise\"}\", \"category\":\"{\"id\":\"43\", \"name\":\"T-Shirt\"}\", \"url\":\"nm.com//blahblah\", \"user\":\"{\"id\":\"43\", \"name\":\"Nihad\", \"surname\":\"ness\", \"password\":\"guessme\", \"role\":\"Admin\"}\"},]";
			assertEquals(expResults, m.testHandle(query));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String sendGet(String url) throws Exception {

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		return response.toString();

	}
}
