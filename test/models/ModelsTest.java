package models;

import java.util.List;

import org.junit.*;

import models.*;
import play.test.WithApplication;
import static org.junit.Assert.*;
import static play.test.Helpers.*;


public class ModelsTest extends WithApplication{
	@Before
	public void setUp() {
		fakeApplication(inMemoryDatabase());
	}
	
	//provjera da li je sacuvan user
	@Test
	public void testCreate() {
		Bookmark.create("test", "www.bitcamp.ba");
		Bookmark b = Bookmark.find(1);
		
		assertNotNull(b);
		assertEquals(b.username, "test");
		assertEquals(b.url, "www.bitcamp.ba");
	}
	
	//provjeravamo da li ima ista na id=1000
	@Test
	public void testFindNonExisting() {
		Bookmark b = Bookmark.find(1000);
		
		assertNull(b);
	}
	
	//testiramo brisanje
	@Test
	public void testDelete() {
		Bookmark.create("test", "www.bitcamp.ba");
		Bookmark.delete(1);
		Bookmark b = Bookmark.find(1);
		assertNull(b);
	}
	
	//vracanje svih bookmarka koji su vezani za 1 usera
	@Test
	public void testAll() {
		Bookmark[] all = { new Bookmark("test", "www.bitcamp.ba"),
				new Bookmark("test", "www.pik.ba"),
				new Bookmark("test", "www.ebay.com"),
				new Bookmark("nijeTest", "www.ekupon.ba")
		};
		for(int i = 0; i < all.length; i++)
			all[i].save();
		
		assertEquals(3, Bookmark.all("test").size());
		List<Bookmark> base = Bookmark.all("test");
		assertTrue(base.contains(all[0]));
		assertFalse(base.contains(all[3]));
		
	}

}
