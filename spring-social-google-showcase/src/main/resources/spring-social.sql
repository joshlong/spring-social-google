-- this schema was originally found on https://github.com/SpringSource/spring-social/blob/master/spring-social-core/src/main/resources/org/springframework/social/connect/jdbc/JdbcUsersConnectionRepository.sql
create table UserConnection (userId varchar(255) not null,
	providerId varchar(255) not null,
	providerUserId varchar(255),
	rank int not null,
	displayName varchar(255),
	profileUrl varchar(512),
	imageUrl varchar(512),
	accessToken varchar(255) not null,
	secret varchar(255),
	refreshToken varchar(255),
	expireTime bigint,
	primary key (userId, providerId, providerUserId));


create unique index UserConnectionRank on UserConnection(userId, providerId, rank);
