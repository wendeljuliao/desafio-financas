INSERT INTO tb_roles (id, name) VALUES (0, 'ROLE_CUSTOMER');
INSERT INTO tb_roles (id, name) VALUES (1, 'ROLE_ADMINISTRATOR');

INSERT INTO tb_users (id, fullname, document, email, password, amount, user_type) VALUES (0, 'Fulano de Tal', '09493244243', 'teste@hotmail.com', '$2a$12$Tb71.OFT87mrev85L8LNMe4xNBwVilfxGok52PygQvMFrGuZRjRle', 100.0, 'COMUM');
INSERT INTO tb_users (id, fullname, document, email, password, amount, user_type) VALUES (1, 'Fulano de Tal', '09493244249', 'teste2@hotmail.com', '$2a$12$Tb71.OFT87mrev85L8LNMe4xNBwVilfxGok52PygQvMFrGuZRjRle', 100.0, 'COMUM');

INSERT INTO tb_users_roles (user_id, role_id) VALUES (0, 0);
INSERT INTO tb_users_roles (user_id, role_id) VALUES (1, 0);