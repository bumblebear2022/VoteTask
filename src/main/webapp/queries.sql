CREATE TABLE data.genres
(
    id bigserial,
    name text NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS data.genres
    OWNER to postgres;

CREATE TABLE data.performers
(
    id bigserial,
    name text NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS data.performers
    OWNER to postgres;

CREATE TABLE data.votes
(
    id bigserial,
    date_time timestamp without time zone NOT NULL,
    about text NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS data.votes
    OWNER to postgres;

CREATE TABLE data.emails
(
    id bigserial,
    email text NOT NULL,
    isSent boolean NOT NULL,
    sendingAttempts bigint NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS data.emails
    OWNER to postgres;

CREATE TABLE data.vote_genre
(
    id_vote bigint NOT NULL,
    id_genre bigint NOT NULL,
    CONSTRAINT genre_fk FOREIGN KEY (id_genre)
        REFERENCES data.genres (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT vote_fk FOREIGN KEY (id_vote)
        REFERENCES data.votes (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

ALTER TABLE IF EXISTS data.vote_genre
    OWNER to postgres;

CREATE TABLE data.vote_performer
(
    id_vote bigint NOT NULL,
    id_performer bigint NOT NULL,
    CONSTRAINT performer_fk FOREIGN KEY (id_performer)
        REFERENCES data.performers (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT vote_fk FOREIGN KEY (id_vote)
        REFERENCES data.votes (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

ALTER TABLE IF EXISTS data.vote_performer
    OWNER to postgres;

CREATE TABLE data.vote_email
(
    id_vote bigint NOT NULL,
    id_email bigint NOT NULL,
    CONSTRAINT email_fk FOREIGN KEY (id_email)
        REFERENCES data.emails (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT vote_fk FOREIGN KEY (id_vote)
        REFERENCES data.votes (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

ALTER TABLE IF EXISTS data.vote_email
    OWNER to postgres;