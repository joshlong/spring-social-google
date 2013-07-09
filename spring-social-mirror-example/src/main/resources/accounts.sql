create table user_account (id  serial,
						username varchar unique,
						password varchar not null,
						firstName varchar not null,
						lastName varchar not null,
						primary key (id));


insert into user_account (username, password, firstName, lastName) values ('habuma', 'freebirds', 'Craig', 'Walls');
insert into user_account (username, password, firstName, lastName) values ('kdonald', 'melbourne', 'Keith', 'Donald');
insert into user_account (username, password, firstName, lastName) values ('rclarkson', 'atlanta', 'Roy', 'Clarkson');
insert into user_account (username, password, firstName, lastName) values ('jlong', 'cowbell', 'Josh', 'Long');


