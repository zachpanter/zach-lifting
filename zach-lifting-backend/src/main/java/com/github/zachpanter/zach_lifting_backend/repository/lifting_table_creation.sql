CREATE TABLE lift (
    lift_id serial PRIMARY KEY,
    lift_name text UNIQUE NOT NULL,
    priority int,
    one_rm_max int
);
INSERT INTO lift (lift_id, lift_name, priority, one_rm_max) VALUES (1, );


CREATE TABLE muscle_group_lookup (
    muscle_group_id serial PRIMARY KEY,
    muscle_group_name text UNIQUE NOT NULL
);

CREATE TABLE muscle (
    muscle_id serial PRIMARY KEY,
    muscle_name text UNIQUE NOT NULL,
    muscle_group_id int NOT NULL,
    FOREIGN KEY (muscle_group_id) REFERENCES muscle_group_lookup (muscle_group_id)
);

CREATE TABLE lift_muscle_xref (
    lift_id serial NOT NULL,
    muscle_id int NOT NULL,
    UNIQUE (lift_id, muscle_id)
);

CREATE TABLE log (
    log_id serial PRIMARY KEY,
    timestamp TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    lift_id int NOT NULL,
    reps int NOT NULL,
    resistance int NOT NULL,
    FOREIGN KEY (lift_id) REFERENCES lift(lift_id)
);

CREATE TABLE unit_lookup (
    unit_lookup_id serial PRIMARY KEY,
    unit_lookup_name text UNIQUE,
);
INSERT INTO unit_lookup (unit_lookup_name) VALUES ('cm');
INSERT INTO unit_lookup (unit_lookup_name) VALUES ('lbs');

CREATE TABLE morphometric(
    morphometric_id serial PRIMARY KEY,
    value decimal NOT NULL,
    unit_id int NOT NULL,
    timestamp TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    FOREIGN KEY (unit_id) REFERENCES unit_lookup(unit_id)
);

CREATE TABLE calisthenic_variant (
    calisthenic_variant_id serial PRIMARY KEY,
    calisthenic_variant_name text NOT NULL,
    reference_lift_id int NOT NULL,
    FOREIGN KEY (reference_lift_id) REFERENCES lift(lift_id),
    UNIQUE (calisthenic_variant_name, reference_lift_id)
);

-- TODO: CREATE VIEWS
CREATE VIEW muscles_with_groups ();