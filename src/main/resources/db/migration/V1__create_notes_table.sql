
CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE OR REPLACE FUNCTION uuid_generate_v7()
RETURNS uuid AS $$
DECLARE 
    unix_time_ms bytea;
    uuid_bytes bytea;
BEGIN
    -- 48 bits de epoch time en milisegundos
    unix_time_ms = substring(int8send(floor(extract(epoch from clock_timestamp()) * 1000)::bigint) from 3 for 6);
    
    -- 10 bytes aleatorios (requiere pgcrypto)
    uuid_bytes = unix_time_ms || gen_random_bytes(10);
    
    -- Ajustar versión (0111 -> 7) en el byte 7
    uuid_bytes = set_byte(uuid_bytes, 6, (get_byte(uuid_bytes, 6) & 15) | 112);
    -- Ajustar variante RFC 4122 (10xx) en el byte 9
    uuid_bytes = set_byte(uuid_bytes, 8, (get_byte(uuid_bytes, 8) & 63) | 128);

    RETURN encode(uuid_bytes, 'hex')::uuid;
END;
$$ LANGUAGE plpgsql VOLATILE;

CREATE TABLE IF NOT EXISTS notes (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v7(),
    title text NOT NULL CHECK(length(title) BETWEEN 1 AND 150),
    body text NOT NULL DEFAULT '',
    created_at timestamptz NOT NULL DEFAULT now()
);