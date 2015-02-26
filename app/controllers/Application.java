package controllers;

import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.*;
import models.*;

public class Application extends Controller {

	static String message = "Welcome to Bookmark";

	static Form<Name> input = new Form<Name>(Name.class);
	static String name = "no name";

	static Form<Bookmark> newBookmark = new Form<Bookmark>(Bookmark.class);

	public static Result index() {
		String name = session("name");
		if (name == null) {
			name = "No name";
			return ok(index.render(message, name, input));
		} else {
			return redirect("/bookmarks");
		}
	}

	public static Result recognize() {
		name = input.bindFromRequest().get().name;
		session("name", name);
		return redirect("/bookmarks");
	}

	public static Result bookmarks() {
		String name = session("name");
		if (name == null) {
			return redirect("/");
		}
		return ok(bookmark.render(newBookmark, name, Bookmark.all(name)));
	}

	public static Result addBookmark() {
		String url = newBookmark.bindFromRequest().get().url;
		String username = session("name");
		Bookmark.create(username, url);
		return redirect("/bookmarks");
	}

}
