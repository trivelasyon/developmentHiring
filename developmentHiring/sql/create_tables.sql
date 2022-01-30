-- Table: trendyol.bag

-- DROP TABLE trendyol.bag;

-- Table: trendyol.delivery_point

-- DROP TABLE trendyol.delivery_point;

CREATE SCHEMA trendyol

CREATE TABLE trendyol.delivery_point
(
    delivery_point integer NOT NULL,
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    created_by character varying(50) COLLATE pg_catalog."default" NOT NULL,
    created_date time without time zone NOT NULL,
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    active boolean NOT NULL,
    CONSTRAINT delivery_point_pkey PRIMARY KEY (id),
    CONSTRAINT u_delivery_point UNIQUE (delivery_point)
)

TABLESPACE pg_default;

ALTER TABLE trendyol.delivery_point
    OWNER to postgres;

CREATE TABLE trendyol.shipment
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    created_by character varying(50) COLLATE pg_catalog."default" NOT NULL,
    delivery_point integer NOT NULL,
    volumetric_weight integer NOT NULL,
    created_date timestamp without time zone NOT NULL,
    barcode character varying(50) COLLATE pg_catalog."default" NOT NULL,
    active boolean NOT NULL,
    status character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT shipment_pkey PRIMARY KEY (id),
    CONSTRAINT u_barcode UNIQUE (barcode),
    CONSTRAINT fk_delivery_point FOREIGN KEY (delivery_point)
        REFERENCES trendyol.delivery_point (delivery_point) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE trendyol.shipment
    OWNER to postgres;


CREATE TABLE trendyol.bag
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    created_date timestamp without time zone NOT NULL,
    barcode character varying(50) COLLATE pg_catalog."default" NOT NULL,
    delivery_point integer NOT NULL,
    active boolean NOT NULL,
    created_by character varying(50) COLLATE pg_catalog."default" NOT NULL,
    status character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT u_bag_barcode UNIQUE (barcode),
    CONSTRAINT fk_delivery_point FOREIGN KEY (delivery_point)
        REFERENCES trendyol.delivery_point (delivery_point) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE trendyol.bag
    OWNER to postgres;



-- Table: trendyol.log

-- DROP TABLE trendyol.log;

CREATE TABLE trendyol.log
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    created_date timestamp without time zone NOT NULL,
    barcode character varying(50) COLLATE pg_catalog."default" NOT NULL,
    message character varying(255) COLLATE pg_catalog."default" NOT NULL,
    active boolean NOT NULL,
    created_by character varying(50) COLLATE pg_catalog."default" NOT NULL
)

TABLESPACE pg_default;

ALTER TABLE trendyol.log
    OWNER to postgres;


-- Table: trendyol.package_in_bag

-- DROP TABLE trendyol.package_in_bag;

CREATE TABLE trendyol.package_in_bag
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    created_date timestamp without time zone NOT NULL,
    barcode character varying(50) COLLATE pg_catalog."default" NOT NULL,
    bag_barcode character varying(50) COLLATE pg_catalog."default" NOT NULL,
    created_by character varying(50) COLLATE pg_catalog."default" NOT NULL,
    active boolean NOT NULL,
    CONSTRAINT package_bag_pkey PRIMARY KEY (id),
    CONSTRAINT fk_bag_barcode FOREIGN KEY (bag_barcode)
        REFERENCES trendyol.bag (barcode) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_barcode FOREIGN KEY (barcode)
        REFERENCES trendyol.shipment (barcode) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE trendyol.package_in_bag
    OWNER to postgres;


-- Table: trendyol.shipment

-- DROP TABLE trendyol.shipment;


-- Table: trendyol.vehicle

-- DROP TABLE trendyol.vehicle;

CREATE TABLE trendyol.vehicle
(
    license_plate character varying(50) COLLATE pg_catalog."default" NOT NULL,
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    created_date timestamp without time zone,
    created_by character varying(50) COLLATE pg_catalog."default" NOT NULL,
    active boolean NOT NULL,
    CONSTRAINT u_license_plate UNIQUE (license_plate)
)

TABLESPACE pg_default;

ALTER TABLE trendyol.vehicle
    OWNER to postgres;

COMMENT ON TABLE trendyol.vehicle
    IS 'vehicle table';

COMMENT ON COLUMN trendyol.vehicle.license_plate
    IS 'license plate';