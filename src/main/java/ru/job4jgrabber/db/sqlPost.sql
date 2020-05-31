create table post(
  id serial primary key,
  name text,
  text text,
  link text uniquq,
  created timestamp
);