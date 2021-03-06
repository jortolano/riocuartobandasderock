CREATE TABLE Album (
	id text primary key,
	title text not null,
	releaseDate date
);

CREATE TABLE artistDB (
    artistID text primary key,
    name text,
    surname text,
    nickname text
);

CREATE TABLE SongDB (
    idSong int primary key, 
    name text not null, 
    duration int
);

CREATE TABLE BandDB (
    bandID text primary key,
    name text not null,
    genre text not null
);



GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO rock_db_owner;
