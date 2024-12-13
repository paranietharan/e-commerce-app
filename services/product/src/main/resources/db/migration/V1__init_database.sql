create table if not exists category
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    description varchar(255),
    name varchar(255)
);

create table if not exists product
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    available_quantity int not null,
    description varchar(255),
    name varchar(255),
    price numeric(38, 2),
    category_id integer,
    constraint fk1mtsbur82frn64de7balymq9s foreign key (category_id) references category(id)
);