package br.gov.serpro.bookmarkmobile.persistence;

import br.gov.frameworkdemoiselle.objectify.template.ObjectifyCrud;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.serpro.bookmarkmobile.domain.Bookmark;

@PersistenceController
public class BookmarkDAO extends ObjectifyCrud<Bookmark> {
	
	private static final long serialVersionUID = 1L;
	
}
