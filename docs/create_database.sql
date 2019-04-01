-- Taxi user.
CREATE SEQUENCE public.id_taxi_user_seq;
CREATE TABLE public.taxi_user (
                id_taxi_user INTEGER NOT NULL DEFAULT nextval('public.id_taxi_user_seq'),
                first_name VARCHAR(255) NOT NULL,
                last_name VARCHAR(255) NOT NULL,
                phone VARCHAR(50) UNIQUE NOT NULL,
                password VARCHAR(60) NOT NULL,
                CONSTRAINT id_taxi_user PRIMARY KEY (id_taxi_user)
);
COMMENT ON TABLE public.taxi_user IS 'Registered users.';
ALTER SEQUENCE public.id_taxi_user_seq OWNED BY public.taxi_user.id_taxi_user;


-- User's travels.
CREATE SEQUENCE public.id_travel_seq;
CREATE TABLE public.travel (
                id_travel INTEGER NOT NULL DEFAULT nextval('public.id_travel_seq'),
                id_taxi_user INTEGER NOT NULL,
                CONSTRAINT id_travel PRIMARY KEY (id_travel)
);
COMMENT ON TABLE public.travel IS 'User''s travels.';
ALTER SEQUENCE public.id_travel_seq OWNED BY public.travel.id_travel;


-- Travel's tracking.
CREATE SEQUENCE public.id_tracking_seq;
CREATE TABLE public.tracking (
                id_tracking INTEGER NOT NULL DEFAULT nextval('public.id_tracking_seq'),
                lat REAL NOT NULL,
                lng REAL NOT NULL,
                time TIMESTAMP NOT NULL,
                id_travel INTEGER NOT NULL,
                CONSTRAINT id_tracking PRIMARY KEY (id_tracking)
);
COMMENT ON TABLE public.tracking IS 'Travels'' tracking.';
COMMENT ON COLUMN public.tracking.lat IS 'Latitude.';
COMMENT ON COLUMN public.tracking.lng IS 'Longitude';
ALTER SEQUENCE public.id_tracking_seq OWNED BY public.tracking.id_tracking;


-- Constraints.
ALTER TABLE public.travel ADD CONSTRAINT user_travel_fk
FOREIGN KEY (id_taxi_user)
REFERENCES public.taxi_user (id_taxi_user)
ON DELETE RESTRICT
ON UPDATE RESTRICT
NOT DEFERRABLE;

ALTER TABLE public.tracking ADD CONSTRAINT travel_tracking_fk
FOREIGN KEY (id_travel)
REFERENCES public.travel (id_travel)
ON DELETE RESTRICT
ON UPDATE RESTRICT
NOT DEFERRABLE;


-- Some users.
INSERT INTO taxi_user (first_name, last_name, phone, password) 
        VALUES ('Daniel', 'Vargas', '5511223344', '$2a$11$PEAXua65nJ3WIRe5xj2p7OSgOJP8aEhDK9iYvR5zwubHRT1CB2bWe');
INSERT INTO taxi_user (first_name, last_name, phone, password) 
        VALUES ('Ileana', 'Juárez', '5544332211', '$2a$11$ueTKIqT56LbZ9Jl1QkB7c.iNuheAvCSmqyc5Z5hjOW7YUueJJBv6u');
INSERT INTO taxi_user (first_name, last_name, phone, password) 
        VALUES ('José', 'Méndez', '5512345678', '$2a$11$ozD9EFg/RLDBg3O.KyFNLupIX9dhagF4tkHKg.6kvYmjXp3jXOVg6');


-- Some travels.
INSERT INTO travel (id_taxi_user)  VALUES (1);
INSERT INTO travel (id_taxi_user)  VALUES (1);
INSERT INTO travel (id_taxi_user)  VALUES (1);
INSERT INTO travel (id_taxi_user)  VALUES (2);
INSERT INTO travel (id_taxi_user)  VALUES (2);
INSERT INTO travel (id_taxi_user)  VALUES (3);


-- mapquestapi.com

-- Some locations.
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 14:00:00.0', 1, -99.136192, 19.387457);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 14:02:00.0', 1, -99.135895, 19.387419);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 14:04:00.0', 1, -99.135902, 19.386082);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 14:06:00.0', 1, -99.133774, 19.385799);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 14:08:00.0', 1, -99.13456, 19.382032);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 14:10:00.0', 1, -99.141441, 19.383062);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 14:12:00.0', 1, -99.139847, 19.382635);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 14:14:00.0', 1, -99.144028, 19.357121);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 14:16:00.0', 1, -99.144173, 19.34321);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 14:18:00.0', 1, -99.171097, 19.34557);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 14:20:00.0', 1, -99.171692, 19.341631);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 14:22:00.0', 1, -99.176208, 19.336061);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 14:24:00.0', 1, -99.177368, 19.333694);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 14:26:00.0', 1, -99.178093, 19.333441);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 14:28:00.0', 1, -99.178307, 19.333509);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 14:29:00.0', 1, -99.178345, 19.333618);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 14:30:00.0', 1, -99.178757, 19.332148);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 14:32:00.0', 1, -99.17907, 19.330444);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 14:34:00.0', 1, -99.18074, 19.327351);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 14:35:00.0', 1, -99.184975, 19.323431);

INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 15:00:00.0', 2, -99.184975, 19.323431);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 15:07:00.0', 2, -99.188881, 19.328669);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 15:14:00.0', 2, -99.189568, 19.331976);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 15:21:00.0', 2, -99.189629, 19.332457);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 15:28:00.0', 2, -99.189995, 19.336512);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 15:35:00.0', 2, -99.182587, 19.362247);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 15:42:00.0', 2, -99.177811, 19.364908);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 15:49:00.0', 2, -99.143539, 19.357176);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 15:56:00.0', 2, -99.141464, 19.369827);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 16:03:00.0', 2, -99.138893, 19.387123);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 16:05:00.0', 2, -99.136192, 19.387457);

INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-28 21:05:00.0', 3, -99.136192, 19.387457);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-28 21:08:00.0', 3, -99.135895, 19.387419);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-28 21:11:00.0', 3, -99.135902, 19.386082);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-28 21:14:00.0', 3, -99.133774, 19.385799);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-28 21:17:00.0', 3, -99.13456, 19.382032);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-28 21:19:00.0', 3, -99.161057, 19.38595);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-28 21:21:00.0', 3, -99.163391, 19.389656);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-28 21:23:00.0', 3, -99.161797, 19.396328);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-28 21:25:00.0', 3, -99.156143, 19.395838);

INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 09:08:00.0', 4, -99.19751, 19.433525);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 09:12:00.0', 4, -99.197571, 19.431686);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 09:16:00.0', 4, -99.197029, 19.429918);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 09:20:00.0', 4, -99.197945, 19.426756);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 09:24:00.0', 4, -99.201042, 19.427225);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 09:28:00.0', 4, -99.202202, 19.426867);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 09:30:00.0', 4, -99.200356, 19.42448);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 09:33:00.0', 4, -99.194641, 19.41651);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 09:36:00.0', 4, -99.193893, 19.415472);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 09:39:00.0', 4, -99.191002, 19.38488);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 09:41:00.0', 4, -99.210136, 19.332426);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 09:44:00.0', 4, -99.207436, 19.332079);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 09:48:00.0', 4, -99.19104, 19.336746);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 09:51:00.0', 4, -99.183929, 19.337358);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 09:53:00.0', 4, -99.183746, 19.337297);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 09:56:00.0', 4, -99.176208, 19.336061);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 09:59:00.0', 4, -99.176704, 19.334831);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-27 10:01:00.0', 4, -99.177467, 19.335106);

INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-28 14:00:00.0', 5, -99.182167, 19.363485);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-28 14:05:00.0', 5, -99.180832, 19.367662);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-28 14:10:00.0', 5, -99.143539, 19.357176);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-28 14:15:00.0', 5, -99.141464, 19.369827);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-28 14:20:00.0', 5, -99.138893, 19.387123);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-28 14:25:00.0', 5, -99.138252, 19.38747);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-28 14:30:00.0', 5, -99.13826, 19.387394);

INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-28 15:15:00.0', 6, -99.13826, 19.387394);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-28 15:18:00.0', 6, -99.138252, 19.38747);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-28 15:21:00.0', 6, -99.135895, 19.387419);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-28 15:24:00.0', 6, -99.135902, 19.386082);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-28 15:27:00.0', 6, -99.133774, 19.385799);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-28 15:30:00.0', 6, -99.140434, 19.357279);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-28 15:33:00.0', 6, -99.141563, 19.357388);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-28 15:36:00.0', 6, -99.142197, 19.357231);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-28 15:39:00.0', 6, -99.176872, 19.364424);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-28 15:42:00.0', 6, -99.182426, 19.362692);
INSERT INTO tracking (time, id_travel, lng, lat) VALUES ('2019-03-28 15:45:00.0', 6, -99.182167, 19.363485);
