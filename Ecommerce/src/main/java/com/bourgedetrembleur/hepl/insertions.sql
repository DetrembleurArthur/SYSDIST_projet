
truncate table command;
truncate table item;
truncate table category;
truncate table article;
truncate table command_items;
truncate table user;
-- drop event if exists dropStandByCommandEvent;
-- drop event if exists e_hourly;
-- create event dropStandByCommandEvent
-- on schedule
-- every 1 minute
-- do
-- delete from command where status like 'En stand by';*/

drop trigger if exists del_com_item;

CREATE TRIGGER del_com_item
    before DELETE
         ON item FOR EACH ROW
BEGIN
    delete from command_items where command_items.items_id = OLD.id;
END;


insert into category values(1, 'book', 6);
insert into category values(2, 'video-game', 21);
insert into category values(3, 'goodie', 12);


insert into article values(1, 'Halo cryptum', 8.5, 10, 1);
insert into article values(2, 'Halo primordium', 9, 10, 1);
insert into article values(3, 'Halo silentium', 15.0, 10, 1);
insert into article values(4, 'Halo Combat Evolved', 40.0, 10, 2);
insert into article values(5, 'Halo 2', 45.5, 10, 2);
insert into article values(6, 'Halo 3', 50.0, 10, 2);
insert into article values(7, 'Halo 3 ODST', 30.0, 10, 2);
insert into article values(8, 'Halo 4', 50.0, 10, 2);
insert into article values(9, 'Halo 5 Guardians', 60.0, 10, 2);
insert into article values(10, 'Halo Reach', 55.0, 10, 2);
insert into article values(11, 'Halo 4 Poster', 4.0, 10, 3);
insert into article values(12,'Halo 3 T-shirt', 8.0, 10, 3);
insert into article values(13,'Halo 2 Mug', 7.5, 10, 3);
