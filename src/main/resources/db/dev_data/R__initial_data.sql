delete
from items;

INSERT INTO ITEMS(id, name, description)
values (nextval('item_id_seq'), 'shoe', 'This is a shoe');

delete
from users;

INSERT INTO USERS(username, password, enabled)
values ('user', 'password', true);

INSERT INTO authorities(username, authority)
values ('user', 'USER');