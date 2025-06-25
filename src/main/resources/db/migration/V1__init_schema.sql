-- Optional: explicitly create schema
CREATE SCHEMA IF NOT EXISTS public;

-- Table: match
CREATE TABLE match (
                       id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       description VARCHAR(255) NOT NULL,
                       match_date VARCHAR(20) NOT NULL,
                       match_time VARCHAR(10) NOT NULL,
                       team_a VARCHAR(100) NOT NULL,
                       team_b VARCHAR(100) NOT NULL,
                       sport SMALLINT NOT NULL
);

-- Indexes for match table
CREATE INDEX idx_match_date ON match (match_date);
CREATE INDEX idx_match_time ON match (match_time);
CREATE INDEX idx_match_sport ON match (sport);
CREATE INDEX idx_match_team_a ON match (team_a);
CREATE INDEX idx_match_team_b ON match (team_b);

-- Table: match_odds
CREATE TABLE match_odds (
                            id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                            match_id INTEGER NOT NULL REFERENCES match(id) ON DELETE CASCADE,
                            specifier VARCHAR(50) NOT NULL,
                            odd DECIMAL(5, 2) NOT NULL
);

-- Index for foreign key in match_odds
CREATE INDEX idx_match_odds_match_id ON match_odds (match_id);