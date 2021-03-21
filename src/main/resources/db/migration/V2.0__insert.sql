INSERT INTO public.user_role(role)
	VALUES ('user');
INSERT INTO public.user_role(role)
	VALUES ('administration');

INSERT INTO public.user_info(name, role_id, email, password, phone_number)
	VALUES ('Eugene', 1, 'kara-91@tut.by', 'kara', '+375297320231');

INSERT INTO public.user_info(name, role_id, email, password, phone_number)
	VALUES ('Vladimir', 1, 'vova@tut.by', 'vovan', '+375295325531');

INSERT INTO public.user_info(name, role_id, email, password, phone_number)
	VALUES ('Admin', 2, 'admin@tut.by', 'admin', '+375297325531');

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

INSERT INTO public.car_ad(price, sold, user_id, descriptions, car_brand_id, publication_date)
	VALUES (300000, false, 1, 'very good car', 1, '2021-03-16 21:38:09.169' );

INSERT INTO public.car_ad(price, sold, user_id, descriptions, car_brand_id, publication_date)
	VALUES (310000, false, 1, 'very good car', 1, '2021-03-16 22:38:09.169' );

INSERT INTO public.car_ad(price, sold, user_id, descriptions, car_brand_id, publication_date)
	VALUES (314000, false, 1, 'very good car', 1, '2021-03-16 23:38:09.169' );

INSERT INTO public.car_ad(price, sold, user_id, descriptions, car_brand_id, publication_date)
	VALUES (320000, false, 1, 'very good car', 1, '2021-03-16 16:38:09.169' );

INSERT INTO public.car_ad(price, sold, user_id, descriptions, car_brand_id, publication_date)
	VALUES (330000, true, 1, 'very good car', 1, '2021-03-16 17:38:09.169' );

INSERT INTO public.car_ad(price, sold, user_id, descriptions, car_brand_id, publication_date)
	VALUES (340000, true, 1, 'very good car', 3, '2021-03-16 21:35:09.169' );

INSERT INTO public.car_ad(price, sold, user_id, descriptions, car_brand_id, publication_date)
	VALUES (350000, false, 2, 'very good car', 2, '2021-03-16 23:38:09.169' );

INSERT INTO public.car_ad(price, sold, user_id, descriptions, car_brand_id, publication_date)
	VALUES (360000, false, 2, 'very good car', 2, '2021-03-16 23:38:09.169' );

INSERT INTO public.car_ad(price, sold, user_id, descriptions, car_brand_id, publication_date)
	VALUES (370000, true, 2, 'very good car', 1, '2021-03-14 19:28:09.169' );

INSERT INTO public.car_ad(price, sold, user_id, descriptions, car_brand_id, publication_date)
	VALUES (380000, false, 2, 'very good car', 1, '2021-03-14 18:38:09.169' );

INSERT INTO public.car_ad(price, sold, user_id, descriptions, car_brand_id, publication_date)
	VALUES (390000, false, 2, 'very good car', 1, '2021-03-14 16:38:09.169' );

INSERT INTO public.car_ad(price, sold, user_id, descriptions, car_brand_id, publication_date)
	VALUES (400000, false, 2, 'very good car', 2, '2021-03-14 20:38:09.169' );

INSERT INTO public.car_ad(price, sold, user_id, descriptions, car_brand_id, publication_date)
	VALUES (410000, false, 2, 'very good car', 2, '2021-03-14 21:38:09.169' );

INSERT INTO public.car_ad(price, sold, user_id, descriptions, car_brand_id, publication_date)
	VALUES (420000, false, 2, 'very good car', 2, '2021-03-14 07:36:09.169' );

INSERT INTO public.car_ad(price, sold, user_id, descriptions, car_brand_id, publication_date)
	VALUES (430000, false, 2, 'very good car', 1, '2021-03-15 05:38:09.169' );

INSERT INTO public.car_ad(price, sold, user_id, descriptions, car_brand_id, publication_date)
	VALUES (434000, false, 2, 'very good car', 1, '2021-03-15 22:48:09.169' );

INSERT INTO public.car_ad(price, sold, user_id, descriptions, car_brand_id, publication_date)
	VALUES (330000, false, 2, 'very good car', 2, '2021-03-15 22:38:09.169' );

INSERT INTO public.car_ad(price, sold, user_id, descriptions, car_brand_id, publication_date)
	VALUES (330000, false, 2, 'very good car', 3, '2021-03-15 23:18:09.169' );

INSERT INTO public.car_ad(price, sold, user_id, descriptions, car_brand_id, publication_date)
	VALUES (360000, false, 2, 'very good car', 1, '2021-03-14 23:38:09.169' );

INSERT INTO public.car_ad(price, sold, user_id, descriptions, car_brand_id, publication_date)
	VALUES (370000, false, 2, 'very good car', 1, '2021-03-16 20:38:09.169' );

INSERT INTO public.car_ad(price, sold, user_id, descriptions, car_brand_id, publication_date)
	VALUES (380000, false, 2, 'very good car', 1, '2021-03-15 14:38:09.169' );

INSERT INTO public.car_ad(price, sold, user_id, descriptions, car_brand_id, publication_date)
	VALUES (390000, false, 1, 'very good car', 1, '2021-03-14 21:38:09.169' );

INSERT INTO public.car_ad(price, sold, user_id, descriptions, car_brand_id, publication_date)
	VALUES (304000, false, 1, 'very good car', 1, '2021-03-14 23:38:09.169' );

INSERT INTO public.car_ad(price, sold, user_id, descriptions, car_brand_id, publication_date)
	VALUES (305000, false, 1, 'very good car', 3, '2021-03-14 22:38:09.169' );

INSERT INTO public.car_ad(price, sold, user_id, descriptions, car_brand_id, publication_date)
	VALUES (100000, false, 1, 'very good car', 3, '2021-03-19 23:38:09.169' );

INSERT INTO public.car_ad(price, sold, user_id, descriptions, car_brand_id, publication_date)
	VALUES (200000, false, 1, 'very good car', 3, '2021-03-14 23:38:09.169' );

INSERT INTO public.car_ad(price, sold, user_id, descriptions, car_brand_id, publication_date)
	VALUES (320000, false, 1, 'very good car', 3, '2021-03-17 23:38:09.169' );

INSERT INTO public.car_ad(price, sold, user_id, descriptions, car_brand_id, publication_date)
	VALUES (350000, false, 1, 'very good car', 3, '2021-03-15 23:38:09.169' );

INSERT INTO public.car_image(image, car_ad_id)
	VALUES ('crgvtyhuyjijkyutytgr', 13);

INSERT INTO public.car_image(image, car_ad_id)
	VALUES ('crgvtyhuyjijkyutycrghyujitktgr', 13);

INSERT INTO public.car_image(image, car_ad_id)
	VALUES ('crgvtyhuyjijkyutytgr', 14);