create table bandroom (
                         band_room_id bigint not null primary key,
                         admin_id bigint not null,
                         band_room_name varchar(100) not null,
                         band_room_number varchar(100) not null,
                         band_room_email varchar(100),
                         band_room_email_domain varchar(100),

                         created_at datetime not null,
                         modified_at datetime not null
);


