SELECT COUNT(member.id) FROM member
WHERE member.id NOT IN(
	SELECT member_id FROM checkout_item
);