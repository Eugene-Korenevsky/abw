create table if not exists user_role(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    role character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT user_role_pkey PRIMARY KEY (id)
);

create table if not exists user_info(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
        name character varying(255) COLLATE pg_catalog."default" NOT NULL,
        role_id bigint NOT NULL,
        email character varying(255) COLLATE pg_catalog."default" NOT NULL,
        password character varying(255) COLLATE pg_catalog."default" NOT NULL,
        phone_number character varying(255) COLLATE pg_catalog."default" NOT NULL,
        CONSTRAINT user_info_pkey PRIMARY KEY (id),
        CONSTRAINT fkoqajbnsbcmpki28seidqrht6o FOREIGN KEY (role_id)
            REFERENCES public.user_role (id) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
);


create table if not exists car_brand_name(
   id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
       name character varying(255) COLLATE pg_catalog."default" NOT NULL,
       CONSTRAINT car_brand_name_pkey PRIMARY KEY (id)
);

create table if not exists car_brand(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
        name character varying(255) COLLATE pg_catalog."default" NOT NULL,
        car_brand_name_id bigint NOT NULL,
        CONSTRAINT car_brand_pkey PRIMARY KEY (id),
        CONSTRAINT fki72udxeubyj7j2mfevfeo2avl FOREIGN KEY (car_brand_name_id)
            REFERENCES public.car_brand_name (id) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
);

create table if not exists car_ad(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
        price bigint NOT NULL,
        user_id bigint NOT NULL,
        descriptions character varying(1000) COLLATE pg_catalog."default",
        car_brand_id bigint NOT NULL,
        publication_date timestamp without time zone,
        end_publication_date timestamp without time zone,
        status character varying(15) COLLATE pg_catalog."default",
        price_currency character varying(3) COLLATE pg_catalog."default",
        CONSTRAINT car_ad_pkey PRIMARY KEY (id),
        CONSTRAINT fk4dt09u56oym4jxbveh57eybnn FOREIGN KEY (car_brand_id)
            REFERENCES public.car_brand (id) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION,
        CONSTRAINT fkt2t6nquf4b9tuy5lvrp2j90rm FOREIGN KEY (user_id)
            REFERENCES public.user_info (id) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
);

create table if not exists car_image(
        id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
        content_image oid NOT NULL,
        car_ad_id bigint NOT NULL,
        CONSTRAINT car_image1_pkey PRIMARY KEY (id),
        CONSTRAINT fk6bk2hppd7gqyr8cnbtahb2b1w FOREIGN KEY (car_ad_id)
            REFERENCES public.car_ad (id) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
)

WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

create table if not exists currency_exchange(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    currency_main character varying(3) COLLATE pg_catalog."default" NOT NULL,
    currency_to character varying(3) COLLATE pg_catalog."default" NOT NULL,
    value numeric(19,4),
    CONSTRAINT currency_exchange_pkey PRIMARY KEY (id)
);