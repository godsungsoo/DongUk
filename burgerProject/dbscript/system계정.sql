CREATE user burger identified by burger;

rollback;
grant resource, connect, create view, create sequence to burger;