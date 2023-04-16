DELIMITER $$
create procedure insertLoop3()
begin
	declare i INT default 1;
	declare k int default 1;
    declare ei int default 1;
	while i <= 10000 DO
		insert into novel(novel_id, author, description, free_type, genre, title)
		values (i, 'a', 'a', 'FREE', 'FANTANSY', concat('테스트',i));

        while k <= 15 do
			insert into novel_episode(episode_id, create_date, hit, price, sequence, title, novel_id)
			values (ei, now(), 0,0,k,'title',i);
			set ei = ei + 1;
            set k = k + 1;
end while;
		set k = 0;
		set i = i + 1;
END WHILE;

END$$
DELIMITER

CALL insertLoop3();