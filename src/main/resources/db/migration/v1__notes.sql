CREATE TABLE notes (
    id uuid PRIMARY KEY DEFAULT uuidv7(),
    title text NOT NULL CHECK(length(title) BETWEEN 1 AND 150),
    body text NOT NULL DEFAULT '',
    created_at timestamptz NOT NULL DEFAULT now()
);