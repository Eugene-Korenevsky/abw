create table if not exists user_ad_like
(
    user_id bigint NOT NULL,
    ad_id bigint NOT NULL,
    CONSTRAINT user_ad_like_pkey PRIMARY KEY (user_id, ad_id),
    CONSTRAINT "user_like-ad" FOREIGN KEY (ad_id)
        REFERENCES public.car_ad (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "user_like-user" FOREIGN KEY (user_id)
        REFERENCES public.user_info (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

create table if not exists user_ad_viewed
(
    user_id bigint NOT NULL,
    ad_id bigint NOT NULL,
    viewed_time timestamp without time zone NOT NULL,
    CONSTRAINT user_ad_viewed_pkey PRIMARY KEY (user_id, ad_id),
    CONSTRAINT "user_ad_viewed-ad" FOREIGN KEY (ad_id)
        REFERENCES public.car_ad (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "user_ad_viewed-user" FOREIGN KEY (user_id)
        REFERENCES public.user_info (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);