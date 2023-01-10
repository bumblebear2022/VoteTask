CREATE TABLE data.genres
(
    id bigserial,
    name text NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS data.genres
    OWNER to vote_user;

CREATE TABLE data.performers
(
    id bigserial,
    name text NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS data.performers
    OWNER to vote_user;

CREATE TABLE data.votes
(
    id bigserial,
    date_time timestamp without time zone NOT NULL,
    about text NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS data.votes
    OWNER to vote_user;

CREATE TABLE IF NOT EXISTS data.vote_genre
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
)

ALTER TABLE IF EXISTS data.vote_genre
    OWNER to vote_user;

CREATE TABLE IF NOT EXISTS data.vote_performer
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
)

ALTER TABLE IF EXISTS data.vote_performer
    OWNER to vote_user;