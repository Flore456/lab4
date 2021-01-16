CREATE TABLE IF NOT EXISTS public.bank
(
    account_num character varying(60) NOT NULL primary key,
    account_year integer NOT NULL,
    number_card integer,
    manager_name character varying(20)
);
CREATE TABLE IF NOT EXISTS public.car
(
    car_id character varying(60) NOT NULL primary key,
    brand character varying(10)	 NOT NULL,
    color character varying(10)	 NOT NULL
);
CREATE TABLE IF NOT EXISTS  public.person
(
    id character varying(60) NOT NULL primary key,
    name character varying(20) NOT NULL,
    first_name character varying(50) NOT NULL,
    city character varying(50),
    birthday date,
    account_num character varying(60) references Bank(account_num),
    car_id character varying(60) references Car(car_id),
    CONSTRAINT "FK_BANK" FOREIGN KEY (account_num)
    REFERENCES public.bank (account_num) MATCH SIMPLE,
    CONSTRAINT "FK_CAR" FOREIGN KEY (car_id)
    REFERENCES public.car (car_id) MATCH SIMPLE
);

