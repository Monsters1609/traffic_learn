--
-- PostgreSQL database dump
--

\restrict ZVa0PcvYf0VEMOaxu4reFg0jgFt2YTeq2mYZit4q7qdKCdfGj0Zpb07WCFQfEeR

-- Dumped from database version 18.4
-- Dumped by pg_dump version 18.4

-- Started on 2026-06-22 09:13:58

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE traffic_db;
--
-- TOC entry 5069 (class 1262 OID 18487)
-- Name: traffic_db; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE traffic_db WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Vietnamese_Vietnam.1252';


ALTER DATABASE traffic_db OWNER TO postgres;

\unrestrict ZVa0PcvYf0VEMOaxu4reFg0jgFt2YTeq2mYZit4q7qdKCdfGj0Zpb07WCFQfEeR
\connect traffic_db
\restrict ZVa0PcvYf0VEMOaxu4reFg0jgFt2YTeq2mYZit4q7qdKCdfGj0Zpb07WCFQfEeR

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 219 (class 1259 OID 18488)
-- Name: account_entity; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.account_entity (
    account_id uuid NOT NULL,
    created_at timestamp(6) with time zone NOT NULL,
    updated_at timestamp(6) with time zone,
    password character varying(255),
    account character varying(255)
);


ALTER TABLE public.account_entity OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 18497)
-- Name: multiple_account_roles_entity; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.multiple_account_roles_entity (
    multiple_account_roles_id uuid CONSTRAINT multiple_account_roles_entit_multiple_account_roles_id_not_null NOT NULL,
    account_id uuid,
    role_id uuid
);


ALTER TABLE public.multiple_account_roles_entity OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 18568)
-- Name: profile_entity; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.profile_entity (
    account_id uuid NOT NULL,
    fullname character varying(255),
    age integer,
    date integer,
    date_of integer,
    date_of_ integer,
    date_of_birth integer,
    gender character varying(255),
    phone_number integer,
    email character varying(255),
    avatar_url character varying(255),
    avatar_id character varying(255),
    address character varying(255),
    CONSTRAINT profile_entity_gender_check CHECK (((gender)::text = ANY ((ARRAY['MAN'::character varying, 'WOMAN'::character varying, 'OTHER'::character varying])::text[])))
);


ALTER TABLE public.profile_entity OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 18503)
-- Name: role_entity; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role_entity (
    role_id uuid NOT NULL,
    created_at timestamp(6) with time zone NOT NULL,
    updated_at timestamp(6) with time zone,
    role_name character varying(255),
    role_code integer,
    role_description character varying(255)
);


ALTER TABLE public.role_entity OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 18512)
-- Name: session_token_entity; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.session_token_entity (
    token_id uuid NOT NULL,
    created_at timestamp(6) with time zone NOT NULL,
    updated_at timestamp(6) with time zone,
    expires_at timestamp(6) without time zone,
    refresh_token character varying(255),
    account_id uuid
);


ALTER TABLE public.session_token_entity OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 18519)
-- Name: traffic_aqi_entity; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.traffic_aqi_entity (
    traffic_aqi_id uuid NOT NULL,
    created_at timestamp(6) with time zone NOT NULL,
    updated_at timestamp(6) with time zone,
    dew integer,
    dominentpol character varying(255),
    h integer,
    o3 integer,
    p real,
    pm10 integer,
    pm25 integer,
    t integer,
    w integer,
    wg integer,
    traffic_id uuid
);


ALTER TABLE public.traffic_aqi_entity OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 18526)
-- Name: traffic_info_entity; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.traffic_info_entity (
    traffic_id uuid NOT NULL,
    created_at timestamp(6) with time zone NOT NULL,
    updated_at timestamp(6) with time zone,
    address character varying(255),
    latitude double precision,
    longitude double precision
);


ALTER TABLE public.traffic_info_entity OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 18533)
-- Name: traffic_speed_entity; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.traffic_speed_entity (
    traffic_speed_id uuid NOT NULL,
    confidence real,
    current_speed double precision,
    current_travel_time double precision,
    frc character varying(255),
    free_flow_speed double precision,
    free_flow_travel_time double precision,
    road_closure boolean,
    traffic_level character varying(255),
    traffic_id uuid,
    created_at timestamp(6) with time zone NOT NULL,
    updated_at timestamp(6) with time zone,
    CONSTRAINT traffic_speed_entity_traffic_level_check CHECK (((traffic_level)::text = ANY ((ARRAY['LOW'::character varying, 'MEDIUM'::character varying, 'HIGH'::character varying])::text[])))
);


ALTER TABLE public.traffic_speed_entity OWNER TO postgres;

--
-- TOC entry 5056 (class 0 OID 18488)
-- Dependencies: 219
-- Data for Name: account_entity; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.account_entity VALUES ('3604b0fa-adba-465f-8b15-030c09a4989a', '2026-06-11 09:02:10.051715+07', '2026-06-11 09:02:10.051715+07', '$2a$10$IyjbV2HI3l5S5ODCkONj7OwPL9psx/oY.sRUTvq051sxNcyMe4hUi', 'admin');


--
-- TOC entry 5057 (class 0 OID 18497)
-- Dependencies: 220
-- Data for Name: multiple_account_roles_entity; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 5063 (class 0 OID 18568)
-- Dependencies: 226
-- Data for Name: profile_entity; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 5058 (class 0 OID 18503)
-- Dependencies: 221
-- Data for Name: role_entity; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 5059 (class 0 OID 18512)
-- Dependencies: 222
-- Data for Name: session_token_entity; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 5060 (class 0 OID 18519)
-- Dependencies: 223
-- Data for Name: traffic_aqi_entity; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 5061 (class 0 OID 18526)
-- Dependencies: 224
-- Data for Name: traffic_info_entity; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.traffic_info_entity VALUES ('0ebb4965-2e93-4f7f-b414-1a296db673eb', '2026-06-11 16:28:09.912051+07', '2026-06-11 16:28:09.912051+07', 'Đường xe máy Cầu Thanh Trì, Phường Lĩnh Nam, Phường Vĩnh Hưng, Hà Nội, 11712, Việt Nam', 20.990419240765505, 105.89849833842653);
INSERT INTO public.traffic_info_entity VALUES ('8df25f5c-2bad-4dca-8c69-4aab75af2fa8', '2026-06-11 16:28:22.982455+07', '2026-06-11 16:28:22.982455+07', 'Phường Lĩnh Nam, Hà Nội, 11709, Việt Nam', 20.98561105889552, 105.90055719976095);
INSERT INTO public.traffic_info_entity VALUES ('b09629b0-31d7-4d24-ba1e-04ed5db91241', '2026-06-11 16:28:59.893583+07', '2026-06-11 16:28:59.893583+07', 'Trường Tiểu học Thuý Lĩnh, Ngõ 115 Phố Thúy Lĩnh, Thúy Lĩnh, Phường Lĩnh Nam, Hà Nội, 11709, Việt Nam', 20.973750227321176, 105.89472375931345);
INSERT INTO public.traffic_info_entity VALUES ('761a6a82-94cd-481a-a8d9-bcbd02e5605b', '2026-06-11 17:25:46.291522+07', '2026-06-11 17:25:46.291522+07', 'Cầu Thanh Trì, Ngõ 31 Đường Xuân Khôi, Độc Lập, Phường Long Biên, Hà Nội, 11712, Việt Nam', 20.99234246332228, 105.90138074220032);
INSERT INTO public.traffic_info_entity VALUES ('9818e01b-01d1-408f-81bc-98b16defe794', '2026-06-12 11:24:55.689129+07', '2026-06-12 11:24:55.689129+07', 'Nút giao Thanh Xuân, Đường Khuất Duy Tiến, Phường Thanh Xuân, Hà Nội, 11417, Việt Nam', 20.990839945829347, 105.80280525647368);
INSERT INTO public.traffic_info_entity VALUES ('97ac42c3-2d22-4d3e-9919-e6c12f615c39', '2026-06-12 14:47:42.414704+07', '2026-06-12 14:47:42.414704+07', 'Cầu Thanh Trì, Đi đường Đỗ Mười, Vĩnh Hưng, Phường Vĩnh Hưng, Hà Nội, 11712, Việt Nam', 20.99618884390423, 105.90481217775765);
INSERT INTO public.traffic_info_entity VALUES ('49e65400-4b5a-4e9b-b871-56f31abc02f4', '2026-06-12 15:20:04.834616+07', '2026-06-12 15:20:04.834616+07', 'Đường cao tốc Vành đai 3 TP. Hà Nội, Mễ Trì Hạ, Mễ Trì, Phường Từ Liêm, Hà Nội, 10074, Việt Nam', 21.016989636520215, 105.78267305251251);
INSERT INTO public.traffic_info_entity VALUES ('ced580a0-7b63-40a6-b8b2-f3f9b1bcd2b4', '2026-06-11 16:33:58.900798+07', '2026-06-19 09:41:13.103205+07', 'ACB, 461, Ngõ 482 Đường Trương Định, Tương Mai, Phường Tương Mai, Hà Nội, 11715, Việt Nam', 20.984894328715193, 105.84622972392071);
INSERT INTO public.traffic_info_entity VALUES ('8cd2086e-783f-4d6e-8e70-47ffd4bfd6f3', '2026-06-11 15:46:09.261677+07', '2026-06-19 09:46:00.574573+07', 'Cầu Trần Hưng Đạo, Thạch Cầu, Phường Hồng Hà, Hà Nội, 10066, Việt Nam', 21.01988797563314, 105.86577336682873);
INSERT INTO public.traffic_info_entity VALUES ('a60af001-f3ee-4f9e-93a0-fd6a69f01b81', '2026-06-11 15:47:36.859823+07', '2026-06-19 09:48:21.550404+07', 'Phố Hào Nam, Hào Nam, Phường Ô Chợ Dừa, Hà Nội, 11511, Việt Nam', 21.02516573248619, 105.827184333496);
INSERT INTO public.traffic_info_entity VALUES ('f75f4fa9-6b3c-4db0-8474-c47cb3c87cca', '2026-06-19 14:18:26.887736+07', '2026-06-19 14:18:26.887736+07', 'Đường Dương Nội, Phường Dương Nội, Trường An, Hà Nội, 10189, Việt Nam', 20.973918531200738, 105.74771779045568);


--
-- TOC entry 5062 (class 0 OID 18533)
-- Dependencies: 225
-- Data for Name: traffic_speed_entity; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.traffic_speed_entity VALUES ('150f01b3-17b3-4e92-85b9-58faef9e1f78', 1, 52, 229, 'FRC0', 52, 229, false, 'LOW', '0ebb4965-2e93-4f7f-b414-1a296db673eb', '2026-06-22 08:54:53.611488+07', '2026-06-22 08:54:53.611488+07');
INSERT INTO public.traffic_speed_entity VALUES ('91693b23-177f-446a-b872-b57c6a30dd93', 1, 39, 302, 'FRC0', 52, 227, false, 'MEDIUM', '8df25f5c-2bad-4dca-8c69-4aab75af2fa8', '2026-06-22 08:54:53.885455+07', '2026-06-22 08:54:53.885455+07');
INSERT INTO public.traffic_speed_entity VALUES ('ae7ac1d1-fba1-4e03-9132-2a1f3c6c71c0', 1, 46, 1384, 'FRC4', 46, 1384, false, 'LOW', 'b09629b0-31d7-4d24-ba1e-04ed5db91241', '2026-06-22 08:54:54.132935+07', '2026-06-22 08:54:54.132935+07');
INSERT INTO public.traffic_speed_entity VALUES ('4a05688b-1aac-4964-af61-3be54813977a', 1, 39, 302, 'FRC0', 52, 227, false, 'MEDIUM', '761a6a82-94cd-481a-a8d9-bcbd02e5605b', '2026-06-22 08:54:54.372653+07', '2026-06-22 08:54:54.372653+07');
INSERT INTO public.traffic_speed_entity VALUES ('821c2d34-9912-4163-a40b-080b6c9bedfa', 1, 26, 636, 'FRC2', 44, 376, false, 'MEDIUM', '9818e01b-01d1-408f-81bc-98b16defe794', '2026-06-22 08:54:54.61107+07', '2026-06-22 08:54:54.61107+07');
INSERT INTO public.traffic_speed_entity VALUES ('00b84c6c-7d50-44be-9633-689c2f0bae4f', 1, 39, 302, 'FRC0', 52, 227, false, 'MEDIUM', '97ac42c3-2d22-4d3e-9919-e6c12f615c39', '2026-06-22 08:54:54.852954+07', '2026-06-22 08:54:54.852954+07');
INSERT INTO public.traffic_speed_entity VALUES ('a5e9c0aa-fefe-41b6-9f64-bf380d40162f', 1, 26, 393, 'FRC0', 63, 162, false, 'HIGH', '49e65400-4b5a-4e9b-b871-56f31abc02f4', '2026-06-22 08:54:55.100784+07', '2026-06-22 08:54:55.100784+07');
INSERT INTO public.traffic_speed_entity VALUES ('805b9a44-d8c5-4934-9300-fbe93e316d2c', 1, 22, 1005, 'FRC1', 37, 598, false, 'MEDIUM', 'ced580a0-7b63-40a6-b8b2-f3f9b1bcd2b4', '2026-06-22 08:54:55.337328+07', '2026-06-22 08:54:55.337328+07');
INSERT INTO public.traffic_speed_entity VALUES ('4352d4dc-2fb4-4d55-aab9-98bfe15e1977', 1, 30, 2353, 'FRC2', 40, 1764, false, 'MEDIUM', '8cd2086e-783f-4d6e-8e70-47ffd4bfd6f3', '2026-06-22 08:54:55.589058+07', '2026-06-22 08:54:55.589058+07');
INSERT INTO public.traffic_speed_entity VALUES ('509a1899-a3b7-4465-9c59-92cdc6c1c0fd', 1, 22, 345, 'FRC4', 32, 237, false, 'MEDIUM', 'a60af001-f3ee-4f9e-93a0-fd6a69f01b81', '2026-06-22 08:54:55.85322+07', '2026-06-22 08:54:55.85322+07');
INSERT INTO public.traffic_speed_entity VALUES ('53848dee-4b90-44f0-90d7-7dfb939dc632', 0.999506, 27, 1469, 'FRC4', 40, 991, false, 'MEDIUM', 'f75f4fa9-6b3c-4db0-8474-c47cb3c87cca', '2026-06-22 08:54:56.120116+07', '2026-06-22 08:54:56.120116+07');
INSERT INTO public.traffic_speed_entity VALUES ('0bdbe3cf-5648-44ae-8a78-54279d600fc2', 1, 52, 229, 'FRC0', 52, 229, false, 'LOW', '0ebb4965-2e93-4f7f-b414-1a296db673eb', '2026-06-22 09:09:52.982129+07', '2026-06-22 09:09:52.982129+07');
INSERT INTO public.traffic_speed_entity VALUES ('28cb8e13-838d-44f1-8b80-78b5acf6d7de', 1, 38, 310, 'FRC0', 52, 227, false, 'MEDIUM', '8df25f5c-2bad-4dca-8c69-4aab75af2fa8', '2026-06-22 09:09:53.24168+07', '2026-06-22 09:09:53.24168+07');
INSERT INTO public.traffic_speed_entity VALUES ('dd85e783-f3fa-469d-9692-d1e1b5a8e678', 1, 46, 1384, 'FRC4', 46, 1384, false, 'LOW', 'b09629b0-31d7-4d24-ba1e-04ed5db91241', '2026-06-22 09:09:53.496324+07', '2026-06-22 09:09:53.496324+07');
INSERT INTO public.traffic_speed_entity VALUES ('2303d3af-3beb-47f5-9a0a-87fc00878a8c', 1, 38, 310, 'FRC0', 52, 227, false, 'MEDIUM', '761a6a82-94cd-481a-a8d9-bcbd02e5605b', '2026-06-22 09:09:53.740335+07', '2026-06-22 09:09:53.740335+07');
INSERT INTO public.traffic_speed_entity VALUES ('67511273-f842-43b9-b038-fbef5699c141', 1, 26, 636, 'FRC2', 44, 376, false, 'MEDIUM', '9818e01b-01d1-408f-81bc-98b16defe794', '2026-06-22 09:09:53.98938+07', '2026-06-22 09:09:53.98938+07');
INSERT INTO public.traffic_speed_entity VALUES ('3ec2eb93-11a3-4bef-9ed0-0e58fdcf0bdc', 1, 38, 310, 'FRC0', 52, 227, false, 'MEDIUM', '97ac42c3-2d22-4d3e-9919-e6c12f615c39', '2026-06-22 09:09:54.22694+07', '2026-06-22 09:09:54.22694+07');
INSERT INTO public.traffic_speed_entity VALUES ('6228c989-d130-4bf2-b69f-a501b4902d02', 1, 33, 309, 'FRC0', 63, 162, false, 'MEDIUM', '49e65400-4b5a-4e9b-b871-56f31abc02f4', '2026-06-22 09:09:54.482484+07', '2026-06-22 09:09:54.482484+07');
INSERT INTO public.traffic_speed_entity VALUES ('f94d159e-ec47-41f3-9b49-aa5d2c6cde37', 1, 23, 962, 'FRC1', 36, 614, false, 'MEDIUM', 'ced580a0-7b63-40a6-b8b2-f3f9b1bcd2b4', '2026-06-22 09:09:54.734605+07', '2026-06-22 09:09:54.734605+07');
INSERT INTO public.traffic_speed_entity VALUES ('6f32da68-3c9d-49c1-9a52-1b4b8a4beffa', 1, 30, 2353, 'FRC2', 40, 1764, false, 'MEDIUM', '8cd2086e-783f-4d6e-8e70-47ffd4bfd6f3', '2026-06-22 09:09:54.990835+07', '2026-06-22 09:09:54.990835+07');
INSERT INTO public.traffic_speed_entity VALUES ('efc96c34-5774-4c4d-9c08-bfcb096f8800', 1, 22, 345, 'FRC4', 31, 245, false, 'MEDIUM', 'a60af001-f3ee-4f9e-93a0-fd6a69f01b81', '2026-06-22 09:09:55.251952+07', '2026-06-22 09:09:55.251952+07');
INSERT INTO public.traffic_speed_entity VALUES ('aa460af5-8a0c-4693-95a0-b55fcd164cf8', 0.999507, 26, 1525, 'FRC4', 40, 991, false, 'MEDIUM', 'f75f4fa9-6b3c-4db0-8474-c47cb3c87cca', '2026-06-22 09:09:55.496334+07', '2026-06-22 09:09:55.496334+07');


--
-- TOC entry 4886 (class 2606 OID 18496)
-- Name: account_entity account_entity_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account_entity
    ADD CONSTRAINT account_entity_pkey PRIMARY KEY (account_id);


--
-- TOC entry 4890 (class 2606 OID 18502)
-- Name: multiple_account_roles_entity multiple_account_roles_entity_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.multiple_account_roles_entity
    ADD CONSTRAINT multiple_account_roles_entity_pkey PRIMARY KEY (multiple_account_roles_id);


--
-- TOC entry 4902 (class 2606 OID 18573)
-- Name: profile_entity profile_entity_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile_entity
    ADD CONSTRAINT profile_entity_pkey PRIMARY KEY (account_id);


--
-- TOC entry 4892 (class 2606 OID 18511)
-- Name: role_entity role_entity_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role_entity
    ADD CONSTRAINT role_entity_pkey PRIMARY KEY (role_id);


--
-- TOC entry 4894 (class 2606 OID 18518)
-- Name: session_token_entity session_token_entity_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.session_token_entity
    ADD CONSTRAINT session_token_entity_pkey PRIMARY KEY (token_id);


--
-- TOC entry 4896 (class 2606 OID 18525)
-- Name: traffic_aqi_entity traffic_aqi_entity_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.traffic_aqi_entity
    ADD CONSTRAINT traffic_aqi_entity_pkey PRIMARY KEY (traffic_aqi_id);


--
-- TOC entry 4898 (class 2606 OID 18532)
-- Name: traffic_info_entity traffic_info_entity_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.traffic_info_entity
    ADD CONSTRAINT traffic_info_entity_pkey PRIMARY KEY (traffic_id);


--
-- TOC entry 4900 (class 2606 OID 18541)
-- Name: traffic_speed_entity traffic_speed_entity_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.traffic_speed_entity
    ADD CONSTRAINT traffic_speed_entity_pkey PRIMARY KEY (traffic_speed_id);


--
-- TOC entry 4888 (class 2606 OID 18583)
-- Name: account_entity ukskli1p4yc24r10yw9d0mw8x70; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account_entity
    ADD CONSTRAINT ukskli1p4yc24r10yw9d0mw8x70 UNIQUE (account);


--
-- TOC entry 4906 (class 2606 OID 18557)
-- Name: traffic_aqi_entity fk29pcb87h5gwbrx5p0swwa6t8e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.traffic_aqi_entity
    ADD CONSTRAINT fk29pcb87h5gwbrx5p0swwa6t8e FOREIGN KEY (traffic_id) REFERENCES public.traffic_info_entity(traffic_id);


--
-- TOC entry 4903 (class 2606 OID 18542)
-- Name: multiple_account_roles_entity fk3wssl09st3xi3yog8shuemk9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.multiple_account_roles_entity
    ADD CONSTRAINT fk3wssl09st3xi3yog8shuemk9 FOREIGN KEY (account_id) REFERENCES public.account_entity(account_id);


--
-- TOC entry 4908 (class 2606 OID 18574)
-- Name: profile_entity fk96jxd5vbor61ieinhnhhmp8i9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile_entity
    ADD CONSTRAINT fk96jxd5vbor61ieinhnhhmp8i9 FOREIGN KEY (account_id) REFERENCES public.account_entity(account_id);


--
-- TOC entry 4905 (class 2606 OID 18552)
-- Name: session_token_entity fkdcnlg49i7phn1ppddvu05vy16; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.session_token_entity
    ADD CONSTRAINT fkdcnlg49i7phn1ppddvu05vy16 FOREIGN KEY (account_id) REFERENCES public.account_entity(account_id);


--
-- TOC entry 4904 (class 2606 OID 18547)
-- Name: multiple_account_roles_entity fkf64eowxh3tt29fuexu7e1tlv4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.multiple_account_roles_entity
    ADD CONSTRAINT fkf64eowxh3tt29fuexu7e1tlv4 FOREIGN KEY (role_id) REFERENCES public.role_entity(role_id);


--
-- TOC entry 4907 (class 2606 OID 18562)
-- Name: traffic_speed_entity fkn1lltfq4cmeosbaq3gqrd3aft; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.traffic_speed_entity
    ADD CONSTRAINT fkn1lltfq4cmeosbaq3gqrd3aft FOREIGN KEY (traffic_id) REFERENCES public.traffic_info_entity(traffic_id);


-- Completed on 2026-06-22 09:13:59

--
-- PostgreSQL database dump complete
--

\unrestrict ZVa0PcvYf0VEMOaxu4reFg0jgFt2YTeq2mYZit4q7qdKCdfGj0Zpb07WCFQfEeR

