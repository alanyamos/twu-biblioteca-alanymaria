SELECT member.name
FROM book, member, checkout_item
WHERE book.id=checkout_item.book_id AND
	     member.id=checkout_item.member_id AND
	     book.title="The Hobbit";