INSERT INTO book (title) VALUES ("The Pragmatic Programmer");

INSERT INTO member (name) VALUES ("Maria Silva");

INSERT INTO checkout_item (member_id, book_id) VALUES (
    (SELECT member.id FROM member WHERE name="Maria Silva"),
    (SELECT book.id FROM book WHERE title="The Pragmatic Programmer")
);