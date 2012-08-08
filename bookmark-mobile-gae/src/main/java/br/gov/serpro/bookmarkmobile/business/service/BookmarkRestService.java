package br.gov.serpro.bookmarkmobile.business.service;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.gov.serpro.bookmarkmobile.business.BookmarkBC;
import br.gov.serpro.bookmarkmobile.domain.Bookmark;

@Path("/bookmark")
public class BookmarkRestService {

	@Inject
	private BookmarkBC bookmarkBC;

	@GET
	@Path("/findAll")
	@Produces("application/json")
	public List<Bookmark> findAll() {

		return this.bookmarkBC.findAll();

	}

	@GET
	@Path("/delete/{bookmarkId}")
	@Produces("application/json")
	public void delete(@PathParam("bookmarkId") String bookmarkId) {

		this.bookmarkBC.delete(Long.parseLong(bookmarkId));
		
	}

	@GET
	@Path("/insert/{description}/{link}")
	@Produces("application/json")
	public void insert(@PathParam("description") String description, @PathParam("link") String link) {

		this.bookmarkBC.insert(new Bookmark(description, link));
		
	}

	@GET
	@Path("/update/{bookmarkId}/{description}/{link}")
	@Produces("application/json")
	public void insert(@PathParam("bookmarkId") String bookmarkId, @PathParam("description") String description, @PathParam("link") String link) {

		Bookmark bookmark = new Bookmark(description, link);
		bookmark.setId(Long.parseLong(bookmarkId));
		
		this.bookmarkBC.update(bookmark);
		
	}

	@GET
	@Path("/load/{bookmarkId}")
	@Produces("application/json")
	public Bookmark load(@PathParam("bookmarkId") String bookmarkId) {

		return this.bookmarkBC.load(Long.parseLong(bookmarkId));
		
	}

}
