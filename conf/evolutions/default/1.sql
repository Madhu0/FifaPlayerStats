# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table user (
  id                            bigint auto_increment not null,
  email                         varchar(30),
  name                          varchar(30),
  password_hash                 varchar(100),
  created_at                    datetime(6) not null,
  modified_at                   datetime(6) not null,
  constraint uq_user_email unique (email),
  constraint pk_user primary key (id)
);


# --- !Downs

drop table if exists user;

