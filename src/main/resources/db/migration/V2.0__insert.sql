INSERT INTO public.user_role(role)
	VALUES ('USER');
INSERT INTO public.user_role(role)
	VALUES ('ADMIN');

INSERT INTO public.user_info(name, role_id, email, password, phone_number)
	VALUES ('Eugene', 1, 'kara-91@tut.by', '$2a$10$nmYkvdXJEJ3T/iSEpHUoauUFqDAvSZWS8icXsLOi.ZYYQdXCkThfO', '+375297320231');

INSERT INTO public.user_info(name, role_id, email, password, phone_number)
	VALUES ('Vladimir', 1, 'vova@tut.by', '$2a$10$nmYkvdXJEJ3T/iSEpHUoauUFqDAvSZWS8icXsLOi.ZYYQdXCkThfO', '+375295325531');

INSERT INTO public.user_info(name, role_id, email, password, phone_number)
	VALUES ('Admin', 2, 'admin@tut.by', '$2a$10$nmYkvdXJEJ3T/iSEpHUoauUFqDAvSZWS8icXsLOi.ZYYQdXCkThfO', '+375297325531');

INSERT INTO public.car_brand_name(name)
	VALUES ('Audi');

INSERT INTO public.car_brand_name(name)
	VALUES ('Volkswagen');

INSERT INTO public.car_brand(name, car_brand_name_id)
	VALUES ('80B3', 1);

INSERT INTO public.car_brand(name, car_brand_name_id)
	VALUES ('80B4', 1);

INSERT INTO public.car_brand(name, car_brand_name_id)
	VALUES ('100', 1);

INSERT INTO public.car_brand(name, car_brand_name_id)
	VALUES ('golf', 2);

INSERT INTO public.car_ad(price,user_id, descriptions, car_brand_id, publication_date,status,price_currency)
	VALUES (300000, 1, 'very good car', 1, '2021-04-25 21:38:09.169','ACTIVE', 'BYN');

INSERT INTO public.car_ad(price,user_id, descriptions, car_brand_id, publication_date,status,price_currency)
    VALUES (320000, 1, 'very good car', 1, '2021-04-25 21:38:09.169','ACTIVE', 'BYN');

INSERT INTO public.car_ad(price,user_id, descriptions, car_brand_id, publication_date,status,price_currency)
    VALUES (400000, 1, 'very good car', 1, '2021-04-25 21:38:09.169','ACTIVE', 'BYN');

INSERT INTO public.car_ad(price,user_id, descriptions, car_brand_id, publication_date,status,price_currency)
    VALUES (530000, 1, 'very good car', 1, '2021-04-25 21:38:09.169','ACTIVE', 'BYN');

INSERT INTO public.car_ad(price,user_id, descriptions, car_brand_id, publication_date,status,price_currency)
    VALUES (320000, 1, 'very good car', 1, '2021-04-25 21:38:09.169','ACTIVE', 'BYN');

INSERT INTO public.car_ad(price,user_id, descriptions, car_brand_id, publication_date,status,price_currency)
    VALUES (320000, 1, 'very good car', 1, '2021-04-25 21:38:09.169','ACTIVE', 'BYN');

INSERT INTO public.car_ad(price,user_id, descriptions, car_brand_id, publication_date,status,price_currency)
    VALUES (323000, 1, 'very good car', 1, '2021-04-25 21:38:09.169','ACTIVE', 'BYN');

INSERT INTO public.car_ad(price,user_id, descriptions, car_brand_id, publication_date,status,price_currency)
    VALUES (312000, 1, 'very good car', 1, '2021-04-25 21:38:09.169','ACTIVE', 'BYN');

INSERT INTO public.car_ad(price,user_id, descriptions, car_brand_id, publication_date,status,price_currency)
    VALUES (3340000, 1, 'very good car', 1, '2021-04-25 21:38:09.169','ACTIVE', 'BYN');

INSERT INTO public.car_ad(price,user_id, descriptions, car_brand_id, publication_date,status,price_currency)
    VALUES (323000, 1, 'very good car', 1, '2021-04-25 21:38:09.169','ACTIVE', 'BYN');

INSERT INTO public.car_ad(price,user_id, descriptions, car_brand_id, publication_date,status,price_currency)
    VALUES (3007000, 1, 'very good car', 1, '2021-03-16 21:38:09.169','ACTIVE', 'BYN');

INSERT INTO public.car_ad(price,user_id, descriptions, car_brand_id, publication_date,status,price_currency)
    VALUES (3000300, 1, 'very good car', 1, '2021-03-16 21:38:09.169','ACTIVE', 'BYN');

INSERT INTO public.car_ad(price,user_id, descriptions, car_brand_id, publication_date,status,price_currency)
    VALUES (3003000, 1, 'very good car', 1, '2021-03-16 21:38:09.169','ACTIVE', 'BYN');

INSERT INTO public.currency_exchange(currency_main, currency_to, value)
	VALUES ('BYN', 'RUB', 30.34);

INSERT INTO public.currency_exchange(currency_main, currency_to, value)
	VALUES ('BYN', 'BYN', 1.0);

INSERT INTO public.currency_exchange(currency_main, currency_to, value)
	VALUES ('BYN', 'EUR', 3.10);

INSERT INTO public.currency_exchange(currency_main, currency_to, value)
	VALUES ('BYN', 'USD', 2.52);

INSERT INTO public.currency_exchange(currency_main, currency_to, value)
	VALUES ('BYN', 'UAH', 10.75);

INSERT INTO public.currency_exchange(currency_main, currency_to, value)
	VALUES ('RUB', 'RUB', 1.0);

INSERT INTO public.currency_exchange(currency_main, currency_to, value)
	VALUES ('RUB', 'BYN', 0.04);

INSERT INTO public.currency_exchange(currency_main, currency_to, value)
	VALUES ('RUB', 'EUR', 0.01);

INSERT INTO public.currency_exchange(currency_main, currency_to, value)
	VALUES ('RUB', 'USD', 0.015);

INSERT INTO public.currency_exchange(currency_main, currency_to, value)
	VALUES ('RUB', 'UAH', 0.20);

INSERT INTO public.currency_exchange(currency_main, currency_to, value)
	VALUES ('USD', 'RUB', 70.34);

INSERT INTO public.currency_exchange(currency_main, currency_to, value)
	VALUES ('USD', 'BYN', 0.40);

INSERT INTO public.currency_exchange(currency_main, currency_to, value)
	VALUES ('USD', 'EUR', 1.10);

INSERT INTO public.currency_exchange(currency_main, currency_to, value)
	VALUES ('USD', 'USD', 1.0);

INSERT INTO public.currency_exchange(currency_main, currency_to, value)
	VALUES ('USD', 'UAH', 0.10);

INSERT INTO public.currency_exchange(currency_main, currency_to, value)
	VALUES ('EUR', 'RUB', 90.34);

INSERT INTO public.currency_exchange(currency_main, currency_to, value)
	VALUES ('EUR', 'BYN', 3.10);

INSERT INTO public.currency_exchange(currency_main, currency_to, value)
	VALUES ('EUR', 'EUR', 1.0);

INSERT INTO public.currency_exchange(currency_main, currency_to, value)
	VALUES ('EUR', 'USD', 0.92);

INSERT INTO public.currency_exchange(currency_main, currency_to, value)
	VALUES ('EUR', 'UAH', 15.75);

INSERT INTO public.currency_exchange(currency_main, currency_to, value)
	VALUES ('UAH', 'RUB', 30.34);

INSERT INTO public.currency_exchange(currency_main, currency_to, value)
	VALUES ('UAH', 'BYN', 10.0);

INSERT INTO public.currency_exchange(currency_main, currency_to, value)
	VALUES ('UAH', 'EUR', 15.10);

INSERT INTO public.currency_exchange(currency_main, currency_to, value)
	VALUES ('UAH', 'USD', 10.52);

INSERT INTO public.currency_exchange(currency_main, currency_to, value)
	VALUES ('UAH', 'UAH', 1.0);