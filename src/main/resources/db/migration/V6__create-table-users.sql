create table users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    login varchar(100) not null unique,
    password varchar(255) not null,
    role varchar(50) not null
);