-- Switch to your app database
CREATE DATABASE smartlibrary;

\c smartlibrary;

-- Create the users table
CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  name TEXT,
  email TEXT,
  role TEXT
);

-- Create the books table
CREATE TABLE books (
  id SERIAL PRIMARY KEY,
  title TEXT NOT NULL,
  author TEXT NOT NULL,
  isbn TEXT UNIQUE NOT NULL,
  copies INT NOT NULL CHECK (copies >= 0),
  available_copies INT NOT NULL CHECK (available_copies >= 0),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE loan (
  id SERIAL PRIMARY KEY,
  user_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
  book_id INT NOT NULL REFERENCES books(id) ON DELETE CASCADE,
  issue_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  due_date TIMESTAMP NOT NULL,
  status TEXT NOT NULL DEFAULT 'ACTIVE' CHECK (status IN ('ACTIVE', 'RETURNED', 'OVERDUE'))
);


-- Function to set available_copies = copies on insert
CREATE OR REPLACE FUNCTION set_available_copies()
RETURNS TRIGGER AS $$
BEGIN
  IF NEW.available_copies IS NULL THEN
    NEW.available_copies := NEW.copies;
  END IF;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger to set available_copies before insert
CREATE TRIGGER trg_set_available_copies
BEFORE INSERT ON books
FOR EACH ROW
EXECUTE FUNCTION set_available_copies();

-- Function to update updated_at on row update
CREATE OR REPLACE FUNCTION update_updated_at()
RETURNS TRIGGER AS $$
BEGIN
  NEW.updated_at := CURRENT_TIMESTAMP;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger to auto-update updated_at timestamp
CREATE TRIGGER trg_update_updated_at
BEFORE UPDATE ON books
FOR EACH ROW
EXECUTE FUNCTION update_updated_at();

-- Create user for your app
CREATE USER librarian WITH PASSWORD 'librarian';

-- Give user access to the database
GRANT ALL PRIVILEGES ON DATABASE smartlibrary TO librarian;

-- Grant connection and usage rights
GRANT CONNECT ON DATABASE smartlibrary TO librarian;
GRANT USAGE ON SCHEMA public TO librarian;

-- Grant access to tables and sequences
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO librarian;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO librarian;

-- Automatically grant these permissions for future tables and sequences
ALTER DEFAULT PRIVILEGES IN SCHEMA public
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO librarian;

ALTER DEFAULT PRIVILEGES IN SCHEMA public
GRANT USAGE, SELECT ON SEQUENCES TO librarian;

