INSERT INTO employer (ruc, company_name) VALUES ('2064725874', 'DevTech Inudstries');

INSERT INTO balance (amount_available, bank_account_number, retirement_date) VALUES (50000.34, '1234-4312-2134-3124-1326', TO_DATE('2022-05-12', 'YYYY-MM-DD'));
INSERT INTO balance (amount_available, bank_account_number, retirement_date) VALUES (1000.34, '5555-4444-3333-2222-1111', TO_DATE('2022-04-10', 'YYYY-MM-DD'));

INSERT INTO request (amount, bank_account_number, retirement_date) VALUES (30000, '1234-4312-2134-3124-1326', TO_DATE('2022-05-12', 'YYYY-MM-DD'));

INSERT INTO customer (dni, firstname, lastname, phone_number, email, afp, balance_id) VALUES ('40853581', 'Jimmy Willer', 'Maco Elera', '943133590', ' jmacoe@gmail.com', 'INTEGRA', 1);
INSERT INTO customer (dni, firstname, lastname, phone_number, email, afp, balance_id, request_id) VALUES ('40853582', 'Julio', 'Verne', '943133594', ' jmacoe@gmail.com', 'PRIMA', 2, 1);
INSERT INTO customer (dni, firstname, lastname, phone_number, email, afp) VALUES ('40853583', 'Albert', 'Einstein', '943133595', ' jmacoe@gmail.com', 'PROFUTURO');
INSERT INTO customer (dni, firstname, lastname, phone_number, email, afp) VALUES ('40853584', 'John', 'Lenon', '943133593', ' jmacoe@gmail.com', 'HABITAT');

INSERT INTO customer_employer (customer_id, employer_id) VALUES (1, 1);
INSERT INTO customer_employer (customer_id, employer_id) VALUES (2, 1);
INSERT INTO customer_employer (customer_id, employer_id) VALUES (3, 1);
INSERT INTO customer_employer (customer_id, employer_id) VALUES (4, 1);

