SELECT book.title FROM book
WHERE book.id NOT IN(
	SELECT book_id FROM checkout_item
	WHERE book_id IS NOT NULL
)
UNION
SELECT movie.title FROM movie
WHERE movie.id NOT IN(
	SELECT movie_id FROM checkout_item
	WHERE movie_id IS NOT NULL
);